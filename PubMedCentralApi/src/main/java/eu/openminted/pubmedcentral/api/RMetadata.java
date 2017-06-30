/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.openminted.pubmedcentral.api;

import com.jcabi.xml.XMLDocument;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author samuel
 */
public class RMetadata {

    private final XMLDocument xml;

    public RMetadata(String metadata) {
        this.xml = new XMLDocument(metadata);
    }

    public RMetadata(File file) throws IOException {
        this.xml = new XMLDocument(file);
    }

    public String publisher() throws IOException {
        try {
            return this.xml.xpath("//journal-meta/publisher//publisher-name/text()").get(0);
        } catch (Exception e) {
            return "";
        }
    }

    public String pmc() throws IOException {
        try {
            return this.xml.xpath("//article-meta/article-id[@pub-id-type='pmc']/text()").get(0);
        } catch (Exception e) {
            return "";
        }
    }
}
