package eu.openminted.toolkit.elsevier.harvester;

import edu.uci.ics.crawler4j.crawler.CrawlController;
import eu.openminted.toolkit.elsevier.harvester.configuration.ElsevierHarvesterConfiguration;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
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

    @Autowired
    ElsevierHarvesterConfiguration elsevierHarvesterConfiguration;

    Integer numberOfCrawlers;

    @PostConstruct
    private void init() {
        numberOfCrawlers = Integer.parseInt(elsevierHarvesterConfiguration.numberOfCrawlers);
    }

    @Override
    public void run(String... args) throws Exception {

        crawlController.start(crawlerCrawlerControllerFactory, numberOfCrawlers);

    }

}
