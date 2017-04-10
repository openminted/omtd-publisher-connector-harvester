package eu.openminted.toolkit.springer.harvester;

import com.google.gson.Gson;
import eu.openminted.toolkit.crossref.model.works.Link;
import eu.openminted.toolkit.crossref.model.multiple_works.Item;
import eu.openminted.toolkit.crossref.CrossRefClient;
import eu.openminted.toolkit.queue.ScheduledArticle;
import eu.openminted.toolkit.queue.services.QueueService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author lucasanastasiou
 */
@Component
public class SpringerHarvester {

    @Autowired
    CrossRefClient crossRefClient;

    @Autowired
    QueueService queueService;

    private static final Logger logger = Logger.getLogger("SpringerHarvester");

    //
    // TODO make single-prefix able 
    public void scheduleMonthsPublications() {
        for (String prefix : SpringerConstants.SPRINGER_PREFIXES) {
            for (String month : this.getMonths()) {
                List<Item> monthItems = crossRefClient.getPublisherMonthsItemByLicense(prefix, month, SpringerConstants.SPRINGER_TDM_LICENSE_URL);
                monthItems.forEach(item -> scheduleItem(prefix, item));

            }
        }
    }

    public void schedulePrefixMonthPublications(String prefix, String month) {
        logger.info(String.format("Harvesting for prefix : %s for month : %s", prefix, month));
        List<Item> monthItems = crossRefClient.getPublisherMonthsItemByLicense(prefix, month, SpringerConstants.SPRINGER_TDM_LICENSE_URL);
        monthItems.forEach(item -> scheduleItem(prefix, item));
    }

    public List<String> getMonthsFromYearToYear(int fromYear, int toYear){
        List<String> months = new ArrayList<>();
        for (int year = fromYear; year <= toYear; year++) {
            for (int monthPrefix = 1; monthPrefix <= 12; monthPrefix++) {
                String monthDate = "" + year + "-";
                if (monthPrefix < 10) {
                    monthDate += "0" + monthPrefix;
                } else {
                    monthDate += "" + monthPrefix;
                }
                months.add(monthDate);
            }
        }
        return months;
    }
    
    private List<String> getMonths() {
        List<String> months = new ArrayList<>();
        for (int year = 1980; year <= 2017; year++) {
            for (int monthPrefix = 1; monthPrefix <= 12; monthPrefix++) {
                String monthDate = "" + year + "-";
                if (monthPrefix < 10) {
                    monthDate += "0" + monthPrefix;
                } else {
                    monthDate += "" + monthPrefix;
                }
                months.add(monthDate);
            }
        }
        return months;
    }

    private void scheduleItem(String publisherPrefix, Item item) {

        logger.info(String.format("Scheduling (pushing to queue) item : %s",item.getDOI()));
        Gson gson = new Gson();
        String metadata = gson.toJson(item);

        // field "Link" is a list of links (not just a single link)
        //item.getLink().forEach(linkItem -> System.out.println("Scheduling ...:"+linkItem.getURL()));
        for (Link link : item.getLink()) {
            ScheduledArticle article = new ScheduledArticle();
            article.setDoi(item.getdOI());
            article.setDownloadUrl(link.getURL());
            article.setPublisherPrefix(publisherPrefix);
            article.setMetadata(metadata);
            queueService.scheduleArticle(article);
        }

    }
}
