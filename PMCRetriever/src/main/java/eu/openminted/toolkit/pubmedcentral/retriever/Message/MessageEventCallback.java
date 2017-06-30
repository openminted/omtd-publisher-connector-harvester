/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.openminted.toolkit.pubmedcentral.retriever.Message;

import eu.openminted.toolkit.queue.ScheduledArticle;

/**
 *
 * @author samuel
 */
public interface MessageEventCallback {
    
    public void callback(ScheduledArticle article) throws Exception;
    
}
