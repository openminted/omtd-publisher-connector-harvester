
package eu.openminted.toolkit.crossref.model.works;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Link {

    @SerializedName("URL")
    @Expose
    private String uRL;
    @SerializedName("content-type")
    @Expose
    private String contentType;
    @SerializedName("content-version")
    @Expose
    private String contentVersion;
    @SerializedName("intended-application")
    @Expose
    private String intendedApplication;

    public String getURL() {
        return uRL;
    }

    public void setURL(String uRL) {
        this.uRL = uRL;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentVersion() {
        return contentVersion;
    }

    public void setContentVersion(String contentVersion) {
        this.contentVersion = contentVersion;
    }

    public String getIntendedApplication() {
        return intendedApplication;
    }

    public void setIntendedApplication(String intendedApplication) {
        this.intendedApplication = intendedApplication;
    }

    @Override
    public String toString() {
        return "Link{" + "uRL=" + uRL + ", contentType=" + contentType + ", contentVersion=" + contentVersion + ", intendedApplication=" + intendedApplication + '}';
    }

}
