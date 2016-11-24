package eu.openminted.toolkit.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @author lucasanastasiou
 */
@Configuration
@ComponentScan(basePackages = {"eu.openminted.toolkit"})
@PropertySource(value = {"classpath:storage.properties"})
public class StorageConfiguration {

    @Value("${STORAGE_BASE_PATH}")
    public String STORAGE_BASE_PATH;
    
    
    @Value("${STORAGE_SITEMAPS_PATH}")
    public String STORAGE_SITEMAPS_PATH;
    
    @Value("${STORAGE_METADATA_PATH}")
    public String STORAGE_METADATA_PATH;
    
    @Value("${STORAGE_PDF_PATH}")
    public String STORAGE_PDF_PATH;
}
