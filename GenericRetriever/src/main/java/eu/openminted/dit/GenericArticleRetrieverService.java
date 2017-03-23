package eu.openminted.dit;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import org.springframework.stereotype.Service;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author lucasanastasiou
 */
@Service
public class GenericArticleRetrieverService {

    public void retrieveUrlToFile(String articleUrl,String fileLocation) throws GenericRetrieverException {
        InputStream in = null;
        try {
            in = new URL(articleUrl).openStream();

            File file = new File(fileLocation);
            Files.copy(in, file.toPath(),StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException iOException) {
            throw new GenericRetrieverException("Cannot retrieve url :" + articleUrl, iOException);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }
    
    
}
