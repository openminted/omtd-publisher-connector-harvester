package eu.openminted.toolkit.elsevier.processor;

import eu.openminted.toolkit.database.exceptions.DatabaseException;
import eu.openminted.toolkit.database.services.ArticleFilesDAO;
import eu.openminted.toolkit.database.services.NodeProcessingDAO;
import eu.openminted.toolkit.elsevier.beans.article.FullTextRetrievalResponse;
import eu.openminted.toolkit.elsevier.processor.exceptions.ElsevierProcessorException;
import eu.openminted.toolkit.queue.LeafNode;
import eu.openminted.toolkit.storage.StorageDAO;
import eu.openminted.toolkit.storage.exceptions.StorageException;
import java.io.IOException;
import java.util.List;
import javax.xml.bind.JAXBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author lucasanastasiou
 */
public class Processor {
    
    @Autowired
    LeafNodeRetrieverService leafNodeRetrieverService;
    
    @Autowired
    LeafNodeParser leafNodeParser;
    
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
    
    Logger logger = LoggerFactory.getLogger("Processor");
    
    public void receiveMessage(String message) {
        
        LeafNode leafNode = leafNodeRetrieverService.parseLeafNode(message);
        
        try {
            long dbId = nodeProcessingDAO.addNewNodeProcessing(leafNode.getUrl());
            
            List<String> articleUrls = leafNodeParser.getArticleUrls(leafNode);
            
            for (String articleUrl : articleUrls) {
                try {
                    
                    String metadataFileLocation = storageDAO.getMetadataFileLocation(articleUrl);
                    
                    //
                    articleRetrieverService.retrieveArticleMetadata(articleUrl,metadataFileLocation);
                    //
//                    String mFilename = storageDAO.storeMetadataFile(articleUrl, articleMetadata);
                    
                    FullTextRetrievalResponse fArticle = null;
                    
                    String articleMetadataContent = storageDAO.getFileContents(metadataFileLocation);
                    fArticle = articleMetadataParser.getFullTextRetrievalResponse(articleMetadataContent);
                    String doi = fArticle.getCoredata().getDoi();
                    articleFilesDAO.insertNewArticleFiles(dbId, doi);
                    articleFilesDAO.setMetadataFilename(dbId, doi, metadataFileLocation);
                    
                    if (fArticle.getCoredata().isOpenaccessArticle()) {
                        String pdfFileLocation = storageDAO.getPdfFileLocation(articleUrl);
                        
                        //
                        articleRetrieverService.retrieveArticlePdf(articleUrl,pdfFileLocation);
                        //
                        
                        articleFilesDAO.setPdfFilename(dbId, doi, pdfFileLocation);
                        
                    }
                    
                } catch (ElsevierProcessorException e) {
                    logger.error("Error while processing " + articleUrl, e);
                } catch (JAXBException j) {
                    logger.error("Error while parsing article metadata response", j);
                } catch (IOException ex) {
                    logger.error("IO exception", ex);
                } catch (StorageException s) {
                    logger.error("StorageException for :" + articleUrl, s);
                }
                
            }
            nodeProcessingDAO.setNodeProcessingEnd(dbId);
        } catch (DatabaseException ex) {
            logger.error("Database exception", ex);
        }
    }
    
}
