package eu.openminted.toolkit.elsevier.beans.article;

import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lucasanastasiou
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "link")
public class Link {

    @XmlAttribute(name = "href")
    String href;
    @XmlAttribute(name = "rel")
    String rel;

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

    @Override
    public String toString() {
        return "Link{" + "href=" + href + ", rel=" + rel + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.href);
        hash = 97 * hash + Objects.hashCode(this.rel);
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
        final Link other = (Link) obj;
        if (!Objects.equals(this.href, other.href)) {
            return false;
        }
        if (!Objects.equals(this.rel, other.rel)) {
            return false;
        }
        return true;
    }
    
    

}
