/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.openminted.toolkit.pubmedcentral.processor;

import eu.openminted.dit.GenericArticleRetrieverService;
import eu.openminted.pubmedcentral.api.RMetadata;
import eu.openminted.pubmedcentral.api.saxparser.UpdateRecord;
import eu.openminted.toolkit.database.exceptions.DatabaseException;
import eu.openminted.toolkit.database.services.GenericArticleFileDAO;
import eu.openminted.toolkit.storage.StorageDAO;
import eu.openminted.toolkit.storage.exceptions.StorageException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarInputStream;
import org.slf4j.LoggerFactory;

/**
 *
 * @author samuel
 */
public class ProcessDownloadedDocument implements Runnable {

    private final File tempArchiveLocation;
    private final UpdateRecord updateRecord;

    private final StorageDAO storageDAO;

    private final GenericArticleFileDAO genericArticleFileDAO;

    private final GenericArticleRetrieverService genericArticleRetrieverService;

    org.slf4j.Logger logger = LoggerFactory.getLogger("ProcessDownloadedDocument");

    public ProcessDownloadedDocument(File tempArchiveLocation, UpdateRecord updateRecord, StorageDAO storageDAO, GenericArticleFileDAO genericArticleFileDAO, GenericArticleRetrieverService genericArticleRetrieverService) {
        this.tempArchiveLocation = tempArchiveLocation;
        this.updateRecord = updateRecord;
        this.storageDAO = storageDAO;
        this.genericArticleFileDAO = genericArticleFileDAO;
        this.genericArticleRetrieverService = genericArticleRetrieverService;
    }

    @Override
    public void run() {
        final long startProcessTime = System.currentTimeMillis();

        Map<String, File> contents = new HashMap<>();

        FileInputStream fis = null;
        GZIPInputStream gis = null;
        TarInputStream tis = null;

        File tempPdf = null;
        File tempXml = null;

        try {
            fis = new FileInputStream(this.tempArchiveLocation);
            gis = new GZIPInputStream(fis);
            tis = new TarInputStream(gis);

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
            final long endProcessArchive = System.currentTimeMillis();
            logger.info(updateRecord.getId() + "Time to Process Archive: " + (endProcessArchive - startProcessTime));

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
                    final long startDBQuery = System.currentTimeMillis();
                    this.genericArticleFileDAO.insertNewArticle("pubmed_central", pmcID, "");
                    this.genericArticleFileDAO.updateMetaFileLocation(pmcID, fileLocation.getAbsolutePath());
                    logger.info(updateRecord.getId() + " Saving DB Query insert metadata: " + (System.currentTimeMillis() - startDBQuery));
                    FileUtils.copyFile(xmlFile, fileLocation);
                } catch (DatabaseException | StorageException | IOException e) {
                    logger.error(e.getMessage(), e);
                } finally {
                    xmlFile.delete();
                }
            }
            if (contents.containsKey("pdf")) {
                File pdfFile = contents.get("pdf");
                try {
                    File fileLocation = new File(this.storageDAO.getPdfFileLocation(publisher, pmcID, updateRecord.getLinkHref()));
                    FileUtils.copyFile(pdfFile, fileLocation);
                    final long startDBQuery = System.currentTimeMillis();
                    this.genericArticleFileDAO.updatePdfFileLocation(pmcID, fileLocation.getAbsolutePath());
                    logger.info(updateRecord.getId() + " Saving DB Query updatePdfFileLocation: " + (System.currentTimeMillis() - startDBQuery));
                } catch (DatabaseException | StorageException ex) {
                    logger.error(ex.getMessage(), ex);
                } finally {
                    pdfFile.delete();
                }
            }
            final long endProcessTime = System.currentTimeMillis();
            logger.info(updateRecord.getId() + "Time to Save Files: " + (endProcessTime - endProcessArchive));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProcessDownloadedDocument.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProcessDownloadedDocument.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.tempArchiveLocation.delete();
            if (tempPdf != null) {
                tempPdf.delete();
            }
            if (tempXml != null) {
                tempXml.delete();
            }
            IOUtils.closeQuietly(fis);
            IOUtils.closeQuietly(gis);
            IOUtils.closeQuietly(tis);
        }
    }
}
