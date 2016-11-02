package eu.openminted.toolkit.elsevier.processor;

import eu.openminted.toolkit.database.services.SitemapCrawlDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author lucasanastasiou
 */
public class LeafNodeRetrieverService {

    @Autowired
    SitemapCrawlDAO sitemapCrawlDAO;
    
    public List<LeafNode> retrieveLeafNodes(int howMany){
        
    }
}
