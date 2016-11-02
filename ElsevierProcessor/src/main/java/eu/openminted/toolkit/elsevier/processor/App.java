package eu.openminted.toolkit.elsevier.processor;

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
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
