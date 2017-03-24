package eu.openminted.toolkit.springer.harvester;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author lucasanastasiou
 */
@Component
public class Invoker implements CommandLineRunner {

    @Autowired
    SpringerHarvester springerHarvester;

    private static final Logger logger = Logger.getLogger("Invoker");

    @Override
    public void run(String... strings) throws Exception {
//        String prefix = "10.1891";
        String prefix = "10.7603";
        String month = "2005-01";
        logger.info(String.format("Scheduling for prefix:%s , month:%s", prefix, month));
        springerHarvester.schedulePrefixMonthPublications(prefix, month);
        month = "2005-02";
        springerHarvester.schedulePrefixMonthPublications(prefix, month);
        month = "2005-03";
        springerHarvester.schedulePrefixMonthPublications(prefix, month);
        month = "2005-04";
        springerHarvester.schedulePrefixMonthPublications(prefix, month);
    }

}
