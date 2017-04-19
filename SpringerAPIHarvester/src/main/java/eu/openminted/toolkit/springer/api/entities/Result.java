
package eu.openminted.toolkit.springer.api.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("start")
    @Expose
    private String start;
    @SerializedName("pageLength")
    @Expose
    private String pageLength;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getPageLength() {
        return pageLength;
    }

    public void setPageLength(String pageLength) {
        this.pageLength = pageLength;
    }

}
