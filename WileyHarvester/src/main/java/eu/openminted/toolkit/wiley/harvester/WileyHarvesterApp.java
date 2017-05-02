package eu.openminted.toolkit.wiley.harvester;

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
public class WileyHarvesterApp {

    private static final Logger LOGGER = Logger.getLogger(WileyHarvesterApp.class.getName());
    
    public static void main(String[] args) throws IOException {
        SpringApplication.run(WileyHarvesterApp.class, args);
    }
}