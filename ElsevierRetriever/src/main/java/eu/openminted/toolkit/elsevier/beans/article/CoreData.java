package eu.openminted.toolkit.elsevier.beans.article;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lucasanastasiou
 */
//@XmlRootElement(name = "coredata")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "coredata")
public class CoreData {
    
    @XmlElement(name = "url", namespace = "http://prismstandard.org/namespaces/basic/2.0/")
    String prism_url;

    @XmlElement(name = "identifier", namespace = "http://purl.org/dc/elements/1.1/")
    String dc_identifier;

    @XmlElement(name = "eid")
    String eid;

    @XmlElement(name = "doi", namespace = "http://prismstandard.org/namespaces/basic/2.0/")
    String doi;

    @XmlElement(name = "pii")
    String pii;

    @XmlElement(name = "title", namespace = "http://purl.org/dc/elements/1.1/")
    String dc_title;

    @XmlElement(name = "publicationName", namespace = "http://prismstandard.org/namespaces/basic/2.0/")
    String prism_publicationName;

    @XmlElement(name = "aggregationType", namespace = "http://prismstandard.org/namespaces/basic/2.0/")
    String prism_aggregationType;

    @XmlElement(name = "issn", namespace = "http://prismstandard.org/namespaces/basic/2.0/")
    String prism_issn;

    @XmlElement(name = "openaccess")
    int openaccess;

    @XmlElement(name = "openaccessArticle")
    boolean openaccessArticle;

    @XmlElement(name = "openaccessType")
    String openaccessType;

    @XmlElement(name = "openArchiveArticle")
    boolean openArchiveArticle;

    @XmlElement(name = "openaccessSponsorName")
    String openaccessSponsorName;

    @XmlElement(name = "openaccessSponsorType")
    String openaccessSponsorType;

    @XmlElement(name = "openaccessUserLicense")
    String openaccessUserLicense;

    @XmlElement(name = "link")
    List<Link> link = new ArrayList<>();

    public String getPrism_url() {
        return prism_url;
    }

    public void setPrism_url(String prism_url) {
        this.prism_url = prism_url;
    }

    public String getDc_identifier() {
        return dc_identifier;
    }

