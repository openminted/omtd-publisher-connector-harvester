package eu.openminted.toolkit.elsevier.beans.leafnode;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lucasanastasiou
 */
@XmlRootElement(name="html")
public class ArticleResponse {

    private HtmlHead head;
    private HtmlBody body;

    public HtmlHead getHead() {
        return head;
    }

    public void setHead(HtmlHead head) {
        this.head = head;
    }

    public HtmlBody getBody() {
        return body;
    }

    public void setBody(HtmlBody body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "ArticleResponse{" + "head=" + head + ", body=" + body + '}';
    }
    
    
}
