package eu.openminted.toolkit.database.model;

import java.sql.Timestamp;

/**
 *
 * @author lucasanastasiou
 */
public class CrawlVisit {

    private Integer id;
    
    private String url;
    
    private String parentUrl;
    
    private Timestamp timeCrawled;
    
    private int level;
    
    private String filename;

    public CrawlVisit(){}
    
    public CrawlVisit(Integer id, String url, String parentUrl, int level, String filename) {
        this.id = id;
        this.url = url;
        this.parentUrl = parentUrl;
        this.level = level;
        this.filename = filename;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParentUrl() {
        return parentUrl;
    }

    public void setParentUrl(String parentUrl) {
        this.parentUrl = parentUrl;
    }

    public Timestamp getTimeCrawled() {
        return timeCrawled;
    }

    public void setTimeCrawled(Timestamp timeCrawled) {
        this.timeCrawled = timeCrawled;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public String toString() {
        return "CrawlVisit{" + "id=" + id + ", url=" + url + ", parentUrl=" + parentUrl + ", timeCrawled=" + timeCrawled + ", level=" + level + ", filename=" + filename + '}';
    }
}
