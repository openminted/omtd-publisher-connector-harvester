/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.openminted.pubmedcentral.api.saxparser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author samuel
 */
public class UpdatesSaxHandler extends DefaultHandler {

    private final UpdatesSaxHandlerCallback callback;

    public UpdatesSaxHandler(UpdatesSaxHandlerCallback callback) {
        this.callback = callback;
    }

    private boolean record = false;
    private boolean link = false;

    private UpdateRecord updateRecord;

    @Override
    public void startElement(String uri,
            String localName, String qName, Attributes attributes)
            throws SAXException {

        if (qName.equalsIgnoreCase("record")) {
            record = true;
            updateRecord = new UpdateRecord();
            updateRecord.setId(attributes.getValue("id"));
            updateRecord.setCitation(attributes.getValue("citation"));
        }

        if (this.record && qName.equalsIgnoreCase("link")) {
            updateRecord.setLinkFormat(attributes.getValue("format"));
            updateRecord.setLinkUpdated(attributes.getValue("updated"));
            updateRecord.setLinkHref(attributes.getValue("href"));
        }
    }

    @Override
    public void endElement(String uri,
            String localName, String qName) throws SAXException {

        if (qName.equalsIgnoreCase("link")) {
            this.link = false;
        }

        if (qName.equalsIgnoreCase("record")) {
            if (this.callback != null) {
                callback.callback(this.updateRecord);
            }
            this.record = false;
            this.updateRecord = null;
        }
    }
}
