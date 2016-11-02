package eu.openminted.toolkit.elsevier.harvester;

import edu.uci.ics.crawler4j.crawler.CrawlController;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author lucasanastasiou
 */
@Component
public class Invoker implements CommandLineRunner {

    private static final Logger LOGGER = Logger.getLogger(Invoker.class.getName());

    @Autowired
    CrawlController crawlController;
    
    @Autowired
    CrawlerCrawlerControllerFactory crawlerCrawlerControllerFactory;

    @Override
    public void run(String... args) throws Exception {
        
        crawlController.start(crawlerCrawlerControllerFactory,1);

    }

}
