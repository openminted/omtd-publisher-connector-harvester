package eu.openminted.toolkit.elsevier.processor;

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
public class ProcessorConfiguration {

    @Bean
    Processor processor(){
        return new Processor();
    }
    
    @Bean
    MessageListenerAdapter listenerAdapter(Processor receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }
    
        @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
            MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QueueConstants.QUEUE_NAME);
        container.setMessageListener(listenerAdapter);
        return container;
    }
}
