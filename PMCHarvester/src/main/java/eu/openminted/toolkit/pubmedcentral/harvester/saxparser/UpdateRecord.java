
package eu.openminted.toolkit.pubmedcentral.harvester.saxparser;

/**
 * This DTO is populated with the xml data of a PubMed Update
 * @author samuel
 */
public class UpdateRecord {

    private String id;
    private String citation;
    private String linkFormat;
    private String linkUpdated;
    private String linkHref;
    
    public UpdateRecord() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCitation() {
        return citation;
    }

    public void setCitation(String citation) {
        this.citation = citation;
    }

    public String getLinkFormat() {
        return linkFormat;
    }

    public void setLinkFormat(String linkFormat) {
        this.linkFormat = linkFormat;
    }

    public String getLinkUpdated() {
        return linkUpdated;
    }

    public void setLinkUpdated(String linkUpdated) {
        this.linkUpdated = linkUpdated;
    }

    public String getLinkHref() {
        return linkHref;
    }

    public void setLinkHref(String linkHref) {
        this.linkHref = linkHref;
    }
    
}
