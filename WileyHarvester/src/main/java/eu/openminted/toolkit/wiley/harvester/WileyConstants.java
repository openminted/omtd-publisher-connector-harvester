package eu.openminted.toolkit.wiley.harvester;

import com.jcabi.aspects.Cacheable;
import com.jcabi.http.Request;
import com.jcabi.http.request.ApacheRequest;
import com.jcabi.http.response.JsonResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Samuel Pearce <samuel.pearce@open.ac.uk>
 */
public class WileyConstants {

    private final Request REQUEST = new ApacheRequest("https://api.crossref.org")
            .header(HttpHeaders.USER_AGENT, "JAVA")
            .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);

    public WileyConstants() {
    }   
    
    public final JsonArray listLicense() throws IOException {
        return this.REQUEST
                .uri().path("/licenses").queryParam("query", "Wiley").back()
                .method(Request.GET)
                .fetch()
                .as(JsonResponse.class)
                .json()
                .readObject()
                .getJsonObject("message")
                .getJsonArray("items");
    }

    @Cacheable(forever = true)
    public final JsonArray listPrefix() throws IOException {
        return this.REQUEST
                .uri().path("/members").queryParam("query", "Wiley").back()
                .method(Request.GET)
                .fetch()
                .as(JsonResponse.class)
                .json()
                .readObject()
                .getJsonObject("message")
                .getJsonArray("items")
                .getJsonObject(0)
                .getJsonArray("prefixes");
    }
}
