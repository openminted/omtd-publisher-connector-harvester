package eu.openminted.toolkit.elsevier.beans.leafnode;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lucasanastasiou
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "body")
public class HtmlBody {

    @XmlElement(name = "a")
    List<WebUrl> webUrls;

    public List<WebUrl> getWebUrls() {
        return webUrls;
    }

    public void setWebUrls(List<WebUrl> webUrls) {
        this.webUrls = webUrls;
    }

    @Override
    public String toString() {
        return "HtmlBody{" + "webUrls=" + webUrls + '}';
    }
    
    
}
