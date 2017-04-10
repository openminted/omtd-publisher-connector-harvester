
package eu.openminted.toolkit.crossref.model.works;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContentDomain {

    @SerializedName("domain")
    @Expose
    private List<String> domain = null;
    @SerializedName("crossmark-restriction")
    @Expose
    private Boolean crossmarkRestriction;

    public List<String> getDomain() {
        return domain;
    }

    public void setDomain(List<String> domain) {
        this.domain = domain;
    }

    public Boolean getCrossmarkRestriction() {
        return crossmarkRestriction;
    }

    public void setCrossmarkRestriction(Boolean crossmarkRestriction) {
        this.crossmarkRestriction = crossmarkRestriction;
    }

}
