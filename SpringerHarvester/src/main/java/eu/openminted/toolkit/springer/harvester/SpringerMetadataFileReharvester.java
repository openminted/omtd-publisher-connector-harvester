package eu.openminted.toolkit.springer.harvester;

import com.google.gson.Gson;
import eu.openminted.toolkit.crossref.CrossRefClient;
import eu.openminted.toolkit.crossref.model.works.WorksResponse;
import eu.openminted.toolkit.database.model.GenericFile;
import eu.openminted.toolkit.database.services.GenericArticleFileDAO;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author lucasanastasiou
 */
@Component
public class SpringerMetadataFileReharvester implements CommandLineRunner {

    @Autowired
    GenericArticleFileDAO genericArticleFileDAO;

    @Autowired
    CrossRefClient crossRefClient;

    private Logger logger = LoggerFactory.getLogger("SpringerMetadataFileReharvester");

    @Override
    public void run(String... strings) throws Exception {
        int start = 114459;
        int end = 200000;
        logger.info("Running from :" + start);
        for (int page = start; page < end; page = page + 1000) {
            List<GenericFile> genericFiles = genericArticleFileDAO.retrievePublisherGenericFilePage("Springer-OA", page, 1000);

            for (GenericFile genericFile : genericFiles) {
                if (genericFile != null) {
                    WorksResponse worksResponse = crossRefClient.getByDoi(genericFile.getDoi());

                    if (worksResponse != null && genericFile.getMetadata_filename() != null && !genericFile.getMetadata_filename().isEmpty()) {
                        logger.info("Overwriting :" + genericFile.getMetadata_filename());
                        try {
                            File file = new File(genericFile.getMetadata_filename());

                            logger.info(String.format("Saving %s into %s", genericFile.getDoi(), file.getAbsolutePath()));

                            Gson gson = new Gson();
                            String metadataToSave = gson.toJson(worksResponse.getMessage());

                            InputStream is = new ByteArrayInputStream(metadataToSave.getBytes(StandardCharsets.UTF_8));
                            Files.copy(is, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        } catch (Exception e) {
                            logger.error("Exception while processing:" + genericFile.getDoi() + " :" + e.getMessage());
                        }
                    }
                }
            }
        }
    }

}
