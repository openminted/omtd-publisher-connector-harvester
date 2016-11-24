package eu.openminted.toolkit.storage.impl;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author lucasanastasiou
 */
@SpringBootTest
public class HardDiskFilesystemStorageDAOTest {

    HardDiskFilesystemStorageDAO hardDiskFilesystemStorageDAO;

    public HardDiskFilesystemStorageDAOTest() {
        hardDiskFilesystemStorageDAO = new HardDiskFilesystemStorageDAO();
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testEncode() {
        String toBeEncoded = "10.1016/j.aap.2015.12.008";
        String toBeEncoded2 = "10.1016/B978-1-4832-3317-8.50009-1";
        String encodedString = hardDiskFilesystemStorageDAO.encodeToFilesystemSafe(toBeEncoded);
        String encodedString2 = hardDiskFilesystemStorageDAO.encodeToFilesystemSafe(toBeEncoded2);
        String expectedEncodedString = "MTAuMTAxNi9qLmFhcC4yMDE1LjEyLjAwOA==";
        System.out.println("encodedStirng = " + encodedString);
        System.out.println("encodedString2 = " + encodedString2);

        Assert.assertTrue(encodedString.equals(expectedEncodedString));
    }
    
    @Test
    public void testDecode() {
        String toBeDecoded = "MTAuMTAxNi9qLmFhcC4yMDE1LjEyLjAwOA==";
        String decodedString = hardDiskFilesystemStorageDAO.decodeFromFilesystemSafe(toBeDecoded);
        String expectedDecodedString = "10.1016/j.aap.2015.12.008";
        
        System.out.println("decodedString = " + decodedString);

        Assert.assertTrue(decodedString.equals(expectedDecodedString));
    }

}
