/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.openminted.toolkit.pubmedcentral.harvester.saxparser;

import eu.openminted.pubmedcentral.api.saxparser.UpdatesSaxHandler;
import eu.openminted.pubmedcentral.api.saxparser.UpdatesSaxHandlerCallback;
import eu.openminted.pubmedcentral.api.saxparser.UpdateRecord;
import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.junit.Test;
import static org.junit.Assert.*;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author samuel
 */
public class UpdatesSaxHandlerTest {

    @Test
    public void testParsingXml() throws Exception {
        File inputFile = new File("test-resources/UpdatesSaxHandlerResources/update.xml");
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();

        UpdatesSaxHandlerCallback u = (UpdateRecord updateRecord) -> {
            assertEquals("PMC3405500", updateRecord.getId());
            assertEquals("J Exp Med. 2012 Jul 2; 209(7):1325-1334", updateRecord.getCitation());
            assertEquals("tgz", updateRecord.getLinkFormat());
            assertEquals("2013-01-02 00:20:58", updateRecord.getLinkUpdated());
            assertEquals("ftp://ftp.ncbi.nlm.nih.gov/pub/pmc/oa_package/e3/77/PMC3405500.tar.gz", updateRecord.getLinkHref());
        };

        DefaultHandler userhandler = new UpdatesSaxHandler(u);
        saxParser.parse(inputFile, userhandler);
    }

}
