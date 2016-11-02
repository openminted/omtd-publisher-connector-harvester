package eu.openminted.toolkit.elsevier.harvester;

import java.text.SimpleDateFormat;
import java.util.logging.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author lucasanastasiou
 */
@Component
public class HarvesterController {

    private static final Logger LOGGER = Logger.getLogger(HarvesterController.class.getName());
    
    @Autowired
    RabbitTemplate rabbitTemplate;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

//    @Scheduled(fixedRate = 10000)//10 seconds
//    public void reportCurrentTime() {
//        String message = "The time is now {" + dateFormat.format(new Date()) + "}";
//        System.out.println(message);
//        rabbitTemplate.convertAndSend(ElsevierHarvesterConstants.EXCHANGE_NAME, ElsevierHarvesterConstants.ROUTING_KEY, message);
//    }
}
