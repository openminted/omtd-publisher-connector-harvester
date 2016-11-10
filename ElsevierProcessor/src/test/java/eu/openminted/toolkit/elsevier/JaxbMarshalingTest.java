package eu.openminted.toolkit.elsevier;

import eu.openminted.toolkit.elsevier.beans.article.CoreData;
import eu.openminted.toolkit.elsevier.beans.article.FullTextRetrievalResponse;
import eu.openminted.toolkit.elsevier.beans.article.Link;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import org.apache.commons.io.FileUtils;
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
//@ContextConfiguration(classes = {TestConfiguration.class})
public class JaxbMarshalingTest {

    JAXBContext jc = null;
    FullTextRetrievalResponse testObject;

    @Before
    public void initContext() {
        try {
            jc = JAXBContext.newInstance(FullTextRetrievalResponse.class);
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
    }
    
    @Before
    public void initTestObject(){
        testObject = new FullTextRetrievalResponse();
            CoreData coreData = new CoreData();
            coreData.setDc_identifier("doi:10.1016/S0140-6736(16)31322-8");
            coreData.setEid("1-s2.0-S0140673616313228");
            coreData.setDoi("10.1016/S0140-6736(16)31322-8");
            coreData.setPrism_url("http://api.elsevier.com/content/article/pii/S0140673616313228");
            coreData.setDc_title("Benralizumab, an anti-interleukin-5 receptor α monoclonal antibody, "
                    + "as add-on treatment for patients with severe, uncontrolled, eosinophilic asthma (CALIMA): "
                    + "a randomised, double-blind, placebo-controlled phase 3 trial");
            coreData.setPrism_publicationName("The Lancet");
            coreData.setPrism_aggregationType("Journal");
            coreData.setPrism_issn("01406736");
            coreData.setPii("S0140-6736(16)31322-8");
            coreData.setOpenaccess(0);
            coreData.setOpenaccessArticle(false);
            coreData.setOpenaccessType(null);
            coreData.setOpenArchiveArticle(false);
            coreData.setOpenaccessSponsorName(null);
            coreData.setOpenaccessUserLicense(null);

            Link selfLink = new Link();
            selfLink.setHref("http://api.elsevier.com/content/article/pii/S0140673616313228");
            selfLink.setRel("self");
            Link scidirLink = new Link();
            scidirLink.setHref("http://www.sciencedirect.com/science/article/pii/S0140673616313228");
            scidirLink.setRel("scidir");
            List<Link> link = new ArrayList<>();
            link.add(selfLink);
            link.add(scidirLink);
            coreData.setLink(link);
            testObject.setCoredata(coreData);
    }

    @Test
    public void shoudDeserialise() {
        try {
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            File testXml = new File("src/test/resources/S0140673616313228");
            FullTextRetrievalResponse deserialisedObject = (FullTextRetrievalResponse) unmarshaller.unmarshal(testXml);

            Assert.assertNotNull(deserialisedObject);
            Assert.assertEquals(deserialisedObject.getCoredata().getDc_identifier(),testObject.getCoredata().getDc_identifier());
            Assert.assertEquals(deserialisedObject.getCoredata().getLink(), testObject.getCoredata().getLink());
            Assert.assertEquals(deserialisedObject.getCoredata().getOpenaccess(),testObject.getCoredata().getOpenaccess());
            Assert.assertEquals(deserialisedObject.getCoredata().isOpenaccessArticle(), testObject.getCoredata().isOpenaccessArticle());
            
//            Assert.assertEquals(deserialisedObject, testObject);
            
            System.out.println("Deserialized object :\n" + deserialisedObject.toString());
            System.out.println("\nTestObject :\n"+testObject.toString());
        } catch (JAXBException jAXBException) {
            jAXBException.printStackTrace();
        }
    }

    @Test
    public void shouldSerialise() {
        Marshaller marshaller = null;
        try {
            marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
//            marshaller.setProperty("com.sun.xml.bind.xmlDeclaration", Boolean.FALSE);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

            Writer writer = new StringWriter();
            marshaller.marshal(testObject, writer);
            
            String serialised = writer.toString();
//            serialised.replaceAll("<\\?xml version=\"1.0\" encoding=\"UTF\\-8\" standalone=\"yes\"\\?>", "");
            String testXmlAsString = FileUtils.readFileToString(new File("src/test/resources/S0140673616313228"), "UTF-8");
            
            System.out.println("serialised test object :\n"+serialised);
            System.out.println("test xml :\n"+testXmlAsString);
            
//            Assert.assertEquals(asString, testXmlAsString);
        } catch (PropertyException ex) {
            ex.printStackTrace();
        } catch (JAXBException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
