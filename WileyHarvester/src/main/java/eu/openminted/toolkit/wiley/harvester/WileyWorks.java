package eu.openminted.toolkit.wiley.harvester;

import com.jcabi.http.Request;
import com.jcabi.http.request.ApacheRequest;
import com.jcabi.http.response.JsonResponse;
import java.io.IOException;
import javax.json.JsonObject;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Samuel Pearce <samuel.pearce@open.ac.uk>
 */
public class WileyWorks {

    private final Request REQUEST = new ApacheRequest("https://api.crossref.org")
            .header(HttpHeaders.USER_AGENT, "JAVA")
            .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);


    public final JsonObject getWorks(String cursor) throws IOException {
        return this.REQUEST
                .uri()
                .path("/members")
                .path("/311")
                .path("/works")
                .queryParam("filter", "has-full-text:true,full-text.type:application/pdf")
                .queryParam("cursor", cursor).back()
                .method(Request.GET)
                .fetch()
                .as(JsonResponse.class)
                .json()
                .readObject()
                .getJsonObject("message");
    }
}
