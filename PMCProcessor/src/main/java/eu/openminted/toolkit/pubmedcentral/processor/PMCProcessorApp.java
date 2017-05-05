package eu.openminted.toolkit.pubmedcentral.processor;

import eu.openminted.toolkit.queue.services.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 */
@SpringBootApplication
public class PMCProcessorApp implements CommandLineRunner {

    @Autowired
    QueueService queueService;
   
    public static void main(String args[]) {
        SpringApplication.run(PMCProcessorApp.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        this.queueService.declareDedicatedQueue(ProcessorConfiguration.queueName);
    }
}
