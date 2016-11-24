package eu.openminted.toolkit.elsevier.processor;

import eu.openminted.toolkit.database.model.CrawlVisit;
import eu.openminted.toolkit.elsevier.beans.leafnode.ArticleResponse;
import eu.openminted.toolkit.elsevier.beans.leafnode.WebUrl;
import eu.openminted.toolkit.queue.LeafNode;
import eu.openminted.toolkit.storage.StorageDAO;
import eu.openminted.toolkit.storage.exceptions.StorageException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucasanastasiou
 */
@Service
public class LeafNodeParser {

    @Autowired
    StorageDAO storageDAO;

    Unmarshaller unmarshaller = null;

    Logger logger = Logger.getLogger("LeafNodeParser");

    public LeafNodeParser() {
        try {
            JAXBContext jc = JAXBContext.newInstance(ArticleResponse.class);
            unmarshaller = jc.createUnmarshaller();
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
    }

    public List<WebUrl> parseLinks(String htmlContent) {
        try {
            ArticleResponse articleResponse = getArticleResponseObjectFromHtmlContent(htmlContent);
            List<WebUrl> parsedUrls = articleResponse.getBody().getWebUrls();
            return parsedUrls;
        } catch (IOException | JAXBException exception) {
            logger.error("Cannot parse html of leaf node " + exception.getMessage());
            return null;
        }
    }
    
    private ArticleResponse getArticleResponseObjectFromHtmlContent(String htmlContent) throws JAXBException,IOException{
        //unformatted html (non-ending BR elements), remove 
        htmlContent = htmlContent.replaceAll("\\<BR\\>", "");
        
        ArticleResponse articleResponse = (ArticleResponse) unmarshaller.unmarshal(IOUtils.toInputStream(htmlContent, "UTF-8"));
        return articleResponse;
    }

    public List<String> getArticleUrls(String htmlContent) {
        List<WebUrl> parsedUrls = parseLinks(htmlContent);
        // first url is usually the self-reference link
        List<String> articleUrls = new ArrayList<>();
        for (WebUrl parsedUrl : parsedUrls) {
            String href = parsedUrl.getHref().toLowerCase();
            if (href.contains("article") && !href.contains("nonserial")) {
                articleUrls.add(href);
            }
        }
        return articleUrls;
    }
    
    public List<String> getArticleUrls(LeafNode leafNode) {
        String filename = leafNode.getFilename();
        String content = getFileContents(filename);
        return getArticleUrls(content);
    }

    public List<String> getArticleUrls(CrawlVisit crawlVisit) {
        String filename = crawlVisit.getFilename();
        String htmlContent = getFileContents(filename);
        return getArticleUrls(htmlContent);
    }

    public String getFileContents(String filename) {
        try {
            String contents = storageDAO.getFileContents(filename);
            return contents;
        } catch (StorageException exception) {
            logger.error("Cannot get contents of file " + filename, exception);
            return null;
        }
    }
}
