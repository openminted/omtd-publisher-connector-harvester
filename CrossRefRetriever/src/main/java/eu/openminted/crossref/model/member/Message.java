package eu.openminted.crossref.model.member;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Message {

    @SerializedName("items-per-page")
    @Expose
    private Long itemsPerPage;
    @SerializedName("query")
    @Expose
    private Query query;
    @SerializedName("total-results")
    @Expose
    private Long totalResults;
    @SerializedName("items")
    @Expose
    private List<Item> items = null;

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

    @Override
    public String toString() {
        return "Message{" + "itemsPerPage=" + itemsPerPage + ", query=" + query + ", totalResults=" + totalResults + ", items=" + items + '}';
    }

}
