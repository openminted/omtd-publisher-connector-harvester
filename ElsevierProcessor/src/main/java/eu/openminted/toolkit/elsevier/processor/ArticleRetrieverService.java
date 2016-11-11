package eu.openminted.toolkit.elsevier.processor;

import eu.openminted.toolkit.elsevier.processor.exceptions.ElsevierProcessorException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucasanastasiou
 */
@Service
public class ArticleRetrieverService {

    void retrieveArticle(String articleUrl) throws ElsevierProcessorException {
        InputStream in = null;
        try {
            in = new URL(articleUrl).openStream();

            System.out.println(IOUtils.toString(in));
        } catch (IOException iOException) {
            throw new ElsevierProcessorException("Cannot retrieve url :" + articleUrl, iOException);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

}
