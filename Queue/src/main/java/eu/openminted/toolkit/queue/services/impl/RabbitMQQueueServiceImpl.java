package eu.openminted.toolkit.queue.services.impl;

import com.google.gson.Gson;
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
}
