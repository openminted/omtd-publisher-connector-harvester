/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.openminted.toolkit.wiley.harvester;

import com.jcabi.http.request.JdkRequest;
import eu.openminted.toolkit.database.exceptions.DatabaseException;
import eu.openminted.toolkit.database.services.GenericArticleFileDAO;
import eu.openminted.toolkit.pubmedcentral.retriever.Message.MessageEventCallback;
import eu.openminted.toolkit.queue.ScheduledArticle;
import eu.openminted.toolkit.queue.services.QueueService;
import eu.openminted.toolkit.storage.StorageDAO;
import eu.openminted.toolkit.storage.exceptions.StorageException;
import java.io.IOException;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author samuel
 */
public class HandleWileyMessage implements MessageEventCallback {

    Logger logger = LoggerFactory.getLogger(HandleWileyMessage.class.getName());

    private final QueueService queueService;

    private final GenericArticleFileDAO genericArticleFileDAO;
    private final StorageDAO storageDAO;

    private final String publisherPrefix = "wiley";

    public HandleWileyMessage(QueueService queueService, GenericArticleFileDAO genericArticleFileDAO, StorageDAO storageDAO) {
        this.queueService = queueService;
        this.genericArticleFileDAO = genericArticleFileDAO;
        this.storageDAO = storageDAO;
    }

    @Override
    public void callback(ScheduledArticle article) throws IOException, StorageException, DatabaseException {

        URL url = new URL("https://api.wiley.com/onlinelibrary/tdm/v1/articles/" + article.getDoi());

        this.genericArticleFileDAO.insertNewArticle(this.publisherPrefix, article.getDoi(), article.getMetadata());

        // Store metadata into file
        this.storageDAO.storeMetadataFile(url.toString(), article.getMetadata(), ".json");
        String metadataLocation = this.storageDAO.getMetadataFileLocation(this.publisherPrefix, article.getDoi(), url.toString(), ".json");
        this.genericArticleFileDAO.updateMetaFileLocation(article.getDoi(), metadataLocation);

        // Download PDF into file
        String pdfLocation = this.storageDAO.getPdfFileLocation(publisherPrefix, article.getDoi(), url.toString());


        this.genericArticleFileDAO.updatePdfFileLocation(article.getDoi(), pdfLocation);

    }
}
