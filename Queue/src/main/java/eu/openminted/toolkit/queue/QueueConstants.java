package eu.openminted.toolkit.queue;

/**
 *
 * @author lucasanastasiou
 */
public class QueueConstants {

    public final static String QUEUE_NAME = "elsevier_queue";
    public final static String ARTICLES_URLS_QUEUE_NAME = "elsevier_articles_queue";
    public final static String EXCHANGE_NAME = "elsevier_exchange";
    public final static String ROUTING_KEY = "elsevier_routing_key";
    public final static String ARTICLES_ROUTING_KEY = "elsevier_articles_routing_key";
    
    public final static String SCHEDULED_ARTICLES_EXCHANGE = "publisher-connector-exchange";
    public final static String SCHEDULED_ARTICLES_QUEUE_NAME = "publisher-connector-download-articles_queue";
    public final static String SCHEDULED_ARTICLES_ROUTING_KEY = "publisher-connector-routing-key";
    
    public final static String DEDICATED_QUEUES_EXCHANGE_NAME = "dedicated-queue-exchange";
}
