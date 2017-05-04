package eu.openminted.toolkit.pubmedcentral.retriever;

import eu.openminted.toolkit.pubmedcentral.retriever.Message.MessageEvent;
import com.google.gson.Gson;
import eu.openminted.dit.GenericArticleRetrieverService;
import eu.openminted.toolkit.database.services.GenericArticleFileDAO;
import eu.openminted.toolkit.pubmedcentral.retriever.Message.MessageEventCallback;
import eu.openminted.toolkit.storage.StorageDAO;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.annotation.PreDestroy;
import org.slf4j.LoggerFactory;
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
class RetrieverConfiguration {
    
    // Service not registered by componentscan
    @Bean
    GenericArticleRetrieverService genericArticleRetrieverService() {
        return new GenericArticleRetrieverService();    
    }
    
    @Bean
    ExecutorService executorService() {
        return Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());    
    }
    
    @Bean
    ExecutorShutdownHook executorShutdown(ExecutorService executorService) {
        return new ExecutorShutdownHook(executorService);
    }
    
    
    @Bean
    MessageEventCallback messageCallback(StorageDAO storageDOA, GenericArticleFileDAO genericArticleFileDAO, GenericArticleRetrieverService genericArticleRetrieverService, ExecutorService executorService) {
        return new HandlePMCUpdates(storageDOA, genericArticleFileDAO, genericArticleRetrieverService, executorService);
    }
    
    @Bean
    MessageEvent messageEvent(MessageEventCallback callback) {
        return new MessageEvent(new Gson(), callback);
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
        container.setPrefetchCount(50);
        container.setMessageListener(listenerAdapter);        
        return container;
    }
}
