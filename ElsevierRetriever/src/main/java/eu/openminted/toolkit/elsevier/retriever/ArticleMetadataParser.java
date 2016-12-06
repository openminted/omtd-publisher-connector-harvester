package eu.openminted.toolkit.elsevier.retriever;

import eu.openminted.toolkit.elsevier.beans.article.FullTextRetrievalResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucasanastasiou
 */
@Service
public class ArticleMetadataParser {

    Unmarshaller unmarshaller = null;

    public ArticleMetadataParser() {
        try {
            JAXBContext jc = JAXBContext.newInstance(FullTextRetrievalResponse.class);
            unmarshaller = jc.createUnmarshaller();
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
    }

    public FullTextRetrievalResponse getFullTextRetrievalResponse(String htmlContent) throws JAXBException, IOException {

        System.out.println("---");
        System.out.println("htmlContent = " + htmlContent);
        System.out.println("---");
        
        FullTextRetrievalResponse articleResponse = (FullTextRetrievalResponse) unmarshaller.unmarshal(IOUtils.toInputStream(htmlContent, "UTF-8"));
        return articleResponse;
    }

    boolean isOpenAccess(String articleMetadata) {
        FullTextRetrievalResponse article;
        try {
            article = getFullTextRetrievalResponse(articleMetadata);
            return article.getCoredata().isOpenaccessArticle();
        } catch (JAXBException ex) {
            Logger.getLogger(ArticleMetadataParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArticleMetadataParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }


}
