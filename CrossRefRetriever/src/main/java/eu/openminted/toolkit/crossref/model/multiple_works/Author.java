
package eu.openminted.toolkit.crossref.model.multiple_works;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Author {

    @SerializedName("given")
    @Expose
    private String given;
    @SerializedName("family")
    @Expose
    private String family;
    @SerializedName("affiliation")
    @Expose
    private List<Object> affiliation = null;

    public String getGiven() {
        return given;
    }

    public void setGiven(String given) {
        this.given = given;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public List<Object> getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(List<Object> affiliation) {
        this.affiliation = affiliation;
    }

}
