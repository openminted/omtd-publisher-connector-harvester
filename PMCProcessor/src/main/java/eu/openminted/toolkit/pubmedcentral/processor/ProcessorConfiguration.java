package eu.openminted.toolkit.pubmedcentral.processor;

import eu.openminted.toolkit.pubmedcentral.retriever.Message.MessageEvent;
import com.google.gson.Gson;
import eu.openminted.dit.GenericArticleRetrieverService;
import eu.openminted.toolkit.database.services.GenericArticleFileDAO;
import eu.openminted.toolkit.pubmedcentral.retriever.Message.MessageEventCallback;
import eu.openminted.toolkit.queue.services.QueueService;
import eu.openminted.toolkit.storage.StorageDAO;
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
class ProcessorConfiguration {
    
    // Service not registered by componentscan
    @Bean
    GenericArticleRetrieverService genericArticleRetrieverService() {
        return new GenericArticleRetrieverService();    
    }    
    
    @Bean
    MessageEventCallback messageCallback(StorageDAO storageDAO, GenericArticleFileDAO genericArticleFileDAO , GenericArticleRetrieverService genericArticleRetrieverService) {
        return new HandlePMCArchiveDownload(storageDAO, genericArticleFileDAO, genericArticleRetrieverService);
    }
    
    @Bean
    MessageEvent messageEvent(MessageEventCallback callback) {
        return new MessageEvent(new Gson(), callback);
    }

    @Bean
    MessageListenerAdapter listenerAdapter(MessageEvent retriever) {
        return new MessageListenerAdapter(retriever, "receiveMessage");
    }

    public static String queueName = "PMC-process-queue";

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
            MessageListenerAdapter listenerAdapter) {        
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setPrefetchCount(2);
        container.setMessageListener(listenerAdapter);        
        return container;
    }
}