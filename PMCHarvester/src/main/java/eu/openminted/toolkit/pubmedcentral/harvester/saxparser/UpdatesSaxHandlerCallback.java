/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.openminted.toolkit.pubmedcentral.harvester.saxparser;

/**
 *
 * @author samuel
 */
public interface UpdatesSaxHandlerCallback {
    
    void callback(UpdateRecord updateRecord);
    
}
