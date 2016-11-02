package eu.openminted.toolkit.elsevier.processor;

import eu.openminted.toolkit.database.exceptions.DatabaseException;
import eu.openminted.toolkit.database.model.CrawlVisit;
import eu.openminted.toolkit.database.services.SitemapCrawlDAO;
import eu.openminted.toolkit.storage.StorageDAO;
import eu.openminted.toolkit.storage.exceptions.FileDoesNotExistException;
import eu.openminted.toolkit.storage.exceptions.StorageException;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author lucasanastasiou
 */
public class LeafNodeRetrieverService {

    @Autowired
    SitemapCrawlDAO sitemapCrawlDAO;

    @Autowired
    StorageDAO storageDAO;

    private final Logger logger = Logger.getLogger(LeafNodeRetrieverService.class);

    public List<CrawlVisit> retrieveLeafNodes(int howMany) {
        try {
            return sitemapCrawlDAO.getCrawlVisitsByLevel(4, 1);
        } catch (DatabaseException databaseException) {
            logger.error("Cannot retireve leaf nodes from database");
            return null;
        }
    }

    public String getFileContents(String filename) {
        try {
            String contents = storageDAO.getFileContents(filename);
            return contents;
        } catch (StorageException exception) {
            logger.error("Cannot get contents of file " + filename,  exception);
            return null;
        }
    }
}
