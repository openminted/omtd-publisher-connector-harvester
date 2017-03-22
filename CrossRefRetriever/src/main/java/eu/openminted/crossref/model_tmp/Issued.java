
package eu.openminted.crossref.model_tmp;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Issued {

    @SerializedName("date-parts")
    @Expose
    private List<List<Long>> dateParts = null;

    public List<List<Long>> getDateParts() {
        return dateParts;
    }

    public void setDateParts(List<List<Long>> dateParts) {
        this.dateParts = dateParts;
    }

}
