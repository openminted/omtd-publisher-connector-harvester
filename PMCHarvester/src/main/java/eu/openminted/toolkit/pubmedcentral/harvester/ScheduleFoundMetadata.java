/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.openminted.toolkit.pubmedcentral.harvester;

import com.google.gson.Gson;
import eu.openminted.toolkit.crossref.model.multiple_works.Item;
import eu.openminted.toolkit.crossref.model.works.Link;
import eu.openminted.toolkit.queue.ScheduledArticle;
import eu.openminted.toolkit.queue.services.QueueService;
import java.util.logging.Logger;

/**
 *
 * @author samuel
 */
public class ScheduleFoundMetadata {

    private static final Logger logger = Logger.getLogger("Invoker");

    private final Gson gson;
        
    private final QueueService queueService;

    public ScheduleFoundMetadata(Gson gson, QueueService queueService) {
        this.gson = gson;
        this.queueService = queueService;
    }
    
    public ScheduleFoundMetadata(QueueService queueService) {
        this.gson = new Gson();
        this.queueService = queueService;
    }
    
    public void scheduleItem(String publisherPrefix, Item item) {

        logger.info(String.format("Scheduling (pushing to queue) item : %s", item.getDOI()));
        
        String metadata = this.gson.toJson(item);

        // field "Link" is a list of links (not just a single link)
        //item.getLink().forEach(linkItem -> System.out.println("Scheduling ...:"+linkItem.getURL()));
        for (Link link : item.getLink()) {
            ScheduledArticle article = new ScheduledArticle();
            article.setDoi(item.getdOI());
            article.setDownloadUrl(link.getURL());
            article.setPublisherPrefix(publisherPrefix);
            article.setMetadata(metadata);
            queueService.scheduleArticle(article);
        }

    }
}
