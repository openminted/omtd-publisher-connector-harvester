package eu.openminted.dit;

import eu.openminted.toolkit.queue.QueueConstants;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author lucasanastasiou
 */
@Configuration
@ComponentScan(basePackages = {"eu.openminted.toolkit"})
public class GenericRetrieverConfiguration {

    @Bean
    GenericRetriever processor() {
        return new GenericRetriever();
    }

    @Bean
    MessageListenerAdapter listenerAdapter(GenericRetriever retriever) {
        return new MessageListenerAdapter(retriever, "receiveMessage");
    }

    //
    // make queue list injectable
    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
            MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames(QueueConstants.SCHEDULED_ARTICLES_QUEUE_NAME);
        container.setQueueNames("Springer-OA-download-queue");
        container.setMessageListener(listenerAdapter);
        return container;
    }
}
