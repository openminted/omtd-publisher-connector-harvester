
package eu.openminted.crossref.model_tmp;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContentDomain {

    @SerializedName("domain")
    @Expose
    private List<Object> domain = null;
    @SerializedName("crossmark-restriction")
    @Expose
    private Boolean crossmarkRestriction;

    public List<Object> getDomain() {
        return domain;
    }

    public void setDomain(List<Object> domain) {
        this.domain = domain;
    }

    public Boolean getCrossmarkRestriction() {
        return crossmarkRestriction;
    }

    public void setCrossmarkRestriction(Boolean crossmarkRestriction) {
        this.crossmarkRestriction = crossmarkRestriction;
    }

}
