package eu.openminted.toolkit.springer.api;

import eu.openminted.toolkit.springer.api.entities.ApiResponse;
import eu.openminted.toolkit.springer.api.entities.Record;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.JerseyClientBuilder;

/**
 *
 * @author lucasanastasiou
 */
public class SpringerAPIClient {

    private final String SPRINGER_API_ENDPOINT = "http://api.springer.com";

    private Client client = JerseyClientBuilder.newClient();

    private static final Logger logger = Logger.getLogger("SpringerAPIClient");

    private String apiKey;

    public SpringerAPIClient(String apiKey) {
        this.apiKey = apiKey;
    }

    public List<Record> getRecordsOfDay(String day) {

        List<Record> results = new ArrayList<>();

        String dateQueryParam = "date:" + day;

        WebTarget webTarget = client.target(SPRINGER_API_ENDPOINT)
                .path("openaccess")
                .path("json")
                .queryParam("api_key", apiKey)
                .queryParam("q", dateQueryParam)
                .queryParam("s", "1")
                .queryParam("p", SpringerAPIConstants.MAX_PAGE_SIZE.toString());
        
        logger.info(String.format("Hitting HTTP GET : %s", webTarget.getUri().toString()));
        
        Response r = webTarget.request().get();
        String responseString = r.readEntity(String.class);
        Gson gson = new Gson();
        ApiResponse apiResponse = gson.fromJson(responseString, ApiResponse.class);

        int total = Integer.parseInt(apiResponse.getResult().get(0).getTotal());

        if (total > 0) {
            results.addAll(apiResponse.getRecords());
        }
        if (total > SpringerAPIConstants.MAX_PAGE_SIZE) {
            for (Integer offset = SpringerAPIConstants.MAX_PAGE_SIZE + 1; offset < total; offset += SpringerAPIConstants.MAX_PAGE_SIZE) {

                webTarget = client.target(SPRINGER_API_ENDPOINT)
                        .path("openaccess")
                        .path("json")
                        .queryParam("api_key", apiKey)
                        .queryParam("q", dateQueryParam)
                        .queryParam("s", offset.toString())
                        .queryParam("p", SpringerAPIConstants.MAX_PAGE_SIZE.toString());
                
                logger.info(String.format("Hitting HTTP GET : %s", webTarget.getUri().toString()));
                
                r = webTarget.request().get();
                responseString = r.readEntity(String.class);
                apiResponse = gson.fromJson(responseString, ApiResponse.class);
                
                results.addAll(apiResponse.getRecords());
            }
        }
        return results;
    }

    public static void main(String args[]) {
        SpringerAPIClient cl = new SpringerAPIClient("fdcc8721a1359724c6260b04d2ced9b2");

        List<Record> records = cl.getRecordsOfDay("2017-04-01");
        
        records.forEach(item -> System.out.println(item.getIdentifier()));
    }

}
