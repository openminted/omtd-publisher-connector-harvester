package eu.openminted.toolkit.frontiers.harvester;

import com.google.gson.Gson;
import eu.openminted.toolkit.crossref.CrossRefClient;
import eu.openminted.toolkit.crossref.Utility;
import eu.openminted.toolkit.crossref.model.multiple_works.Item;
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

    private static final Logger logger = Logger.getLogger("FrontiersHarvesterService");

    @Override
    public void run(String... strings) throws Exception {

        this.scheduleMonthsPublications();
    }

    //
    // TODO make single-prefix able 
    private void scheduleMonthsPublications() {
        for (String prefix : FrontiersConstants.FRONTIERS_PREFIXES) {
            for (String month : this.getMonths()) {
                List<Item> monthItems = crossRefClient.getPublisherMonthItems(prefix, month);
                monthItems.forEach(item -> scheduleItem(prefix, item));

            }
        }
    }

    public void schedulePrefixMonthPublications(String prefix, String month) {
        logger.info(String.format("Harvesting for prefix : %s for month : %s", prefix, month));
        List<Item> monthItems = crossRefClient.getPublisherMonthItems(prefix, month);
        monthItems.forEach(item -> scheduleItem(prefix, item));
    }

    private List<String> getMonths() {
        return Utility.getMonthsAsStringFromYearToYear("2007", "2009");
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
