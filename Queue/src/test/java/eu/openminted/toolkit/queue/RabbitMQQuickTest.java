package eu.openminted.toolkit.queue;

import com.google.gson.Gson;
import eu.openminted.toolkit.queue.LeafNode;
import eu.openminted.toolkit.queue.QueueConstants;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

/**
 *
 * @author lucasanastasiou
 */
public class RabbitMQQuickTest implements CommandLineRunner {

    @Autowired
    RabbitTemplate rabbitTemplate;
    
    @Override
    public void run(String... strings) throws Exception {
        System.out.println("Testing rabbitmq sending and receiving");
        
        String msg= "{\"filename\":\"L4_httpapielseviercomsitemappagesitemapserialjournalsw18715192Volume_29_Issue_5html_f0779ddf54d083ca7d0f9394828bef5e54446e03.sitemap.xml\",\"url\":\"http://api.elsevier.com/sitemap/page/sitemap/serial/journals/w/18715192/Volume_29_Issue_5.html\"}";
        
        String filename = "L4_httpapielseviercomsitemappagesitemapserialjournalsw18715192Volume_29_Issue_5html_f0779ddf54d083ca7d0f9394828bef5e54446e03.sitemap.xml";
        String url = "http://api.elsevier.com/sitemap/page/sitemap/serial/journals/w/18715192/Volume_29_Issue_5.html";
        LeafNode leafNode = new LeafNode(filename, url);
        
        Gson gson = new Gson();
        String message = gson.toJson(leafNode);

        rabbitTemplate.convertAndSend(QueueConstants.EXCHANGE_NAME, QueueConstants.ROUTING_KEY, message);
        
        Object received = rabbitTemplate.receiveAndConvert(QueueConstants.QUEUE_NAME);
        
        String recievedString = (String) received;
        System.out.println("recievedString = " + recievedString);
        
        LeafNode receivedLeafNode = gson.fromJson(recievedString, LeafNode.class);
        System.out.println("receivedLeafNode = " + receivedLeafNode.toString());
        
        
    }
    
    public static void main(String[] args) {
        SpringApplication.run(RabbitMQQuickTest.class, args);
    }
}
