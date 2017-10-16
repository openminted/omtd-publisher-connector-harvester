/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.openminted.toolkit.wiley.harvester;

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
import java.io.InputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import javax.net.ssl.HttpsURLConnection;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author samuel
 */
public class HandleWileyMessage implements MessageEventCallback {

    Logger logger = LoggerFactory.getLogger(HandleWileyMessage.class.getName());

    private final QueueService queueService;

    private final GenericArticleFileDAO genericArticleFileDAO;
    private final StorageDAO storageDAO;

    private final String publisherPrefix = "Wiley";

    private final String crossrefKey;

    @Autowired
    public HandleWileyMessage(QueueService queueService, GenericArticleFileDAO genericArticleFileDAO, StorageDAO storageDAO, String apiKey) {
        this.queueService = queueService;
        this.genericArticleFileDAO = genericArticleFileDAO;
        this.storageDAO = storageDAO;
        this.crossrefKey = apiKey;
    }

    @Override
    public void callback(ScheduledArticle article) throws IOException, StorageException, DatabaseException {

        URL url = new URL("https://api.wiley.com/onlinelibrary/tdm/v1/articles/" + URLEncoder.encode(article.getDoi(), "UTF-8"));

        this.genericArticleFileDAO.insertNewArticle(this.publisherPrefix, article.getDoi(), article.getMetadata());

        // Store metadata into file
        this.storageDAO.storeMetadataFile(url.toString(), article.getMetadata(), ".json");
        String metadataLocation = this.storageDAO.getMetadataFileLocation(this.publisherPrefix, article.getDoi(), url.toString(), "json");
        this.genericArticleFileDAO.updateMetaFileLocation(article.getDoi(), metadataLocation);

        // Download PDF into file
        String pdfLocation = this.storageDAO.getPdfFileLocation(publisherPrefix, article.getDoi(), url.toString());
        retrieveUrlToFile(url.toString(), pdfLocation);
        this.genericArticleFileDAO.updatePdfFileLocation(article.getDoi(), pdfLocation);

    }

    private void retrieveUrlToFile(String articleUrl, String fileLocation) throws MalformedURLException, IOException {
        URL obj = new URL(articleUrl);
        HttpURLConnection con = (HttpsURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        if (crossrefKey.isEmpty()) {
            throw new RuntimeException("Crossref API key not set. Please rerun the application passing --crossrefkey=<YOUR KEY> as a command line parameter");
        }
        con.addRequestProperty("CR-Clickthrough-Client-Token", crossrefKey);
        con.addRequestProperty("User-Agent", "Java");
        con.addRequestProperty("Content-type", "application/pdf");
        con.setInstanceFollowRedirects(true);
        con.connect();
        logger.info("Request Status: " + con.getResponseCode());

        System.out.println("Request URL ... " + articleUrl);

        boolean redirect = false;

        // normally, 3xx is redirect
        int status = con.getResponseCode();
        if (status != HttpURLConnection.HTTP_OK) {
            if (status == HttpURLConnection.HTTP_MOVED_TEMP
                    || status == HttpURLConnection.HTTP_MOVED_PERM
                    || status == HttpURLConnection.HTTP_SEE_OTHER) {
                redirect = true;
            }
        }

        if (status == HttpURLConnection.HTTP_CONFLICT) {
            InputStream is = null;
            try {
                is = con.getErrorStream();
                String theString = IOUtils.toString(is, "UTF-8"); 
                logger.warn(theString);
            } finally {
                IOUtils.closeQuietly(is);
            }
        }

        System.out.println("Response Code ... " + status);

        if (redirect) {

            // get redirect url from "location" header field
            String newUrl = con.getHeaderField("Location");

            // open the new connnection again
            if (newUrl.startsWith("http://")) {
                con = (HttpURLConnection) new URL(newUrl).openConnection();
            } else {
                con = (HttpsURLConnection) new URL(newUrl).openConnection();
            }
            con.addRequestProperty("User-Agent", "Java");
            con.addRequestProperty("Content-type", "application/pdf");

            System.out.println("Redirect to URL : " + newUrl);

        }

        if (con.getResponseCode() == 200) {

            InputStream is = null;
            FileOutputStream fos = null;
            try {
                is = con.getInputStream();

                fos = new FileOutputStream(new File(fileLocation));

                System.out.println("wrting output to " + fileLocation);
                int length = -1;
                byte[] buffer = new byte[1024];// buffer for portion of data from
                // connection
                while ((length = is.read(buffer)) > -1) {
                    fos.write(buffer, 0, length);
                }
            } finally {
                IOUtils.closeQuietly(is);
                IOUtils.closeQuietly(fos);
            }
        }
    }
}
