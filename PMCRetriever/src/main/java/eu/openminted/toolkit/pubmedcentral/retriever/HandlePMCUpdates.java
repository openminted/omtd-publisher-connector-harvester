/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.openminted.toolkit.pubmedcentral.retriever;

import eu.openminted.dit.GenericArticleRetrieverService;
import eu.openminted.pubmedcentral.api.ftp.PMCFtpClient;
import eu.openminted.pubmedcentral.api.ftp.PMCFtpFile;
import eu.openminted.pubmedcentral.api.saxparser.UpdateRecord;
import eu.openminted.toolkit.database.exceptions.DatabaseException;
import eu.openminted.toolkit.database.services.GenericArticleFileDAO;
import eu.openminted.toolkit.pubmedcentral.retriever.Message.MessageEventCallback;
import eu.openminted.toolkit.queue.ScheduledArticle;
import eu.openminted.toolkit.queue.services.QueueService;
import eu.openminted.toolkit.storage.StorageDAO;
import eu.openminted.toolkit.storage.exceptions.StorageException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author samuel
 */
public class HandlePMCUpdates implements MessageEventCallback {

    Logger logger = LoggerFactory.getLogger("HandlePMCUpdates");

    private final QueueService queueService;

    private final StorageDAO storageDAO;

    public HandlePMCUpdates(QueueService queueService, StorageDAO storageDAO) {
        this.queueService = queueService;
        this.storageDAO = storageDAO;
    }
    
    @Override
    public void callback(ScheduledArticle article) throws IOException, StorageException, DatabaseException {
        UpdateRecord updateRecord = UpdateRecord.newUpdateRecordFromGsonSerialisedString(article.getMetadata());

        logger.info("Message Recieved " + updateRecord.getId());

        if (!"tgz".equals(updateRecord.getLinkFormat())) {
            logger.warn("Record item not a tar.gz file " + updateRecord.getId());
            return;
        }

        URL url = new URL(updateRecord.getLinkHref().replace("ftp://", "https://"));

        final long startTime = System.currentTimeMillis();

        File temp = new File("/data/core/dit/tmp/" + updateRecord.getId() + "." + updateRecord.getLinkFormat());
                
//        PMCFtpFile file = new PMCFtpFile(new PMCFtpClient(), url);
        logger.info(updateRecord.getId() + " Downloading file Recieved " + url.toExternalForm());
//        file.retrieve(new FileOutputStream(temp));
        FileUtils.copyURLToFile(url, temp);
        logger.info(updateRecord.getId() + " Finished Downloading file Recieved " + url.toExternalForm());

        final long endTime = System.currentTimeMillis();
        
        // The API does not let us send our own object so we need to repurpose ScheduledArticle()
        ScheduledArticle scheduledArticle = new ScheduledArticle();
        scheduledArticle.setDownloadUrl(temp.getAbsolutePath());
        scheduledArticle.setMetadata(article.getMetadata());
        this.queueService.scheduleArticleToDedicatedQueue(PMCRetrieverApp.queueName, scheduledArticle);
        
        logger.info(updateRecord.getId() + " Time to Download File: " + (endTime - startTime));

        //executorService.execute(new ProcessDownloadedDocument(temp, updateRecord, storageDAO, genericArticleFileDAO, genericArticleRetrieverService));        
        //logger.info(updateRecord.getId() + " Scheduled Processing");

    }
}
