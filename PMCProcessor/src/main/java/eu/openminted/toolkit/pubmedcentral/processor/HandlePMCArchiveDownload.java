/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.openminted.toolkit.pubmedcentral.processor;

import eu.openminted.dit.GenericArticleRetrieverService;
import eu.openminted.pubmedcentral.api.saxparser.UpdateRecord;
import eu.openminted.toolkit.database.exceptions.DatabaseException;
import eu.openminted.toolkit.database.services.GenericArticleFileDAO;
import eu.openminted.toolkit.pubmedcentral.retriever.Message.MessageEventCallback;
import eu.openminted.toolkit.queue.ScheduledArticle;
import eu.openminted.toolkit.storage.StorageDAO;
import eu.openminted.toolkit.storage.exceptions.StorageException;
import java.io.File;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author samuel
 */
public class HandlePMCArchiveDownload implements MessageEventCallback {

    Logger logger = LoggerFactory.getLogger("HandlePMCUpdates");

    private final StorageDAO storageDAO;

    private final GenericArticleFileDAO genericArticleFileDAO;
    
    private final GenericArticleRetrieverService genericArticleRetrieverService;

    public HandlePMCArchiveDownload(StorageDAO storageDAO, GenericArticleFileDAO genericArticleFileDAO, GenericArticleRetrieverService genericArticleRetrieverService) {
        this.storageDAO = storageDAO;
        this.genericArticleFileDAO = genericArticleFileDAO;
        this.genericArticleRetrieverService = genericArticleRetrieverService;
    }
    
    @Override
    public void callback(ScheduledArticle article) throws IOException, StorageException, DatabaseException {
        UpdateRecord updateRecord = UpdateRecord.newUpdateRecordFromGsonSerialisedString(article.getMetadata());

        logger.info("Message Recieved " + updateRecord.getId());

        File archive = new File(article.downloadUrl);

        if (!archive.exists()) {
            logger.warn("Download file does not exist " + updateRecord.getId() + " " + article.downloadUrl);
            return;
        }

        final long startTime = System.currentTimeMillis();

        new ProcessDownloadedDocument(archive, updateRecord, storageDAO, genericArticleFileDAO, genericArticleRetrieverService).run();
        final long endTime = System.currentTimeMillis();
        logger.info(updateRecord.getId() + " Time to Process File: " + (endTime - startTime));

    }
}
