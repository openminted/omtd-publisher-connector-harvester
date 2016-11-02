package eu.openminted.toolkit.database.services;

import eu.openminted.toolkit.database.exceptions.DatabaseException;
import eu.openminted.toolkit.database.model.CrawlVisit;
import java.util.List;

/**
 *
 * @author lucasanastasiou
 */
public interface SitemapCrawlDAO {

    public void addSitemapCrawling(CrawlVisit crawlVisit) throws DatabaseException;

    public void addSitemapCrawling(Integer id, String url, String parent_url, int level, String filename) throws DatabaseException;

    public List<CrawlVisit> getCrawlVisitsByLevel(int level, int size) throws DatabaseException;
}
