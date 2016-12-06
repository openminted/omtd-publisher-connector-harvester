package eu.openminted.toolkit.queue;

/**
 *
 * @author lucasanastasiou
 */
public class ArticleUrl {

    private String articleUrl;
    private long dbId;

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public long getDbId() {
        return dbId;
    }

    public void setDbId(long dbId) {
        this.dbId = dbId;
    }

    public ArticleUrl(String articleUrl, long dbId) {
        this.articleUrl = articleUrl;
        this.dbId = dbId;
    }

    @Override
    public String toString() {
        return "ArticleUrl{" + "articleUrl=" + articleUrl + ", dbId=" + dbId + '}';
    }
    
    
}
