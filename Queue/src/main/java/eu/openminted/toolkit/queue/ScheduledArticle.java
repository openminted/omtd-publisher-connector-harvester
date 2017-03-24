package eu.openminted.toolkit.queue;

/**
 *
 * @author lucasanastasiou
 */
public class ScheduledArticle {

    public String metadata;
    public String publisherPrefix;
    public String doi;
    public String downloadUrl;

    public ScheduledArticle(){}
    public ScheduledArticle(String metadata, String publisherPrefix, String doi, String downloadUrl) {
        this.metadata = metadata;
        this.publisherPrefix = publisherPrefix;
        this.doi = doi;
        this.downloadUrl = downloadUrl;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public String getPublisherPrefix() {
        return publisherPrefix;
    }

    public void setPublisherPrefix(String publisherPrefix) {
        this.publisherPrefix = publisherPrefix;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    @Override
    public String toString() {
        return "ScheduledArticle{" + "metadata=" + metadata + ", publisherPrefix=" + publisherPrefix + ", doi=" + doi + ", downloadUrl=" + downloadUrl + '}';
    }
    
}
