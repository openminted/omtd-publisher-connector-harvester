package eu.openminted.toolkit.elsevier;

import eu.openminted.toolkit.elsevier.beans.article.FullTextRetrievalResponse;
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
public class ArticleMetadataParserTest {

    JAXBContext jc = null;

    @Before
    public void initContext() {
        try {
            jc = JAXBContext.newInstance(FullTextRetrievalResponse.class);
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void nonOpenAccessTest() {
        try {
            File testXmlFile = new File("src/test/resources/sample-article-non-OA.xml");
            String testXmlAsString = FileUtils.readFileToString(testXmlFile, "UTF-8");

            Unmarshaller unmarshaller = jc.createUnmarshaller();
            FullTextRetrievalResponse articleResponse = (FullTextRetrievalResponse) unmarshaller.unmarshal(IOUtils.toInputStream(testXmlAsString,"UTF-8"));
            
            Assert.assertTrue(false==articleResponse.getCoredata().isOpenaccessArticle());
        } catch (IOException ex) {
            Logger.getLogger(ArticleMetadataParserTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JAXBException ex) {
            Logger.getLogger(ArticleMetadataParserTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void openAccessTest() {
        try {
            File testXmlFile = new File("src/test/resources/sample-article-OA.xml");
            String testXmlAsString = FileUtils.readFileToString(testXmlFile, "UTF-8");

            Unmarshaller unmarshaller = jc.createUnmarshaller();
            FullTextRetrievalResponse articleResponse = (FullTextRetrievalResponse) unmarshaller.unmarshal(IOUtils.toInputStream(testXmlAsString,"UTF-8"));
            
            Assert.assertTrue(true==articleResponse.getCoredata().isOpenaccessArticle());
        } catch (IOException ex) {
            Logger.getLogger(ArticleMetadataParserTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JAXBException ex) {
            Logger.getLogger(ArticleMetadataParserTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
