/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.openminted.pubmedcentral.api;

import com.jcabi.http.Request;
import com.jcabi.http.response.XmlResponse;
import com.jcabi.xml.XML;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author samuel
 */
public class RStatus {

    private final transient RPubMedCentralOA pubMedCentralOA;

    private final transient Request entry;

    public RStatus(RPubMedCentralOA pubMedCentralOA, Request request) {
        this.pubMedCentralOA = pubMedCentralOA;
        this.entry = request;
    }

    public XML fetch() throws IOException {
        return this.entry.fetch()
                .as(XmlResponse.class)
                .xml();
    }

    public String count() throws IOException {
        return this.fetch().xpath("OA/records/count/text()").get(0);
    }

    public String earliest() throws IOException {
        return this.fetch().xpath("OA/records/earliest/text()").get(0);
    }

    public String latest() throws IOException {
        return this.fetch().xpath("OA/records/latest/text()").get(0);
    }
}
