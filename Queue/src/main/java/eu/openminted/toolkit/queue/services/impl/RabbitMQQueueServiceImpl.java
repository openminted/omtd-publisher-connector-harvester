package eu.openminted.toolkit.queue.services.impl;

import com.google.gson.Gson;
import eu.openminted.toolkit.queue.ArticleUrl;
import eu.openminted.toolkit.queue.LeafNode;
import eu.openminted.toolkit.queue.QueueConstants;
import eu.openminted.toolkit.queue.services.QueueService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucasanastasiou
 */
@Service
public class RabbitMQQueueServiceImpl implements QueueService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void pushLeafNode(LeafNode leafNode) {
        Gson gson = new Gson();
        String message = gson.toJson(leafNode);
        rabbitTemplate.convertAndSend(QueueConstants.EXCHANGE_NAME, QueueConstants.ROUTING_KEY, message);
    }

    @Override
    public LeafNode getLeafNode() {

        Object msg = rabbitTemplate.receiveAndConvert(QueueConstants.QUEUE_NAME);
        String msgString = new String((byte[]) msg);
        Gson gson = new Gson();
        LeafNode leafNode = gson.fromJson(msgString, LeafNode.class);
        return leafNode;
    }

    @Override
    public void pushArticleUrl(ArticleUrl articleUrl) {
        Gson gson = new Gson();
        String message = gson.toJson(articleUrl);
        rabbitTemplate.convertAndSend(QueueConstants.EXCHANGE_NAME, QueueConstants.ARTICLES_ROUTING_KEY, message);
    }

    @Override
    public ArticleUrl getArticleUrl() {
        Object msg = rabbitTemplate.receiveAndConvert(QueueConstants.ARTICLES_URLS_QUEUE_NAME);
        String msgString = new String((byte[]) msg);
        Gson gson = new Gson();
        ArticleUrl articleUrl = gson.fromJson(msgString, ArticleUrl.class);
        return articleUrl;
    }
}
