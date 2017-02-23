package eu.openminted.toolkit.elsevier.retriever;

import com.google.gson.Gson;
import eu.openminted.toolkit.database.exceptions.DatabaseException;
import eu.openminted.toolkit.database.services.ArticleFilesDAO;
import eu.openminted.toolkit.database.services.NodeProcessingDAO;
import eu.openminted.toolkit.elsevier.beans.article.FullTextRetrievalResponse;
import eu.openminted.toolkit.elsevier.processor.exceptions.ElsevierProcessorException;
import eu.openminted.toolkit.queue.ArticleUrl;
import eu.openminted.toolkit.storage.StorageDAO;
import eu.openminted.toolkit.storage.exceptions.StorageException;
import java.io.IOException;
import javax.xml.bind.JAXBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author lucasanastasiou
 */
public class Retriever {

    @Autowired
    ArticleMetadataParser articleMetadataParser;

    @Autowired
    ArticleRetrieverService articleRetrieverService;

    @Autowired
    StorageDAO storageDAO;
    
    @Autowired
    ArticleFilesDAO articleFilesDAO;
    
    @Autowired
    NodeProcessingDAO nodeProcessingDAO;

    Logger logger = LoggerFactory.getLogger("Retriever");

    public void receiveMessage(String message) {
        try {
            ArticleUrl articleUrlMessage = parseArticleUrlFromMessage(message);
            
            String articleUrl = articleUrlMessage.getArticleUrl();
            long dbId = articleUrlMessage.getDbId();

            logger.info("Processing url :" + articleUrl);
            String metadataFileLocation = storageDAO.getMetadataFileLocation(articleUrl);

            //
            articleRetrieverService.retrieveArticleMetadata(articleUrl, metadataFileLocation);
            //
            String articleMetadataContent = storageDAO.getFileContents(metadataFileLocation);

            FullTextRetrievalResponse fArticle = articleMetadataParser.getFullTextRetrievalResponse(articleMetadataContent.trim());

            String doi = fArticle.getCoredata().getDoi();

            articleFilesDAO.insertNewArticleFiles(dbId, doi);

            logger.info("Inserting dbId:" + dbId + " doi:" + doi + " metafile location:" + metadataFileLocation);
            articleFilesDAO.setMetadataFilename(dbId, doi, metadataFileLocation);

            if (fArticle.getCoredata().isOpenaccessArticle()) {
                String pdfFileLocation = storageDAO.getPdfFileLocation(articleUrl);

                //
                articleRetrieverService.retrieveArticlePdf(articleUrl, pdfFileLocation);
                //

                articleFilesDAO.setPdfFilename(dbId, doi, pdfFileLocation);

            }

            nodeProcessingDAO.setNodeProcessingEnd(dbId);

        } catch (JAXBException j) {
            logger.error("Error while parsing article metadata response", j);
        } catch (IOException ex) {
            logger.error("IO exception", ex);
        } catch (StorageException ex) {
            logger.error("Storage exception :", ex);
        } catch (DatabaseException ex) {
            logger.error("Database exception :", ex);
        } catch (ElsevierProcessorException ex) {
            logger.error("Processing exception :", ex);
        }

    }

    private ArticleUrl parseArticleUrlFromMessage(String message) {
        Gson gson = new Gson();
        ArticleUrl articleUrl = gson.fromJson(message, ArticleUrl.class);        
        return articleUrl;
    }
    
}
