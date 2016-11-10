package eu.openminted.toolkit.elsevier.processor;

import eu.openminted.toolkit.database.exceptions.DatabaseException;
import eu.openminted.toolkit.database.model.CrawlVisit;
import eu.openminted.toolkit.database.services.SitemapCrawlDAO;
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
}
