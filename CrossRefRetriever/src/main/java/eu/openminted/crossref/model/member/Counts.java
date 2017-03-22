package eu.openminted.crossref.model.member;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Counts {

    @SerializedName("total-dois")
    @Expose
    private Long totalDois;
    @SerializedName("current-dois")
    @Expose
    private Long currentDois;
    @SerializedName("backfile-dois")
    @Expose
    private Long backfileDois;

    public Long getTotalDois() {
        return totalDois;
    }

    public void setTotalDois(Long totalDois) {
        this.totalDois = totalDois;
    }

    public Long getCurrentDois() {
        return currentDois;
    }

    public void setCurrentDois(Long currentDois) {
        this.currentDois = currentDois;
    }

    public Long getBackfileDois() {
        return backfileDois;
    }

    public void setBackfileDois(Long backfileDois) {
        this.backfileDois = backfileDois;
    }

}
