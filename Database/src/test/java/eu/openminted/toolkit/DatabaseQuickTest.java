package eu.openminted.toolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

/**
 *
 * @author lucasanastasiou
 */
public class DatabaseQuickTest  implements CommandLineRunner {

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Override
    public void run(String... args) throws Exception {
        SqlRowSet result = jdbcTemplate.queryForRowSet("SELECT UNIX_TIMESTAMP()");
        java.sql.Timestamp time = result.getTimestamp(1);
        System.out.println("time = " + time);
        jdbcTemplate.execute("CREATE TABLE ttt (id INT,name VARCHAR(8))");
    }
    
}