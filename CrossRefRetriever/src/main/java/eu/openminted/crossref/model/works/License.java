
package eu.openminted.crossref.model.works;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class License {

    @SerializedName("URL")
    @Expose
    private String uRL;
    @SerializedName("start")
    @Expose
    private Start start;
    @SerializedName("delay-in-days")
    @Expose
    private Integer delayInDays;
    @SerializedName("content-version")
    @Expose
    private String contentVersion;

    public String getURL() {
        return uRL;
    }

    public void setURL(String uRL) {
        this.uRL = uRL;
    }

    public Start getStart() {
        return start;
    }

    public void setStart(Start start) {
        this.start = start;
    }

    public Integer getDelayInDays() {
        return delayInDays;
    }

    public void setDelayInDays(Integer delayInDays) {
        this.delayInDays = delayInDays;
    }

    public String getContentVersion() {
        return contentVersion;
    }

    public void setContentVersion(String contentVersion) {
        this.contentVersion = contentVersion;
    }

}
