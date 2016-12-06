package eu.openminted.toolkit.elsevier.processor;

import eu.openminted.toolkit.database.exceptions.DatabaseException;
import eu.openminted.toolkit.database.services.NodeProcessingDAO;
import eu.openminted.toolkit.queue.ArticleUrl;
import eu.openminted.toolkit.queue.LeafNode;
import eu.openminted.toolkit.queue.services.QueueService;
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
    NodeProcessingDAO nodeProcessingDAO;

    @Autowired
    QueueService queueService;

    Logger logger = LoggerFactory.getLogger("Processor");

    public void receiveMessage(String message) {

        LeafNode leafNode = leafNodeRetrieverService.parseLeafNode(message);

        try {
            long dbId = nodeProcessingDAO.addNewNodeProcessing(leafNode.getUrl());

            List<String> articleUrls = leafNodeParser.getArticleUrls(leafNode);

            for (String articleUrl : articleUrls) {

                ArticleUrl articleUrlMessage = new ArticleUrl(articleUrl, dbId);
                queueService.pushArticleUrl(articleUrlMessage);

            }

        } catch (DatabaseException ex) {
            logger.error("Database exception", ex);
        }
    }

}
