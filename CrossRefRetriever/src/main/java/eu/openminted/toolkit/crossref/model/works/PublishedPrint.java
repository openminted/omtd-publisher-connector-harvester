
package eu.openminted.toolkit.crossref.model.works;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PublishedPrint {

    @SerializedName("date-parts")
    @Expose
    private List<List<Integer>> dateParts = null;

    public List<List<Integer>> getDateParts() {
        return dateParts;
    }

    public void setDateParts(List<List<Integer>> dateParts) {
        this.dateParts = dateParts;
    }

    @Override
    public String toString() {
        return "PublishedPrint{" + "dateParts=" + dateParts + '}';
    }

}
