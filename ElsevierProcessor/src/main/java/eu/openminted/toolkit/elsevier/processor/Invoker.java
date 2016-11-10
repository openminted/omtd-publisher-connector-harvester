package eu.openminted.toolkit.elsevier.processor;

import eu.openminted.toolkit.database.model.CrawlVisit;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author lucasanastasiou
 */
@Component
public class Invoker implements CommandLineRunner {

    @Autowired
    LeafNodeRetrieverService leafNodeRetrieverService;
    
    @Autowired
    LeafNodeParser leafNodeParser;
    
    @Override
    public void run(String... strings) throws Exception {
        CrawlVisit crawlVisit = leafNodeRetrieverService.retrieveLeafNode();
        System.out.println("crawlVisit = " + crawlVisit);
        List<String> articleUrls = leafNodeParser.getArticleUrls(crawlVisit);
        
        articleUrls.forEach(System.out::println);
    }

}
