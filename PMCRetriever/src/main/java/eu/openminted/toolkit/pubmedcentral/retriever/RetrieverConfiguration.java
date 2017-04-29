package eu.openminted.toolkit.pubmedcentral.retriever;

import eu.openminted.toolkit.pubmedcentral.retriever.Message.MessageEvent;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 *
 * @author lucasanastasiou
 */
@Configuration
@ComponentScan(basePackages = {"eu.openminted.toolkit"})
class RetrieverConfiguration {

    @Bean
    MessageEvent messageEvent() {
        return new MessageEvent(new Gson());
    }

    @Bean
    MessageListenerAdapter listenerAdapter(MessageEvent retriever) {
        return new MessageListenerAdapter(retriever, "receiveMessage");
    }

    public static String queueName = "PMC-download-queue";

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
            MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }
}
