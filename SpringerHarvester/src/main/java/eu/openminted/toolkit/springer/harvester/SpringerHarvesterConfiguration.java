package eu.openminted.toolkit.springer.harvester;

import eu.openminted.toolkit.springer.api.SpringerAPIClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author lucasanastasiou
 */
@Configuration
@ComponentScan(basePackages = {"eu.openminted.toolkit"})
public class SpringerHarvesterConfiguration {

    @Bean
    SpringerAPIClient springerAPIClient(){
        return new SpringerAPIClient("fdcc8721a1359724c6260b04d2ced9b2");
    }
    
}