    public void setDc_identifier(String dc_identifier) {
        this.dc_identifier = dc_identifier;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getPii() {
        return pii;
    }

    public void setPii(String pii) {
        this.pii = pii;
    }

    public String getDc_title() {
        return dc_title;
    }

    public void setDc_title(String dc_title) {
        this.dc_title = dc_title;
    }

    public String getPrism_publicationName() {
        return prism_publicationName;
    }

    public void setPrism_publicationName(String prism_publicationName) {
        this.prism_publicationName = prism_publicationName;
    }

    public String getPrism_aggregationType() {
        return prism_aggregationType;
    }

    public void setPrism_aggregationType(String prism_aggregationType) {
        this.prism_aggregationType = prism_aggregationType;
    }

    public String getPrism_issn() {
        return prism_issn;
    }

    public void setPrism_issn(String prism_issn) {
        this.prism_issn = prism_issn;
    }

    public int getOpenaccess() {
        return openaccess;
    }

    public void setOpenaccess(int openaccess) {
        this.openaccess = openaccess;
    }

    public boolean isOpenaccessArticle() {
        return openaccessArticle;
    }

    public void setOpenaccessArticle(boolean openaccessArticle) {
        this.openaccessArticle = openaccessArticle;
    }

    public String getOpenaccessType() {
        return openaccessType;
    }

    public void setOpenaccessType(String openaccessType) {
        this.openaccessType = openaccessType;
    }

    public boolean isOpenArchiveArticle() {
        return openArchiveArticle;
    }

    public void setOpenArchiveArticle(boolean openArchiveArticle) {
        this.openArchiveArticle = openArchiveArticle;
    }

    public String getOpenaccessSponsorName() {
        return openaccessSponsorName;
    }

    public void setOpenaccessSponsorName(String openaccessSponsorName) {
        this.openaccessSponsorName = openaccessSponsorName;
    }

    public String getOpenaccessSponsorType() {
        return openaccessSponsorType;
    }

    public void setOpenaccessSponsorType(String openaccessSponsorType) {
        this.openaccessSponsorType = openaccessSponsorType;
    }

    public String getOpenaccessUserLicense() {
        return openaccessUserLicense;
    }

    public void setOpenaccessUserLicense(String openaccessUserLicense) {
        this.openaccessUserLicense = openaccessUserLicense;
    }

    public List<Link> getLink() {
        return link;
    }

    public void setLink(List<Link> link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "CoreData{" + "prism_url=" + prism_url + ", dc_identifier=" + dc_identifier + ", eid=" + eid + ", doi=" + doi + ", pii=" + pii + ", dc_title=" + dc_title + ", prism_publicationName=" + prism_publicationName + ", prism_aggregationType=" + prism_aggregationType + ", prism_issn=" + prism_issn + ", openaccess=" + openaccess + ", openaccessArticle=" + openaccessArticle + ", openaccessType=" + openaccessType + ", openArchiveArticle=" + openArchiveArticle + ", openaccessSponsorName=" + openaccessSponsorName + ", openaccessSponsorType=" + openaccessSponsorType + ", openaccessUserLicense=" + openaccessUserLicense + ", link=" + link + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.prism_url);
        hash = 47 * hash + Objects.hashCode(this.dc_identifier);
        hash = 47 * hash + Objects.hashCode(this.eid);
        hash = 47 * hash + Objects.hashCode(this.doi);
        hash = 47 * hash + Objects.hashCode(this.pii);
        hash = 47 * hash + Objects.hashCode(this.dc_title);
        hash = 47 * hash + Objects.hashCode(this.prism_publicationName);
        hash = 47 * hash + Objects.hashCode(this.prism_aggregationType);
        hash = 47 * hash + Objects.hashCode(this.prism_issn);
        hash = 47 * hash + this.openaccess;
        hash = 47 * hash + (this.openaccessArticle ? 1 : 0);
        hash = 47 * hash + Objects.hashCode(this.openaccessType);
        hash = 47 * hash + (this.openArchiveArticle ? 1 : 0);
        hash = 47 * hash + Objects.hashCode(this.openaccessSponsorName);
        hash = 47 * hash + Objects.hashCode(this.openaccessSponsorType);
        hash = 47 * hash + Objects.hashCode(this.openaccessUserLicense);
        hash = 47 * hash + Objects.hashCode(this.link);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CoreData other = (CoreData) obj;
        if (this.openaccess != other.openaccess) {
            return false;
        }
        if (this.openaccessArticle != other.openaccessArticle) {
            return false;
        }
        if (this.openArchiveArticle != other.openArchiveArticle) {
            return false;
        }
        if (!Objects.equals(this.prism_url, other.prism_url)) {
            return false;
        }
        if (!Objects.equals(this.dc_identifier, other.dc_identifier)) {
            return false;
        }
        if (!Objects.equals(this.eid, other.eid)) {
            return false;
        }
        if (!Objects.equals(this.doi, other.doi)) {
            return false;
        }
        if (!Objects.equals(this.pii, other.pii)) {
            return false;
        }
        if (!Objects.equals(this.dc_title, other.dc_title)) {
            return false;
        }
        if (!Objects.equals(this.prism_publicationName, other.prism_publicationName)) {
            return false;
        }
        if (!Objects.equals(this.prism_aggregationType, other.prism_aggregationType)) {
            return false;
        }
        if (!Objects.equals(this.prism_issn, other.prism_issn)) {
            return false;
        }
        if (!Objects.equals(this.openaccessType, other.openaccessType)) {
            return false;
        }
        if (!Objects.equals(this.openaccessSponsorName, other.openaccessSponsorName)) {
            return false;
        }
        if (!Objects.equals(this.openaccessSponsorType, other.openaccessSponsorType)) {
            return false;
        }
        if (!Objects.equals(this.openaccessUserLicense, other.openaccessUserLicense)) {
            return false;
        }
        if (!Objects.equals(this.link, other.link)) {
            return false;
        }
        return true;
    }
    
    
}
