package eu.openminted.toolkit.pubmedcentral.retriever;

import eu.openminted.toolkit.queue.services.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 */
@SpringBootApplication
public class PMCRetrieverApp implements CommandLineRunner {

    @Autowired
    QueueService queueService;
    
    public final static String queueName = "PMC-process-queue";
    
    public static void main(String args[]) {
        SpringApplication.run(PMCRetrieverApp.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        this.queueService.declareDedicatedQueue(this.queueName);
    }
}
