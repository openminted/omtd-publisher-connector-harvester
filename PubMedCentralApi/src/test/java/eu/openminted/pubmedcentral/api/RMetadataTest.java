/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.openminted.pubmedcentral.api;

import eu.openminted.pubmedcentral.api.RMetadata;
import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author samuel
 */
public class RMetadataTest {

    public RMetadataTest() {
    }

    /**
     * Test of publisher method, of class RMetadata.
     */
    @Test
    public void testPublisher() throws Exception {
        System.out.println("publisher");

        RMetadata metadata = new RMetadata(new File("test-resources/api/publishername.nxml"));

        String expResult = "BioMed Central";
        String result = metadata.publisher();
        assertEquals(expResult, result);
    }

    /**
     * Test of publisher method, of class RMetadata.
     */
    @Test
    public void testNoPublisher() throws Exception {
        System.out.println("publisher");

        RMetadata metadata = new RMetadata(new File("test-resources/api/nopublishername.nxml"));

        String expResult = "";
        String result = metadata.publisher();
        assertEquals(expResult, result);
    }

    /**
     * Test of publisher method, of class RMetadata.
     */
    @Test
    public void testPmc() throws Exception {
        System.out.println("publisher");

        RMetadata metadata = new RMetadata(new File("test-resources/api/publishername.nxml"));

        String expResult = "13901";
        String result = metadata.pmc();
        assertEquals(expResult, result);
    }

}
