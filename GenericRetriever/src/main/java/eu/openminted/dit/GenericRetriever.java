package eu.openminted.dit;

import com.google.gson.Gson;
import eu.openminted.toolkit.database.exceptions.DatabaseException;
import eu.openminted.toolkit.database.services.GenericArticleFileDAO;
import eu.openminted.toolkit.queue.ScheduledArticle;
import eu.openminted.toolkit.storage.StorageDAO;
import eu.openminted.toolkit.storage.exceptions.StorageException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author lucasanastasiou
 */
@Component
public class GenericRetriever {

    @Autowired
    GenericArticleFileDAO genericArticleFileDAO;

    @Autowired
    GenericArticleRetrieverService genericArticleRetrieverService;

    @Autowired
    StorageDAO storageDAO;

    private final Logger logger = Logger.getLogger("GenericRetriever");

    public GenericRetriever() {
    }

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
        try {
            return genericArticleFileDAO.isArticleDownloaded(articleUrlMessage.getPublisherPrefix(), articleUrlMessage.getDoi());
        } catch (DatabaseException de) {
            logger.error("database exception", de);
            return false;
        }
    }

    private void downloadAndStore(ScheduledArticle article) throws StorageException, GenericRetrieverException {
        String fileLocation = "";
        if (article.getDownloadUrl().endsWith("xml") || article.getDownloadUrl().endsWith("html")) {
            fileLocation = storageDAO.getMetadataFileLocation(article.getPublisherPrefix(), article.getDoi(), article.getDownloadUrl());
        }else {
            fileLocation = storageDAO.getPdfFileLocation(article.getPublisherPrefix(), article.getDoi(), article.getDownloadUrl());
        }
        genericArticleRetrieverService.retrieveUrlToFile(article.getDownloadUrl(), fileLocation);
    }
}
