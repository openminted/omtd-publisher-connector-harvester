package eu.openminted.toolkit.elsevier.processor;

import com.google.gson.Gson;
import eu.openminted.toolkit.database.exceptions.DatabaseException;
import eu.openminted.toolkit.database.model.CrawlVisit;
import eu.openminted.toolkit.database.services.SitemapCrawlDAO;
import eu.openminted.toolkit.queue.LeafNode;
import eu.openminted.toolkit.queue.services.QueueService;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucasanastasiou
 */
@Service
public class LeafNodeRetrieverService {

    @Autowired
    SitemapCrawlDAO sitemapCrawlDAO;
    
    @Autowired
    QueueService queueService;

    private final Logger logger = Logger.getLogger(LeafNodeRetrieverService.class);

    public CrawlVisit retrieveLeafNode() {
        List<CrawlVisit> links = retrieveLeafNodes(1);
        return (links != null && !links.isEmpty()) ? links.get(0) : null;
    }

    public List<CrawlVisit> retrieveLeafNodes(int howMany) {
        try {
            return sitemapCrawlDAO.getCrawlVisitsByLevel(4, 1);
        } catch (DatabaseException databaseException) {
            logger.error("Cannot retireve leaf nodes from database");
            return null;
        }
    }
    
    /*
    Deserialise queued message to LeafNode
    */
    public LeafNode parseLeafNode(String message) {
        Gson gson = new Gson();
        LeafNode leafNode = gson.fromJson(message, LeafNode.class);
        return leafNode;
    }
}
