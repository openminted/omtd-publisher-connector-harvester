
package eu.openminted.crossref.model_tmp;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Message {

    @SerializedName("facets")
    @Expose
    private Facets facets;
    @SerializedName("next-cursor")
    @Expose
    private String nextCursor;
    @SerializedName("total-results")
    @Expose
    private Long totalResults;
    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    @SerializedName("items-per-page")
    @Expose
    private Long itemsPerPage;
    @SerializedName("query")
    @Expose
    private Query query;

    public Facets getFacets() {
        return facets;
    }

    public void setFacets(Facets facets) {
        this.facets = facets;
    }

    public String getNextCursor() {
        return nextCursor;
    }

    public void setNextCursor(String nextCursor) {
        this.nextCursor = nextCursor;
    }

    public Long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Long totalResults) {
        this.totalResults = totalResults;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Long getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(Long itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

}
