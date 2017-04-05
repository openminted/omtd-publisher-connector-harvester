package eu.openminted.toolkit.springer.harvester;

import com.google.gson.Gson;
import eu.openminted.toolkit.database.services.DoiDiscoveryLogServiceDAO;
import eu.openminted.toolkit.springer.api.SpringerAPIClient;
import eu.openminted.toolkit.springer.api.entities.Record;
import eu.openminted.toolkit.queue.ScheduledArticle;
import eu.openminted.toolkit.queue.services.QueueService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author lucasanastasiou
 */
@Component
public class SpringerOAHarvester implements CommandLineRunner {

    @Autowired
    QueueService queueService;

    @Autowired
    SpringerAPIClient springerAPIClient;

    @Autowired
    DoiDiscoveryLogServiceDAO doiDiscoveryLogServiceDAO;

    @Override
    public void run(String... strings) throws Exception {

        String year = "2010";

//        List<String> datesToHarvest = Utility.getDaysAsStringFromDateToDate(year + "-01-01", year + "-12-31");
        List<String> datesToHarvest = Utility.getDaysAsStringFromDateToDate("2010-01-01", "2010-01-02");

        System.out.print("Going to harvest days : ");
        datesToHarvest.forEach(item -> System.out.print(item + ", "));
        System.out.println("");

        queueService.declareDedicatedQueue("Springer-OA-download-queue");

        for (String day : datesToHarvest) {
            long dbID = (int)doiDiscoveryLogServiceDAO.insertNewDiscovery("Springer-OA", day);
            List<Record> records = springerAPIClient.getRecordsOfDay(day);
            System.out.println("DOIS of " + day + " (total " + records.size() + " records)");
            records.forEach(item -> processRecord(item));
            
            doiDiscoveryLogServiceDAO.finishDoiDiscovery(dbID, "Springer-OA");
            doiDiscoveryLogServiceDAO.updateDiscoveredDoisCount(dbID, dbID);
            
            System.out.println("");
        }

    }

    private void processRecord(Record record) {
        System.out.println("record doi = " + record.getDoi());

        ScheduledArticle scheduledArticle = new ScheduledArticle();
        scheduledArticle.setDoi(record.getDoi());
//        scheduledArticle.setDownloadUrl("");
        scheduledArticle.setPublisherPrefix("Springer-OA");

        Gson gson = new Gson();
        String metadata = gson.toJson(record);
        scheduledArticle.setMetadata(metadata);

        queueService.scheduleArticleToDedicatedQueue("Springer-OA-download-queue", scheduledArticle);
    }

}
