package eu.openminted.toolkit.crossref;

import com.google.gson.Gson;
import eu.openminted.toolkit.crossref.model.member.Item;
import eu.openminted.toolkit.crossref.model.member.MemberResponse;
import eu.openminted.toolkit.crossref.model.member.Prefix;
import eu.openminted.toolkit.crossref.model.works.WorksResponse;
import eu.openminted.toolkit.crossref.model.multiple_works.MultipleWorksResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucasanastasiou
 */
@Service
public class CrossRefClient {

    private final String CROSSREF_ENDPOINT = "http://api.crossref.org/";
    private final String CROSSREF_SEARCH_ENDPOINT = "http://search.labs.crossref.org/";
    private final String SPRINGER_TDM_LICENSE_URL = "http://www.springer.com/tdm";

    private Client client = JerseyClientBuilder.newClient();

    private static final Logger logger = Logger.getLogger("CrossRefClient");

    public CrossRefClient() {

    }

    public List<Prefix> getPublisherPrefixes(String publisher) {
        WebTarget webTarget = client.target(CROSSREF_ENDPOINT).path("members").queryParam("query", publisher);
        Response r = webTarget.request().get();
        String responseString = r.readEntity(String.class);
        System.out.println("responseString = " + responseString);
        Gson gson = new Gson();
        MemberResponse memberResponse = gson.fromJson(responseString, MemberResponse.class);
        System.out.println("memberResponse = " + memberResponse);

        // TODO do this with java 8 stream instead 
        List<Prefix> prefixes = new ArrayList<>();
        for (Item item : memberResponse.getMessage().getItems()) {
            prefixes.addAll(item.getPrefix());
        }

        List<Prefix> prefixesWithStreams = memberResponse.getMessage().getItems()
                .stream()
                .map(Item::getPrefix)
                .collect(Collectors.toList())
                .stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        System.out.println("====");
        System.out.println("====");
        System.out.println("====");
        printPrefixList(prefixes, prefixesWithStreams);
        System.out.println("====");
        System.out.println("====");
        System.out.println("====");
        return prefixes;
    }

    private void printPrefixList(List<Prefix> list1, List<Prefix> list2) {
        list1.forEach(item -> System.out.println(item));
        System.out.println("-----");
        list2.forEach(item -> System.out.println(item));
    }
//    private boolean isPrefixListsEqual(List<Prefix> list1, List<Prefix> list2){
//        Collections.sort(list1,new Comparator<Prefix>(){
//            
//        };
//        Collections.sort(list2);
//    }

    public WorksResponse getByDoi(String doi) {
        // http://api.crossref.org/works?filter=license.url:http://www.springer.com/tdm
        WebTarget webTarget = client.target(CROSSREF_ENDPOINT)
                .path("works")
                .path(doi);
        Response worksResponse = webTarget.request().get();
        String responseString = worksResponse.readEntity(String.class);
        Gson gson = new Gson();
        WorksResponse worksResponse1 = gson.fromJson(responseString, WorksResponse.class);

        System.out.println("url = " + worksResponse1.getMessage().getURL());
        System.out.println("link = " + worksResponse1.getMessage().getLink());
        return worksResponse1;
    }

    public void populateQueue() {
        WebTarget webTarget = client.target(CROSSREF_ENDPOINT)
                .path("works")
                .path("10.1245/s10434-016-5211-6");
//                .queryParam("filter", "license.url:http://www.springer.com/tdm");
        Response worksResponse = webTarget.request().get();
        String responseString = worksResponse.readEntity(String.class);
//      
    }

    public List<eu.openminted.toolkit.crossref.model.multiple_works.Item> getPublisherItemsOfDate(String publisherPrefix, String month) {
        return getPublisherItemsOfDateFilteredByLicense(publisherPrefix, month, null);
    }

