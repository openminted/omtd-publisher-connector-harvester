
package eu.openminted.crossref.model.works;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Funder {

    @SerializedName("DOI")
    @Expose
    private String dOI;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("doi-asserted-by")
    @Expose
    private String doiAssertedBy;
    @SerializedName("award")
    @Expose
    private List<String> award = null;

    public String getDOI() {
        return dOI;
    }

    public void setDOI(String dOI) {
        this.dOI = dOI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDoiAssertedBy() {
        return doiAssertedBy;
    }

    public void setDoiAssertedBy(String doiAssertedBy) {
        this.doiAssertedBy = doiAssertedBy;
    }

    public List<String> getAward() {
        return award;
    }

    public void setAward(List<String> award) {
        this.award = award;
    }

}
