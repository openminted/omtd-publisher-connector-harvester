//package eu.openminted.toolkit;
//
//import eu.openminted.toolkit.database.exceptions.DatabaseException;
//import eu.openminted.toolkit.database.model.CrawlVisit;
//import eu.openminted.toolkit.database.services.SitemapCrawlDAO;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.context.ApplicationContext;
//import org.springframework.jdbc.core.JdbcTemplate;
//
///**
// *
// * @author lucasanastasiou
// */
//public class DatabaseQuickTest implements CommandLineRunner {
//
//    @Autowired
//    JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    SitemapCrawlDAO sitemapCrawlDAO;
//
//    @Override
//    public void run(String... args) throws Exception {
////        SqlRowSet result = jdbcTemplate.queryForRowSet("SELECT UNIX_TIMESTAMP()");
////        java.sql.Timestamp time = result.getTimestamp(1);
////        System.out.println("time = " + time);
////        jdbcTemplate.execute("CREATE TABLE ttt (id INT,name VARCHAR(8))");
//        try {
//            sitemapCrawlDAO.addSitemapCrawling(5, "my_url", "my_parent_url", -1, "my_filename");
//        } catch (DatabaseException ex) {
//            Logger.getLogger(DatabaseQuickTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        CrawlVisit c = sitemapCrawlDAO.getSitemapByUrl("my_url");
//
//        System.out.println("c = " + c);
//
//    }
//
//    public void doIt() {
//
//    }
//
//    public static void main(String args[]) {
//        ApplicationContext app = SpringApplication.run(DatabaseQuickTest.class, args);
////        DatabaseQuickTest d=new DatabaseQuickTest();
////        d.doIt();
//    }
//
//}