    /**
     * Deep scan (with cursor) all the items of a publisher with given prefix,
     * between the date provided and a month forward, filtered with the license
     * given. Essentially executing the following (example):
     * http://api.crossref.org/works?filter=from-deposit-date:2012-01,until-deposit-date:2012-02,prefix:10.1891,license.url:http://www.springer.com/tdm&cursor=*
     *
     * @param publisherPrefix
     * @param date
     * @param license
     * @return
     */
    public List<eu.openminted.toolkit.crossref.model.multiple_works.Item> getPublisherItemsOfDateFilteredByLicense(String publisherPrefix, String date, String license) {
        List<eu.openminted.toolkit.crossref.model.multiple_works.Item> resultItems = new ArrayList<>();

        String publisher_prefix_param = "prefix:"+publisherPrefix;
        String from_deposit_date = "from-created-date:" + date;
        String until_deposit_date = "until-created-date:" + date;
        String license_filter = "license.url:" + license;

        List<String> filters = new ArrayList<>();
        filters.add(publisher_prefix_param);
        filters.add(from_deposit_date);
        filters.add(until_deposit_date);
        if (license!=null){
            filters.add(license_filter);
        }
        String filterParam = String.join(",", filters);

        String nextCursor = "*";

        while (true) {
            WebTarget webTarget = client.target(CROSSREF_ENDPOINT)
                    .path("/works")
                    .queryParam("filter", filterParam)
                    .queryParam("cursor", nextCursor);

            logger.info(String.format("Hitting HTTP GET : %s", webTarget.getUri().toString()));

            Response lResponse = webTarget.request().get();

            String responseString = lResponse.readEntity(String.class);
            Gson gson = new Gson();
            MultipleWorksResponse multipleWorksResponse = gson.fromJson(responseString, MultipleWorksResponse.class);
            nextCursor = multipleWorksResponse.getMessage().getNextCursor();

            if (multipleWorksResponse == null) {
                logger.info("multiple works response is null");
                break;
            }
            if (multipleWorksResponse.getMessage() == null) {
                logger.info("multiple works response message is null");
                break;
            }
            if (multipleWorksResponse.getMessage().getItems() == null) {
                logger.info("multiple works response message items is null");
                break;
            }

            if (multipleWorksResponse.getMessage().getItems().isEmpty()) {
                logger.info("multiple works response message items is empty");
                break;
            }

            
            resultItems.addAll(multipleWorksResponse.getMessage().getItems());
//            multipleWorksResponse.getMessage().getItems().forEach(item -> {
//                if (item.getLink() != null && !item.getLink().isEmpty()) {
////                    item.getLink().forEach(linkItem -> System.out.println("Scheduling ...:" + linkItem.getURL()));
//                    resultItems.add(item);
//                }
//            });

        }

        
        return resultItems;
    }

    public void filterByLicense(String license) {
        String nextCursor = "*";

        for (int i = 0; i < 190; i++) {
            System.out.println("\n" + i + " request\n=============\n");
            WebTarget webTarget = client.target(CROSSREF_ENDPOINT)
                    .path("works")
                    .queryParam("filter", "from-pub-date:2002-01,until-pub-date:2002-01,license.url:" + license)
                    .queryParam("cursor", nextCursor);

            Response lResponse = webTarget.request().get();

            String responseString = lResponse.readEntity(String.class);
            Gson gson = new Gson();
            MultipleWorksResponse multipleWorksResponse = gson.fromJson(responseString, MultipleWorksResponse.class);

            List<eu.openminted.toolkit.crossref.model.multiple_works.Item> items = multipleWorksResponse.getMessage().getItems();
            //items.forEach(item -> System.out.println(item.getDOI()));
            items.forEach(item -> {
                if (item.getLink() != null && !item.getLink().isEmpty()) {
                    item.getLink().forEach(linkItem -> System.out.println(linkItem.getURL()));
                }
            });
            nextCursor = multipleWorksResponse.getMessage().getNextCursor();
        }

    }

    public void testDois() {
        WebTarget webTarget = client.target(CROSSREF_SEARCH_ENDPOINT)
                .path("dois")
                .queryParam("q", "Carberry%2C+Josiah.+%E2%80%9CToward+a+Unified+Theory+of+High-Energy+Metaphysics%3A+Silly+String+Theory.%E2%80%9D+Journal+of+Psychoceramics+5.11+%282008%29%3A+1-3.#");

        Response r = webTarget.request().get();
        String responseString = r.readEntity(String.class);

        System.out.println("response as string : " + responseString);

    }

    public static void main(String[] args) {
        CrossRefClient cl = new CrossRefClient();
//        List<Prefix> prefixes = cl.getPublisherPrefixes("springer");
//        cl.a();
        cl.filterByLicense("http://www.springer.com/tdm");
//        cl.testDois();
    }
}
