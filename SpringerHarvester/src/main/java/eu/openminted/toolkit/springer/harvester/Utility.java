package eu.openminted.toolkit.springer.harvester;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author lucasanastasiou
 */
public class Utility {

    public static List<String> getDaysAsStringFromDateToDate(String startDate, String toDate) {
        List<String> stringDays = new ArrayList<>();
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(toDate);
        
        while (!start.isAfter(end)) {
            stringDays.add(start.toString());            
            start = start.plusDays(1);
        }
        return stringDays;        
    }

//    public static void main(String[] args) {
//        Utility utility = new Utility();
////        utility.getDaysOfYear().forEach(item -> System.out.println(item));
//    utility.getDaysAsStringFromDateToDate("2012-01-01", "2012-02-25").forEach(item -> System.out.println(item));
//    }
}
