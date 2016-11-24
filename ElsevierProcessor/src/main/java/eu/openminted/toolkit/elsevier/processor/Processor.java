package eu.openminted.toolkit.elsevier.processor;

import eu.openminted.toolkit.elsevier.processor.exceptions.ElsevierProcessorException;
import eu.openminted.toolkit.queue.LeafNode;
import eu.openminted.toolkit.storage.StorageDAO;
import eu.openminted.toolkit.storage.exceptions.StorageException;
import java.util.List;
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

    Logger logger = LoggerFactory.getLogger("Processor");

    public void receiveMessage(String message) {

        LeafNode leafNode = leafNodeRetrieverService.parseLeafNode(message);

        List<String> articleUrls = leafNodeParser.getArticleUrls(leafNode);

        for (String articleUrl : articleUrls) {
            try {
                String articleMetadata = articleRetrieverService.retrieveArticleMetadata(articleUrl);
                try {
                    String mFilename = storageDAO.storeMetadataFile(articleUrl, articleMetadata);
                } catch (StorageException s) {
                    logger.error("Cannot store metadata file for :" + articleUrl, s);
                }
                if (articleMetadataParser.isOpenAccess(articleMetadata)) {
                    String articlePdf = articleRetrieverService.retrieveArticlePdf(articleUrl);
                    try {
                        storageDAO.storePdfFile(articleUrl, articlePdf);
                    } catch (StorageException st) {
                        logger.error("Cannot store pdf file for :" + articleUrl, st);
                    }
                }

            } catch (ElsevierProcessorException e) {
                logger.error("Error while processing "+articleUrl, e);
            }
        }

//        articleUrls.forEach(System.out::println);
    }

}
