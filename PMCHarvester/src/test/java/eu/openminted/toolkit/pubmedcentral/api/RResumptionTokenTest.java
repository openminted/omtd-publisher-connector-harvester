/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.openminted.toolkit.pubmedcentral.api;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author samuel
 */
public class RResumptionTokenTest {

    public RResumptionTokenTest() {
    }

    /**
     * Test of token method, of class RResumptionToken.
     */
    @Test
    public void testToken() throws IOException {
        System.out.println("token");
        String body = new String(java.nio.file.Files.readAllBytes(Paths.get("test-resources/UpdatesSaxHandlerResources/update.xml")), Charset.forName("UTF-8"));

        RResumptionToken instance = new RResumptionToken(body);

        String expResult = "70563!20041005124955!!!4a5f954e36e1f62c";
        String result = instance.token();
        assertEquals(expResult, result);
    }

    /**
     * Test of href method, of class RResumptionToken.
     */
    @Test
    public void testHref() throws IOException {
        System.out.println("href");
        String body = new String(java.nio.file.Files.readAllBytes(Paths.get("test-resources/UpdatesSaxHandlerResources/update.xml")), Charset.forName("UTF-8"));

        RResumptionToken instance = new RResumptionToken(body);

        String expResult = "http://www.ncbi.nlm.nih.gov/pmc/utils/oa/oa.fcgi?resumptionToken=70563%2120041005124955%21%21%214a5f954e36e1f62c";
        String result = instance.href();
        assertEquals(expResult, result);
    }

    /**
     * Test of token method, of class RResumptionToken.
     */
    @Test
    public void testTokenNoResumptionToken() throws IOException {
        System.out.println("TokenNoResumptionToken");
        String body = new String(java.nio.file.Files.readAllBytes(Paths.get("test-resources/UpdatesSaxHandlerResources/no-results.xml")), Charset.forName("UTF-8"));

        RResumptionToken instance = new RResumptionToken(body);

        String expResult = "";
        String result = instance.token();
        assertEquals(expResult, result);
    }

}
