package eu.openminted.toolkit.elsevier.harvester;

import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import eu.openminted.toolkit.database.services.SitemapCrawlDAO;
import org.springframework.beans.factory.annotation.Autowired;
import eu.openminted.toolkit.elsevier.harvester.crawler.ElsevierCrawler;
import eu.openminted.toolkit.queue.services.QueueService;
import eu.openminted.toolkit.storage.StorageDAO;

/**
 *
 * @author lucasanastasiou
 */
public class CrawlerCrawlerControllerFactory implements CrawlController.WebCrawlerFactory {

    @Autowired
    SitemapCrawlDAO sitemapCrawlDAO;

    @Autowired
    QueueService queueService;

    @Autowired
    StorageDAO storageDAO;

    public CrawlerCrawlerControllerFactory() {

    }

    @Override
    public WebCrawler newInstance() {
        return new ElsevierCrawler(storageDAO, sitemapCrawlDAO, queueService); // Or new instance of your WebCrawler children class
    }
}
