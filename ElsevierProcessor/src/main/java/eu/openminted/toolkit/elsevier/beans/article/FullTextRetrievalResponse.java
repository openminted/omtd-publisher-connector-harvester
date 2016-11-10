package eu.openminted.toolkit.elsevier.beans.article;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lucasanastasiou
 */
@XmlRootElement(name = "full-text-retrieval-response")
public class FullTextRetrievalResponse {

    CoreData coredata;

    public CoreData getCoredata() {
        return coredata;
    }

    public void setCoredata(CoreData coredata) {
        this.coredata = coredata;
    }

    @Override
    public String toString() {
        return "FullTextRetrievalResponse{" + "coredata=" + coredata + '}';
    }


}
