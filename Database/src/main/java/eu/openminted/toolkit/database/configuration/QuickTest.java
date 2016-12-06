//package eu.openminted.toolkit.database.configuration;
//
//import eu.openminted.toolkit.database.exceptions.DatabaseException;
//import eu.openminted.toolkit.database.model.CrawlVisit;
//import eu.openminted.toolkit.database.services.ArticleFilesDAO;
//import eu.openminted.toolkit.database.services.NodeProcessingDAO;
//import eu.openminted.toolkit.database.services.SitemapCrawlDAO;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.ApplicationContext;
//import org.springframework.jdbc.core.JdbcTemplate;
//
///**
// *
// * @author lucasanastasiou
// */
//@SpringBootApplication
//public class QuickTest implements CommandLineRunner {
//
//    @Autowired
//    JdbcTemplate jdbcTemplate;
//    
//    @Autowired
//    NodeProcessingDAO nodeProcessingDAO;
//
//    @Autowired
//    SitemapCrawlDAO sitemapCrawlDAO;
//    
//    @Autowired
//    ArticleFilesDAO articleFilesDAO;
//
//    @Override
//    public void run(String... args) throws Exception {
////        SqlRowSet result = jdbcTemplate.queryForRowSet("SELECT UNIX_TIMESTAMP()");
////        java.sql.Timestamp time = result.getTimestamp(1);
////        System.out.println("time = " + time);
////        jdbcTemplate.execute("CREATE TABLE ttt (id INT,name VARCHAR(8))");
//        try {
//            sitemapCrawlDAO.addSitemapCrawling(5, "my_url", "my_parent_url", -1, "my_filename_2");
//        } catch (DatabaseException ex) {
//            Logger.getLogger(QuickTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        CrawlVisit c = sitemapCrawlDAO.getSitemapByUrl("my_url");
//
//        System.out.println("c = " + c);
//        
//        
//        CrawlVisit c2 = sitemapCrawlDAO.getSitemapByUrl("my_url_2");
//
//        System.out.println("c2 = " + c2);
//        
//        
//        long dbId = nodeProcessingDAO.addNewNodeProcessing("my_url");
//        System.out.println("dbId = " + dbId);
//
//        articleFilesDAO.insertNewArticleFiles(dbId, "my_doi");
//        articleFilesDAO.setMetadataFilename(dbId, "my_doi", "a meta path");
//        articleFilesDAO.setPdfFilename(dbId, "my_doi", "a pdf path");
//        
//        
//    }
//
//    public void doIt() {
//
//    }
//
//    public static void main(String args[]) {
//        ApplicationContext app = SpringApplication.run(QuickTest.class, args);
////        DatabaseQuickTest d=new DatabaseQuickTest();
////        d.doIt();
//    }
//}