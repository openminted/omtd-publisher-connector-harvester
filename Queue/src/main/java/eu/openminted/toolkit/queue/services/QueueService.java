package eu.openminted.toolkit.queue.services;

import eu.openminted.toolkit.queue.ArticleUrl;
import eu.openminted.toolkit.queue.LeafNode;
import eu.openminted.toolkit.queue.ScheduledArticle;

/**
 *
 * @author lucasanastasiou
 */
public interface QueueService {
    
    public void pushLeafNode(LeafNode leafNode);
    
    public LeafNode getLeafNode();
    
    public void pushArticleUrl(ArticleUrl articleUrl);
    
    public ArticleUrl getArticleUrl();
    
    public void scheduleArticle(ScheduledArticle scheduledArticle);
}
