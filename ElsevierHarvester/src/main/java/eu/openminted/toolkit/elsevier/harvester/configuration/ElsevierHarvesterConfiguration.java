package eu.openminted.toolkit.elsevier.harvester.configuration;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import eu.openminted.toolkit.elsevier.harvester.CrawlerCrawlerControllerFactory;
import eu.openminted.toolkit.elsevier.harvester.crawler.ElsevierRobotsServer;

/**
 *
 * @author lucasanastasiou
 */
@Configuration
@ComponentScan(basePackages = {"eu.openminted.elsevier.harvester"})
public class ElsevierHarvesterConfiguration {
    
    @Bean
    public CrawlerCrawlerControllerFactory crawlerCrawlerControllerFactory() {
        CrawlerCrawlerControllerFactory factory = new CrawlerCrawlerControllerFactory();
        return factory;
    }
    
    @Bean
    public CrawlController crawler() throws Exception {

        // Configure CrawController accordingly...
        // configure elsevier crawler
        int numberOfCrawlers = 1;
        String crawlStorageFolder = "./storage";
        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
        config.setPolitenessDelay(25);
        
        config.setMaxDepthOfCrawling(3);

//        config.setMaxPagesToFetch(1000);
        config.setIncludeBinaryContentInCrawling(false);
        config.setProxyHost("wwwcache.open.ac.uk");
        config.setProxyPort(80);
        config.setResumableCrawling(true);
        
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
//        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        ElsevierRobotsServer elsevierRobotsServer = new ElsevierRobotsServer();
        
        CrawlController controller = new CrawlController(config, pageFetcher, elsevierRobotsServer);
//        controller.addSeed("http://api.elsevier.com/sitemap/page/sitemap/index.html");

        controller.addSeed("http://api.elsevier.com/sitemap/page/sitemap/m.html");

//    CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
//        controller.startNonBlocking(factory, numberOfCrawlers);
        return controller;
    }
}
