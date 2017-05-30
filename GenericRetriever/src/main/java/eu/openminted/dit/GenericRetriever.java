package eu.openminted.dit;

import com.google.gson.Gson;
import eu.openminted.toolkit.database.exceptions.DatabaseException;
import eu.openminted.toolkit.database.services.GenericArticleFileDAO;
import eu.openminted.toolkit.queue.ScheduledArticle;
import eu.openminted.toolkit.storage.StorageDAO;
import eu.openminted.toolkit.storage.exceptions.StorageException;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author lucasanastasiou
 */
public class GenericRetriever {

    @Autowired
    GenericArticleFileDAO genericArticleFileDAO;

    @Autowired
    GenericArticleRetrieverService genericArticleRetrieverService;

    @Autowired
    StorageDAO storageDAO;

    private final Logger logger = Logger.getLogger("GenericRetriever");

    public void receiveMessage(String message) {
        try {
            ScheduledArticle articleUrlMessage = parseArticleUrlFromMessage(message);

            if (!alreadyDownloaded(articleUrlMessage)) {
                downloadAndStore(articleUrlMessage);
            }

        } catch (Exception e) {
            logger.error(e);
            System.out.println("e = " + e);
        }
    }

    private ScheduledArticle parseArticleUrlFromMessage(String message) {
        Gson gson = new Gson();
        ScheduledArticle article = gson.fromJson(message, ScheduledArticle.class);
        return article;
    }

    private boolean alreadyDownloaded(ScheduledArticle articleUrlMessage) {
        String publisherPrefix = articleUrlMessage.getPublisherPrefix();

        try {
            return genericArticleFileDAO.isArticleDownloaded(publisherPrefix, articleUrlMessage.getDoi());
        } catch (DatabaseException de) {
            logger.error("database exception", de);
            return false;
        }
    }

    private void downloadAndStore(ScheduledArticle article) throws StorageException, GenericRetrieverException, DatabaseException {
        if (article.getDownloadUrl() == null && article.getPublisherPrefix().equals("Springer-OA")) {
            article.setDownloadUrl("http://link.springer.com/" + article.getDoi() + ".pdf");
        }
        if (article.getDownloadUrl() == null && article.getPublisherPrefix().equals("Frontiers")){
            article.setDownloadUrl("http://journal.frontiersin.org/article/"+article.getDoi()+"/pdf");
        }
        String metaFileLocation = "";
        String pdfFileLocation = "";
//        if (article.getDownloadUrl().endsWith("xml") || article.getDownloadUrl().endsWith("html")) {
        metaFileLocation = storageDAO.getMetadataFileLocation(article.getPublisherPrefix(), article.getDoi(), article.getDownloadUrl(),"json");
//        } else {
        pdfFileLocation = storageDAO.getPdfFileLocation(article.getPublisherPrefix(), article.getDoi(), article.getDownloadUrl());
//        }

        genericArticleFileDAO.insertNewArticle(article.getPublisherPrefix(), article.getDoi(), article.getMetadata());

        if (article.getDownloadUrl().endsWith("xml") || article.getDownloadUrl().endsWith("html")) {
            genericArticleRetrieverService.retrieveUrlToFile(article.getDownloadUrl(), metaFileLocation);
        } else {
            genericArticleRetrieverService.retrieveUrlToFile(article.getDownloadUrl(), pdfFileLocation);
            try {
                storageDAO.storeFile(metaFileLocation, article.getMetadata());
            }catch (IOException ioe){
                logger.error("Cannot store metadata file to :"+metaFileLocation);
            }
        }

        genericArticleFileDAO.updateMetadata(article.getDoi(), article.getMetadata());
//        if (article.getDownloadUrl().endsWith("xml")
//                || article.getDownloadUrl().endsWith("html")) {
        genericArticleFileDAO.updateMetaFileLocation(article.getDoi(), metaFileLocation);

//        } else {
        genericArticleFileDAO.updatePdfFileLocation(article.getDoi(), pdfFileLocation);
//        }

    }
}
