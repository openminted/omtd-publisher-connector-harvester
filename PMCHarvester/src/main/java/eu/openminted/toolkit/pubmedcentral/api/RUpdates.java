/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.openminted.toolkit.pubmedcentral.api;

import com.jcabi.http.Request;

/**
 *
 * @author samuel
 */
public class RUpdates {

    private final transient RPubMedCentralOA pubMedCentralOA;

    private final transient Request entry;

    public RUpdates(RPubMedCentralOA pubMedCentralOA, Request request) {
        this.pubMedCentralOA = pubMedCentralOA;
        this.entry = request;
    }

    public RUpdate retrieve(final String from, final String until) {
        return new RUpdate(this.entry, from, until);
    }
    
    public RUpdate retrieve(final RResumptionToken token) {
        return new RUpdate(this.entry, token);
    }
    
    
    

    @Override
    public String toString() {
        return this.entry.uri().get().toString();
    }

}
