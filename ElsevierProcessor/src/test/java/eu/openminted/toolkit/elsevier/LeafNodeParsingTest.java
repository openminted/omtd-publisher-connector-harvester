package eu.openminted.toolkit.elsevier;

import eu.openminted.toolkit.elsevier.beans.leafnode.ArticleResponse;
import eu.openminted.toolkit.elsevier.beans.leafnode.WebUrl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 *
 * @author lucasanastasiou
 */
@RunWith(JUnit4.class)
public class LeafNodeParsingTest {

    JAXBContext jc = null;

    @Before
    public void initContext() {
        try {
            jc = JAXBContext.newInstance(ArticleResponse.class);
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void parseLinksTest() {
        try {
            File testXmlFile = new File("src/test/resources/leafnode-journal-test.sitemap.xml");
            String testXmlAsString = FileUtils.readFileToString(testXmlFile, "UTF-8");
            
            testXmlAsString = testXmlAsString.replaceAll("\\<BR\\>", "");
            
            System.out.println("testXmlAsString = " + testXmlAsString);

            Unmarshaller unmarshaller = jc.createUnmarshaller();
            ArticleResponse articleResponse = (ArticleResponse) unmarshaller.unmarshal(IOUtils.toInputStream(testXmlAsString,"UTF-8"));

            System.out.println("article = " + articleResponse);

            List<WebUrl> parsedUrls = articleResponse.getBody().getWebUrls();

            WebUrl expectedUrl = new WebUrl("http://api.elsevier.com/content/article/pii/S1522472400902472",null,"S1522472400902472");
            Assert.assertTrue(parsedUrls.contains(expectedUrl));
        } catch (IOException ex) {
            Logger.getLogger(LeafNodeParsingTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JAXBException ex) {
            Logger.getLogger(LeafNodeParsingTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
