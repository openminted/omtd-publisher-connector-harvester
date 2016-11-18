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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @author lucasanastasiou
 */
@Configuration
@ComponentScan(basePackages = {"eu.openminted.toolkit.elsevier.harvester"})
@PropertySource(value = {"classpath:elsevier-harvester.properties"})
public class ElsevierHarvesterConfiguration {

    @Value("${elsevier.harvester.numberOfCrawlers:'1'}")
    public String numberOfCrawlers;

    @Value("${elsevier.harvester.crawlStorageFolder:'./storage'}")
    private String crawlStorageFolder;

    @Value("${elsevier.harvester.politenessDelay:'25'}")
    private String politenessDelay;

    @Value("${elsevier.harvester.maxDepthOfCrawling:'4'}")
    private String maxDepthOfCrawling;

    @Value("${elsevier.harvester.resumableCrawling:'true'}")
    private String resumableCrawling;

    @Value("${elsevier.harvester.seed:'http://api.elsevier.com/sitemap/page/sitemap/index.html'}")
    private String seed;
    @Value("${elsevier.harvester.proxyHost}")
    private String proxyHost;
    @Value("${elsevier.harvester.proxyPort}")
    private String proxyPort;

    @Bean
    public CrawlerCrawlerControllerFactory crawlerCrawlerControllerFactory() {
        CrawlerCrawlerControllerFactory factory = new CrawlerCrawlerControllerFactory();
        return factory;
    }

    @Bean
    public CrawlController crawler() throws Exception {

        // Configure elsevier crawler
        CrawlConfig config = new CrawlConfig();

        config.setCrawlStorageFolder(this.crawlStorageFolder);
        config.setPolitenessDelay(Integer.parseInt(this.politenessDelay));
        config.setMaxDepthOfCrawling(Integer.parseInt(this.maxDepthOfCrawling));
        config.setIncludeBinaryContentInCrawling(false);
        if (this.proxyHost != null && !this.proxyHost.isEmpty() && this.proxyPort != null && !this.proxyPort.isEmpty()) {
            config.setProxyHost(this.proxyHost);
            config.setProxyPort(Integer.parseInt(this.proxyPort));
        }
        config.setResumableCrawling(true);

        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        ElsevierRobotsServer elsevierRobotsServer = new ElsevierRobotsServer();

        CrawlController controller = new CrawlController(config, pageFetcher, elsevierRobotsServer);
        controller.addSeed(this.seed);

        return controller;
    }
}
