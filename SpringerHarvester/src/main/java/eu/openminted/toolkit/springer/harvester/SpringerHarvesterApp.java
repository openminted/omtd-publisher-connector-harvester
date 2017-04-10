package eu.openminted.toolkit.springer.harvester;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *
 * @author lucasanastasiou
 */
@EnableAutoConfiguration
@ComponentScan(basePackages = {"eu.openminted.toolkit"})
@EnableScheduling
public class SpringerHarvesterApp {

    private static final Logger LOGGER = Logger.getLogger(SpringerHarvesterApp.class.getName());
    
    public static void main(String[] args) {

        SpringApplication.run(SpringerHarvesterApp.class, args);


    }
}