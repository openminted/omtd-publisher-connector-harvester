package eu.openminted.toolkit.pubmedcentral.harvester;

import java.io.IOException;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *
 * @author Samuel Pearce <samuel.pearce@open.ac.uk>
 */
@EnableAutoConfiguration
@ComponentScan(basePackages = {"eu.openminted.toolkit"})
@EnableScheduling
public class PMCHarvesterApp {

    private static final Logger LOGGER = Logger.getLogger(PMCHarvesterApp.class.getName());
    
    public static void main(String[] args) throws IOException {
        SpringApplication.run(PMCHarvesterApp.class, args).close();
    }
}