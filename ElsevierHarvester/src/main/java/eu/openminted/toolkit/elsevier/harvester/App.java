package eu.openminted.toolkit.elsevier.harvester;

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
public class App {
    
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());
    
    public static void main(String[] args) {
        LOGGER.log(Level.INFO, "Spring boot app starts...");
        SpringApplication.run(App.class, args);
//        ApplicationContext ctx = SpringApplication.run(App.class, args);
//        String[] beanNames = ctx.getBeanDefinitionNames();
//        for (String bean : beanNames){
//            System.out.println("bean = " + bean);
//        }

    }
}
