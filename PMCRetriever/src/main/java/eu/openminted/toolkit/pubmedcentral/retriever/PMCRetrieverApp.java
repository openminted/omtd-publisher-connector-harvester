package eu.openminted.toolkit.pubmedcentral.retriever;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author lucasanastasiou
 */
@SpringBootApplication
public class PMCRetrieverApp implements CommandLineRunner {

    public static void main(String args[]) {
        SpringApplication.run(PMCRetrieverApp.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
    }
}
