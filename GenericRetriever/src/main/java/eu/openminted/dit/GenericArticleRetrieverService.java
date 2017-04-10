package eu.openminted.dit;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import org.springframework.stereotype.Service;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author lucasanastasiou
 */
@Service
public class GenericArticleRetrieverService {

    private Logger logger = LoggerFactory.getLogger("GenericArticleRetrieverService");
    public void retrieveUrlToFile(String articleUrl,String fileLocation) throws GenericRetrieverException {
        InputStream in = null;
        try {
            in = new URL(articleUrl).openStream();

            File file = new File(fileLocation);
            
            logger.info(String.format("Downloading %s into %s", articleUrl,file.getAbsolutePath()));
            
            Files.copy(in, file.toPath(),StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException iOException) {
            throw new GenericRetrieverException("Cannot retrieve url :" + articleUrl, iOException);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }
    
    
}
