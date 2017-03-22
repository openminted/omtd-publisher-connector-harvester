
package eu.openminted.crossref.model_tmp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Query {

    @SerializedName("start-index")
    @Expose
    private Long startIndex;
    @SerializedName("search-terms")
    @Expose
    private Object searchTerms;

    public Long getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Long startIndex) {
        this.startIndex = startIndex;
    }

    public Object getSearchTerms() {
        return searchTerms;
    }

    public void setSearchTerms(Object searchTerms) {
        this.searchTerms = searchTerms;
    }

}
