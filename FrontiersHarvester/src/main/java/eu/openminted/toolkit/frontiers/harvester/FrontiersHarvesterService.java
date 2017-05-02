package eu.openminted.toolkit.frontiers.harvester;

import com.google.gson.Gson;
import eu.openminted.toolkit.crossref.CrossRefClient;
import eu.openminted.toolkit.crossref.Utility;
import eu.openminted.toolkit.crossref.model.multiple_works.Item;
import eu.openminted.toolkit.database.exceptions.DatabaseException;
import eu.openminted.toolkit.database.services.DoiDiscoveryLogServiceDAO;
import eu.openminted.toolkit.queue.ScheduledArticle;
import eu.openminted.toolkit.queue.services.QueueService;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author lucasanastasiou
 */
@Component
public class FrontiersHarvesterService implements CommandLineRunner {

    @Autowired
    CrossRefClient crossRefClient;

    @Autowired
    QueueService queueService;

    @Autowired
    DoiDiscoveryLogServiceDAO doiDiscoveryLogServiceDAO;

    private static final Logger logger = Logger.getLogger("FrontiersHarvesterService");

    @Override
    public void run(String... strings) throws Exception {

        queueService.declareDedicatedQueue("Frontiers-download-queue");
        this.scheduleMonthsPublications();
    }

    //
    // TODO make single-prefix able 
    private void scheduleMonthsPublications() throws DatabaseException {
        for (String prefix : FrontiersConstants.FRONTIERS_PREFIXES) {
            for (String date : this.getDays()) {
                logger.info(String.format("Harvesting for prefix : %s for date : %s", prefix, date));
                List<Item> dateRecords = crossRefClient.getPublisherItemsOfDate(prefix, date);
                
                long dbID = (int) doiDiscoveryLogServiceDAO.insertNewDiscovery("Frontiers", date);

                dateRecords.forEach(item -> scheduleItem(prefix, item));

                logger.info("DOIS of " + date + " (total " + dateRecords.size() + " records)");

                doiDiscoveryLogServiceDAO.finishDoiDiscovery(dbID, "Frontiers");
                doiDiscoveryLogServiceDAO.updateDiscoveredDoisCount(dbID, dateRecords.size());

            }
        }
    }

    private List<String> getMonths() {
        return Utility.getMonthsAsStringFromYearToYear("2010", "2017");
    }

    private List<String> getDays() {
        return Utility.getDaysAsStringFromDateToDate("2010-10-01", "2017-04-12");
    }

    private void scheduleItem(String publisherPrefix, Item item) {

        logger.info(String.format("Scheduling (pushing to queue) item : %s", item.getDOI()));
        Gson gson = new Gson();
        String metadata = gson.toJson(item);

        ScheduledArticle article = new ScheduledArticle();
        article.setDoi(item.getdOI());
        article.setDownloadUrl(null);
        article.setPublisherPrefix("Frontiers");
        article.setMetadata(metadata);
        queueService.scheduleArticleToDedicatedQueue("Frontiers-download-queue", article);

    }
}
