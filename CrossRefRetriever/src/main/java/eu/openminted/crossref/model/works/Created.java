
package eu.openminted.crossref.model.works;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Created {

    @SerializedName("date-parts")
    @Expose
    private List<List<Integer>> dateParts = null;
    @SerializedName("date-time")
    @Expose
    private String dateTime;
    @SerializedName("timestamp")
    @Expose
    private Long timestamp;

    public List<List<Integer>> getDateParts() {
        return dateParts;
    }

    public void setDateParts(List<List<Integer>> dateParts) {
        this.dateParts = dateParts;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

}
