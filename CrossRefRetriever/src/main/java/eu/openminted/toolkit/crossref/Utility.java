package eu.openminted.toolkit.crossref;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

    public static List<String> getMonthsAsStringFromYearToYear(String startingYear, String endingYear) {
        startingYear += "-01-01";
        endingYear += "-12-31";
        List<String> stringDays = new ArrayList<>();
        LocalDate start = LocalDate.parse(startingYear);
        LocalDate end = LocalDate.parse(endingYear);
        
        while (!start.isAfter(end)) {
            stringDays.add(""+start.format(DateTimeFormatter.ofPattern("yyyy-MM", Locale.ENGLISH)));
            start = start.plusMonths(1);
        }
        return stringDays;        
    }
    public static void main(String[] args) {

//        Utility.getDaysAsStringFromDateToDate("2012-01-01", "2012-02-25").forEach(item -> System.out.println(item));
        Utility.getMonthsAsStringFromYearToYear("2001", "2001").forEach(item -> System.out.println(item));
    }
}
