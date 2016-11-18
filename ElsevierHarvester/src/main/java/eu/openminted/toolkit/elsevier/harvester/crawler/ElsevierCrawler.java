package eu.openminted.toolkit.elsevier.harvester.crawler;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import eu.openminted.toolkit.database.exceptions.DatabaseException;
import eu.openminted.toolkit.database.services.SitemapCrawlDAO;
import eu.openminted.toolkit.queue.LeafNode;
import eu.openminted.toolkit.queue.services.QueueService;
import eu.openminted.toolkit.storage.StorageDAO;
import eu.openminted.toolkit.storage.exceptions.StorageException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;


/**
 *
 * @author lucasanastasiou
 */
public class ElsevierCrawler extends WebCrawler {
    
    private static final Logger logger = Logger.getLogger(ElsevierCrawler.class.getName());
    
    private StorageDAO storageDAO;
    private SitemapCrawlDAO sitemapCrawlDAO;
    private QueueService queueService;
    
    private int currentDepth;
    private static final Pattern TITLE_LINK = Pattern.compile("(\\.*content/nonserial\\.*)");
    
    public ElsevierCrawler(StorageDAO storageDAO, SitemapCrawlDAO sitemapCrawlDAO, QueueService queueService) {
        super();
        this.storageDAO = storageDAO;
        this.sitemapCrawlDAO = sitemapCrawlDAO;
        this.queueService = queueService;
    }
    
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        this.currentDepth = url.getDepth();
        String href = url.getURL().toLowerCase();

        //do not explore links containing content/nonserial 
        if (this.currentDepth == 3 && TITLE_LINK.matcher(href).matches()) {
            return false;
        }
        
//        if (this.currentDepth == 4 && ){
//            //send to queue
//        }
        return true;
    }
    
    @Override
    public void visit(Page page) {
        int docid = page.getWebURL().getDocid();
        String url = page.getWebURL().getURL();
        String domain = page.getWebURL().getDomain();
        String path = page.getWebURL().getPath();
        String subDomain = page.getWebURL().getSubDomain();
        String parentUrl = page.getWebURL().getParentUrl();
        if (parentUrl == null) {
            parentUrl = "";
        }
        String anchor = page.getWebURL().getAnchor();
        
        logger.log(Level.INFO, "Id: {0} Visited url:{1}, from parent page:{2}", new String[]{"" + docid, url, parentUrl});
        
        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String html = htmlParseData.getHtml();
            try {
                String filenameStored = storageDAO.storeSitemapFile(docid, this.currentDepth, url, html);
                logger.log(Level.INFO, "Stored sitemap at {0}", new String[]{filenameStored});
                
                sitemapCrawlDAO.addSitemapCrawling(docid, url, parentUrl, this.currentDepth, filenameStored);
                logger.log(Level.INFO, "Logged visit to database {0} / {1} / {2}", new String[]{"" + docid, url, "" + this.currentDepth});
                
                if (this.currentDepth == 4) {
                    LeafNode leafNode = new LeafNode(filenameStored, url);
                    queueService.pushLeafNode(leafNode);
                    logger.log(Level.INFO, "Pushed leaf node for processing to queue : {0}", leafNode.toString());
                }
                
            } catch (StorageException storageException) {
                logger.log(Level.ALL, "Storage exception : " + storageException.getMessage());
            } catch (DatabaseException databaseException) {
                logger.log(Level.ALL, "Database exception : " + databaseException.getMessage());
            }
//            LOGGER.log(Level.FINE,"Page's html:{0}",new String[]{html});
//            Set<WebURL> links = htmlParseData.getOutgoingUrls();
//            links.forEach(System.out::println);
        }
//
//        Header[] responseHeaders = page.getFetchResponseHeaders();
//        if (responseHeaders != null) {
////            LOGGER.log(record);
//            for (Header header : responseHeaders) {
//                
//            }
//        }
    }
    
}
