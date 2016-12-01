package eu.openminted.toolkit.elsevier.processor;

import eu.openminted.toolkit.elsevier.processor.exceptions.ElsevierProcessorException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucasanastasiou
 */
@Service
public class ArticleRetrieverService {

    private void retrieveUrlToFile(String articleUrl,String fileLocation) throws ElsevierProcessorException {
        InputStream in = null;
        try {
            in = new URL(articleUrl).openStream();

            File file = new File(fileLocation);
            Files.copy(in, file.toPath(),StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException iOException) {
            throw new ElsevierProcessorException("Cannot retrieve url :" + articleUrl, iOException);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }
    
    public void retrieveArticleMetadata(String articleUrl, String metadataFileLocation) throws ElsevierProcessorException {
        String metadataArticleUrl = articleUrl+"?httpAccept=application/xml";
        retrieveUrlToFile(metadataArticleUrl,metadataFileLocation);
    }
    
    public void retrieveArticlePdf(String articleUrl, String pdfFileLocation) throws ElsevierProcessorException {
        String pdfArticleUrl = articleUrl+"?httpAccept=application/pdf";
        retrieveUrlToFile(pdfArticleUrl,pdfFileLocation);
    }
    
//    public static void main(String args[]) throws Exception{
//        String url ="http://api.elsevier.com/content/article/pii/S0927776515303544";
//        
//        ArticleRetrieverService a= new ArticleRetrieverService();
//        String meta = a.retrieveArticleMetadata(url);
//        String pdf = a.retrieveArticlePdf(url);
//        
//        FileUtils.writeStringToFile(new File("result.pdf"), pdf);
//        
//        System.out.println("meta = " + meta);
////        System.out.println("pdf = " + pdf);
//    }

}
