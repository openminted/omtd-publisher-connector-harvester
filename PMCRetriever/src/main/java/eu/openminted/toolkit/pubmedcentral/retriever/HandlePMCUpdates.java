/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.openminted.toolkit.pubmedcentral.retriever;

import eu.openminted.pubmedcentral.api.RMetadata;
import eu.openminted.pubmedcentral.api.ftp.PMCFtpClient;
import eu.openminted.pubmedcentral.api.ftp.PMCFtpFile;
import eu.openminted.pubmedcentral.api.saxparser.UpdateRecord;
import eu.openminted.toolkit.database.services.ArticleFilesDAO;
import eu.openminted.toolkit.pubmedcentral.retriever.Message.MessageEventCallback;
import eu.openminted.toolkit.queue.ScheduledArticle;
import eu.openminted.toolkit.storage.StorageDAO;
import eu.openminted.toolkit.storage.exceptions.StorageException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import org.apache.commons.io.FileUtils;
import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author samuel
 */
public class HandlePMCUpdates implements MessageEventCallback {

    StorageDAO storageDAO;

    ArticleFilesDAO articleFilesDAO;

    Logger logger = LoggerFactory.getLogger("HandlePMCUpdates");

    @Autowired
    public HandlePMCUpdates(StorageDAO storageDAO, ArticleFilesDAO articleFilesDAO) {
        this.storageDAO = storageDAO;
        this.articleFilesDAO = articleFilesDAO;
    }

    @Override
    public void callback(ScheduledArticle article) throws IOException, StorageException {
        UpdateRecord updateRecord = UpdateRecord.newUpdateRecordFromGsonSerialisedString(article.getMetadata());

        if (!"tgz".equals(updateRecord.getLinkFormat())) {
            logger.warn("Record item not a tar.gz file " + updateRecord.getId());
            return;
        }
        
        URL url = new URL(updateRecord.getLinkHref());

        final long startTime = System.currentTimeMillis();

        File temp = File.createTempFile(updateRecord.getId(), "." + updateRecord.getLinkFormat());
        File tempPdf = null;
        File tempXml = null;
        PMCFtpFile file = new PMCFtpFile(new PMCFtpClient(), url);

        file.retrieve(new FileOutputStream(temp));
        final long endTime = System.currentTimeMillis();

        logger.info("Time to Download File: " + (endTime - startTime));

        Map<String, File> contents = new HashMap<>();

        try {
            FileInputStream fis = new FileInputStream(temp);
            GZIPInputStream gis = new GZIPInputStream(fis);
            TarInputStream tis = new TarInputStream(gis);

            TarEntry entry;
            ByteArrayOutputStream baos;
            while ((entry = tis.getNextEntry()) != null) {
                String entryName = entry.getName();

                if (entryName.endsWith("pdf") || entryName.endsWith("nxml")) {
                    baos = new ByteArrayOutputStream();

                    if (entryName.endsWith("pdf")) {
                        tempPdf = File.createTempFile(updateRecord.getId(), ".pdf");
                        tis.copyEntryContents(new FileOutputStream(tempPdf));
                        contents.put("pdf", tempPdf);
                    }

                    if (entryName.endsWith("nxml")) {
                        tempXml = File.createTempFile(updateRecord.getId(), ".xml");
                        tis.copyEntryContents(new FileOutputStream(tempXml));
                        contents.put("xml", tempXml);
                    }
                }
            }

            String publisher = "pubmed_central";
            String pmcID = "";
            if (contents.containsKey("xml")) {
                File xmlFile = contents.get("xml");
                try {

                    RMetadata metadata = new RMetadata(xmlFile);

                    publisher = metadata.publisher().isEmpty() ? "pubmed_central" : metadata.publisher().toLowerCase().trim().replace(" ", "_").replaceAll("\\W+", "");
                    int maxLength = 50;
                    if (publisher.length() > maxLength) {
                        publisher = publisher.substring(0, maxLength);
                    }
                    pmcID = metadata.pmc();
                    File fileLocation = new File(this.storageDAO.getMetadataFileLocation(publisher, pmcID, updateRecord.getLinkHref(), ".xml"));

                    FileUtils.copyFile(xmlFile, fileLocation);
                } finally {
                    xmlFile.delete();
                }

            }
            if (contents.containsKey("pdf")) {
                File pdfFile = contents.get("pdf");
                try {
                    File fileLocation = new File(this.storageDAO.getPdfFileLocation(publisher, pmcID, updateRecord.getLinkHref()));

                    FileUtils.copyFile(pdfFile, fileLocation);
                } finally {
                    pdfFile.delete();
                }
            }
            final long endProcessTime = System.currentTimeMillis();

            logger.info("Time to Process File: " + (endProcessTime - endTime));
        } finally {
            temp.delete();
            if (tempPdf != null) {
                tempPdf.delete();
            }
            if (tempXml != null) {
                tempXml.delete();
            }
        }
    }
}
