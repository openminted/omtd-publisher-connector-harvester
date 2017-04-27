/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.openminted.toolkit.pubmedcentral.api;

import com.jcabi.xml.XMLDocument;
import java.util.List;

/**
 *
 * @author samuel
 */
public class RResumptionToken {

    private XMLDocument body;

    public RResumptionToken(String body) {
        this.body = new XMLDocument(body);
    }

    /**
     * Returns the resumption token as a string. 
     *
     * @return ResumptionToken or empty string
     */
    public String token() {
        return this.firstItemOrEmptyString(this.body.xpath("OA/records/resumption/link/@token"));
    }

    /**
     * Returns the full url, as a string, of the next page
     * @return 
     */
    public String href() {
        return this.firstItemOrEmptyString(this.body.xpath("OA/records/resumption/link/@href"));
    }

    /**
     * Checks if the resumptionToken is empty
     * @return true if there is no resumptionToken in the body
     */
    public boolean isEmpty() {
        return this.token().isEmpty();
    }

    /**
     * Returns the first item in a list or an empty string.
     * 
     * TODO: this smells bad, how can it be improved? Create a new object and decorate this one?
     * @param list 
     * @return 
     */
    private String firstItemOrEmptyString(List<String> list) {
        String value = "";
        if (!list.isEmpty()) {
            value = list.get(0);
        }
        return value;
    }
}
