package eu.openminted.toolkit.database.configuration;

import com.jolbox.bonecp.BoneCPDataSource;
import java.util.UUID;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

/**
 *
 * @author lucasanastasiou
 */
@Configuration
@ComponentScan(basePackages = {"eu.openminted.toolkit"})
@PropertySource(value = {"classpath:database.properties"})
public class DatabaseConfiguration {

    @Value("${init-db:false}")
    private String initDatabase;

    @Value("${bonecp.url}")
    private String jdbcUrl;

    @Value("${bonecp.username}")
    private String jdbcUsername;

    @Value("${bonecp.password}")
    private String jdbcPassword;

    @Value("${bonecp.driverClass}")
    private String driverClass;

    @Value("${bonecp.idleMaxAgeInMinutes}")
    private Integer idleMaxAgeInMinutes;

    @Value("${bonecp.idleConnectionTestPeriodInMinutes}")
    private Integer idleConnectionTestPeriodInMinutes;

    @Value("${bonecp.maxConnectionsPerPartition}")
    private Integer maxConnectionsPerPartition;

    @Value("${bonecp.minConnectionsPerPartition}")
    private Integer minConnectionsPerPartition;

    @Value("${bonecp.partitionCount}")
    private Integer partitionCount;

    @Value("${bonecp.acquireIncrement}")
    private Integer acquireIncrement;

    @Value("${bonecp.statementsCacheSize}")
    private Integer statementsCacheSize;

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    @Bean
    public DataSourceInitializer dataSourceInitializer(DataSource dataSource){
        if (Boolean.parseBoolean(initDatabase) == true) {
            DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
            dataSourceInitializer.setEnabled(true);
            dataSourceInitializer.setDataSource(dataSource);
            ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
            databasePopulator.addScript(new ClassPathResource("schema.sql"));
            dataSourceInitializer.setDatabasePopulator(databasePopulator);
            return dataSourceInitializer;
        }
        return null;
    }

    @Bean
    public DataSource dataSource() {
        BoneCPDataSource dataSource = new BoneCPDataSource();
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUsername(jdbcUsername);
        dataSource.setPassword(jdbcPassword);
        String randomPoolName = UUID.randomUUID().toString();
        System.out.println("randomPoolName = " + randomPoolName);
        dataSource.setPoolName(randomPoolName);
        dataSource.setIdleConnectionTestPeriodInMinutes(idleConnectionTestPeriodInMinutes);
        dataSource.setIdleMaxAgeInMinutes(idleMaxAgeInMinutes);
        dataSource.setMaxConnectionsPerPartition(maxConnectionsPerPartition);
        dataSource.setMinConnectionsPerPartition(minConnectionsPerPartition);
        dataSource.setPartitionCount(partitionCount);
        dataSource.setAcquireIncrement(acquireIncrement);
        dataSource.setStatementsCacheSize(statementsCacheSize);
        System.out.println("dataSource = " + dataSource);

        
        return dataSource;
    }

}
