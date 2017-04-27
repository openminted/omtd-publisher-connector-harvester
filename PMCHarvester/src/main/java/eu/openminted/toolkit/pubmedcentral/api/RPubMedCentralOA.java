/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.openminted.toolkit.pubmedcentral.api;

import com.jcabi.http.Request;
import com.jcabi.http.request.ApacheRequest;
import com.jcabi.http.response.XmlResponse;
import com.jcabi.manifests.Manifests;
import java.io.IOException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author samuel
 */
public class RPubMedCentralOA {

    /**
     * This User agent
     */
    private static final String USER_AGENT = "github.com/openminted/omtd-publisher-connector-harvester (not Firefox, Chrome, or Safari)";

    /**
     * REST request.
     */
    private final transient Request request;

    /**
     * Public ctor, for anonymous access to Github.
     *
     * @param emailAddress
     * @since 0.4
     */
    public RPubMedCentralOA(final String emailAddress) {
        this(new ApacheRequest("https://www.ncbi.nlm.nih.gov/pmc/utils/oa/oa.fcgi")
                .header(HttpHeaders.USER_AGENT, RPubMedCentralOA.USER_AGENT)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.USER_AGENT, String.format(
                        "%s %s",
                        RPubMedCentralOA.USER_AGENT,
                        emailAddress
                )));
    }

    public RPubMedCentralOA(final Request req) {
        this.request = req;
    }

    /**
     *
     * @return @throws IOException
     */
    public RStatus status() throws IOException {
        return new RStatus(this, request);
    }

    /**
     *
     * @return @throws IOException
     */
    public RUpdates updates() throws IOException {
        return new RUpdates(this, request);
    }
}
