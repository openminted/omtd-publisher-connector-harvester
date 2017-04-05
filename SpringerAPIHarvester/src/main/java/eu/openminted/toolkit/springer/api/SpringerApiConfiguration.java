package eu.openminted.toolkit.springer.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @author lucasanastasiou
 */
@Configuration
@ComponentScan(basePackages = {"eu.openminted.springer.api"})
@PropertySource(value = {"classpath:application.properties"})
public class SpringerApiConfiguration {

    @Value("${springer.api_key}")
    public String API_KEY;
    
}
