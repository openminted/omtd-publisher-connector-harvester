package eu.openminted.toolkit.pubmedcentral.harvester;

import com.google.gson.Gson;
import eu.openminted.pubmedcentral.api.RPubMedCentralOA;
import eu.openminted.pubmedcentral.api.RResumptionToken;
import eu.openminted.toolkit.crossref.CrossRefClient;
import eu.openminted.toolkit.database.services.DoiDiscoveryLogServiceDAO;
import eu.openminted.pubmedcentral.api.saxparser.UpdatesSaxHandler;
import eu.openminted.toolkit.queue.ScheduledArticle;
import eu.openminted.toolkit.queue.services.QueueService;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;
/**
 *
 * @author lucasanastasiou
 */
@Component
public class PMCHarvester implements CommandLineRunner {

    @Autowired
    CrossRefClient crossRefClient;

    @Autowired
    QueueService queueService;

    @Autowired
    DoiDiscoveryLogServiceDAO doiDiscoveryLogServiceDAO;

    private static final Logger logger = Logger.getLogger("PMCHarvester");

    public static String queueName = "PMC-download-queue";

    private static String publisher = "PMC";

    @Override
    public void run(String... strings) throws Exception {
        queueService.declareDedicatedQueue(this.queueName);

        RPubMedCentralOA pubmedCentralOA = new RPubMedCentralOA("");

        String earliest = "2013-03-24 12:02:42";// pubmedCentralOA.status().earliest();
        String until =  pubmedCentralOA.status().latest();
       
        String body = pubmedCentralOA.updates().retrieve(earliest, until).fetch();
        logger.info(String.format("Retrieving %s to %s", earliest, until));

        List<RResumptionToken> previousResumptionTokens = new ArrayList<>();

        RResumptionToken resumptionToken;

        AtomicInteger failures = new AtomicInteger(0);
        AtomicInteger recordsFound = new AtomicInteger(0);
        do {

            InputSource stream = new InputSource(new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8)));
            stream.setEncoding("UTF-8");

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler userhandler = new UpdatesSaxHandler((updateRecord) -> {
                this.scheduleItem(this.publisher, updateRecord);
                recordsFound.addAndGet(1);
            });

            saxParser.parse(stream, userhandler);

            resumptionToken = new RResumptionToken(body);

            if (resumptionToken.isEmpty()) {
                failures.addAndGet(1);
            } else {
                previousResumptionTokens.add(resumptionToken);
            }

            logger.info(String.format("Following Resumption Token %s", resumptionToken.token()));
            logger.info(String.format("Following Resumption Token %s", resumptionToken.href()));

            if (resumptionToken.isEmpty()) {
                body = pubmedCentralOA.updates().retrieve(previousResumptionTokens.get(previousResumptionTokens.size() - 1)).fetch();
            } else {
                body = pubmedCentralOA.updates().retrieve(resumptionToken).fetch();

            }

        } while (!resumptionToken.isEmpty() && failures.get() < 4);

    }

    private void scheduleItem(String publisherPrefix, Object item) {

        logger.info(String.format("Scheduling (pushing to queue) item : %s", item.toString()));
        Gson gson = new Gson();
        String metadata = gson.toJson(item);

        ScheduledArticle article = new ScheduledArticle();
        article.setDownloadUrl(null);
        article.setPublisherPrefix(publisher);
        article.setMetadata(metadata);
        queueService.scheduleArticleToDedicatedQueue(this.queueName, article);

    }
}
