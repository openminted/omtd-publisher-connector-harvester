package eu.openminted.toolkit.elsevier.beans.leafnode;

import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 *
 * @author lucasanastasiou
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "a")
public class WebUrl {

    public WebUrl(String href, String rel, String link) {
        this.href = href;
        this.rel = rel;
        this.link = link;
    }

    public WebUrl() {
    }

    @XmlAttribute(name = "href")
    String href;

    @XmlAttribute(name = "rel")
    String rel;

    @XmlValue
    String link;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "WebUrl{" + "href=" + href + ", rel=" + rel + ", link=" + link + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.href);
        hash = 47 * hash + Objects.hashCode(this.rel);
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
        final WebUrl other = (WebUrl) obj;
        if (!Objects.equals(this.href, other.href)) {
            return false;
        }
        if (!Objects.equals(this.rel, other.rel)) {
            return false;
        }
        if (!Objects.equals(this.link, other.link)) {
            return false;
        }
        return true;
    }
    
    
}
