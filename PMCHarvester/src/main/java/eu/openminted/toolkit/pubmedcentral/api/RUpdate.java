/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.openminted.toolkit.pubmedcentral.api;

import com.jcabi.http.Request;
import java.io.IOException;

/**
 *
 * @author samuel
 */
public class RUpdate {

    private final transient Request req;

    public RUpdate(Request entry, String from, String until) {
        this.req = entry.uri()
                .queryParam("from", from)
                .queryParam("until", until)
                .back()
                .method(Request.GET);
    }
    
    public RUpdate(Request entry, RResumptionToken token) {
        this.req = entry.uri()
                .queryParam("resumptionToken", token.token())
                .back()
                .method(Request.GET);
    }

    public String fetch() throws IOException {
        return this.req.fetch().body();
    }

}
