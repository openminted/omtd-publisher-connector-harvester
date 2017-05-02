/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.openminted.toolkit.pubmedcentral.retriever.Message;

import com.google.gson.Gson;
import eu.openminted.toolkit.queue.ScheduledArticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author samuel
 */
public class MessageEvent {

    Logger logger = LoggerFactory.getLogger("MessageEvent");

    Gson gson;

    MessageEventCallback eventCallback;
    
    public MessageEvent(Gson gson, MessageEventCallback callback) {
        this.gson = gson;
        this.eventCallback = callback;
    }

    public void receiveMessage(String message) throws Exception {
        ScheduledArticle article = this.gson.fromJson(message, ScheduledArticle.class);
        
        this.eventCallback.callback(article);
    }

}
