package eu.openminted.toolkit.crossref.model.member;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Breakdowns {

    @SerializedName("dois-by-issued-year")
    @Expose
    private List<List<Long>> doisByIssuedYear = null;

    public List<List<Long>> getDoisByIssuedYear() {
        return doisByIssuedYear;
    }

    public void setDoisByIssuedYear(List<List<Long>> doisByIssuedYear) {
        this.doisByIssuedYear = doisByIssuedYear;
    }

}
