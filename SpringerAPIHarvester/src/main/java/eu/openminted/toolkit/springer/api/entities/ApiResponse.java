
package eu.openminted.toolkit.springer.api.entities;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiResponse {

    @SerializedName("query")
    @Expose
    private String query;
    @SerializedName("apiKey")
    @Expose
    private String apiKey;
    @SerializedName("result")
    @Expose
    private List<Result> result = null;
    @SerializedName("records")
    @Expose
    private List<Record> records = null;
    @SerializedName("facets")
    @Expose
    private List<Facet> facets = null;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public List<Facet> getFacets() {
        return facets;
    }

    public void setFacets(List<Facet> facets) {
        this.facets = facets;
    }

    @Override
    public String toString() {
        return "ApiResponse{" + "query=" + query + ", apiKey=" + apiKey + ", result=" + result + ", records=" + records + ", facets=" + facets + '}';
    }
    

}
