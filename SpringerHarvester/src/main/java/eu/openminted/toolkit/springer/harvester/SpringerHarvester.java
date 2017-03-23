package eu.openminted.toolkit.springer.harvester;

import eu.openminted.crossref.model_tmp.Item;
import eu.openminted.crossref.retriever.CrossRefClient;
import eu.openminted.toolkit.queue.ArticleUrl;
import eu.openminted.toolkit.queue.services.QueueService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author lucasanastasiou
 */
public class SpringerHarvester {

    @Autowired
    CrossRefClient crossRefClient;
    
    @Autowired
    QueueService queueService;

    public void scheduleMonthsPublications() {
        for (String prefix : SpringerConstants.SPRINGER_PREFIXES) {
            for (String month : this.getMonths()) {
                List<Item> monthItems = crossRefClient.getPublisherMonthsItemByLicense(prefix, month, SpringerConstants.SPRINGER_TDM_LICENSE_URL);
                monthItems.forEach(item -> scheduleItem(item));

            }
        }

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

    private void scheduleItem(Item item) {
        ArticleUrl articleUrl = new ArticleUrl(item.getDOI(), 0);
        queueService.pushArticleUrl(articleUrl);
    }
}
