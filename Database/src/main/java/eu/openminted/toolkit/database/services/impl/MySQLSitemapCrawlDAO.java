package eu.openminted.toolkit.database.services.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Service;
import eu.openminted.toolkit.database.services.SitemapCrawlDAO;
import eu.openminted.toolkit.database.exceptions.DatabaseException;
import eu.openminted.toolkit.database.model.CrawlVisit;
import java.sql.ResultSet;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author lucasanastasiou
 */
@Service
public class MySQLSitemapCrawlDAO implements SitemapCrawlDAO {

    private final Logger logger = Logger.getLogger("MySQLSitemapCrawlDAO");
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void addSitemapCrawling(CrawlVisit crawlVisit) throws DatabaseException {
        addSitemapCrawling(crawlVisit.getId(), crawlVisit.getUrl(), crawlVisit.getParentUrl(), crawlVisit.getLevel(), crawlVisit.getFilename());
    }

    @Override
    public void addSitemapCrawling(Integer id, String url, String parent_url, int level, String filename) throws DatabaseException {
        String urlHash = DigestUtils.sha1Hex(url);
        String SQL = "INSERT INTO sitemaps_crawling(`id`,`url`,`url_hex_hash`,`parent_url`,`level`,`filename`) "
                + "VALUES(?,?,?,?,?,?) "
                + "ON DUPLICATE KEY UPDATE filename=?";

        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, id);
                ps.setString(2, url);
                ps.setString(3, urlHash);
                ps.setString(4, parent_url);
                ps.setInt(5, level);
                ps.setString(6, filename);
                ps.setString(7, filename);
            }
        };
        try {
            jdbcTemplate.update(SQL, preparedStatementSetter);
        } catch (DataAccessException dataAccessException) {
            logger.error("Sql exception : " + dataAccessException.getMessage());
            throw new DatabaseException("Error while inserting a new sitemap crawl", dataAccessException);
        }
    }

    @Override
    public List<CrawlVisit> getCrawlVisitsByLevel(int level, int size) throws DatabaseException {
        String SQL = "SELECT * FROM sitemaps_crawling WHERE level=? LIMIT ?";

        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, level);
                ps.setInt(2, size);
            }
        };

        RowMapper<CrawlVisit> rowMapper = new SitemapCrawlRowMapper();

        try {
            List<CrawlVisit> crawlVisits = jdbcTemplate.query(SQL, preparedStatementSetter, rowMapper);
            return crawlVisits;
        } catch (DataAccessException dataAccessException) {
            logger.error("Sql exception : " + dataAccessException.getMessage());
            throw new DatabaseException("Error while retrieving sitemaps crawlings by level", dataAccessException);
        }
    }

    @Override
    public CrawlVisit getSitemapByUrl(String href) {
        String urlHash = DigestUtils.sha1Hex(href);
        String SQL = "SELECT * FROM sitemaps_crawling WHERE url_hex_hash=?";
        RowMapper<CrawlVisit> rowMapper = new SitemapCrawlRowMapper();
        try {
            CrawlVisit crawlVisit = jdbcTemplate.queryForObject(SQL, rowMapper, urlHash);
            return crawlVisit;
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (DataAccessException dataAccessException) {
            logger.error("Cannot retireve sitemap by url :" + href);
            return null;
        }
    }

    class SitemapCrawlRowMapper implements RowMapper<CrawlVisit> {

        @Override
        public CrawlVisit mapRow(ResultSet rs, int i) throws SQLException {
            CrawlVisit crawlVisit = new CrawlVisit();
            crawlVisit.setId(rs.getInt(1));
            crawlVisit.setUrl(rs.getString(2));
            crawlVisit.setParentUrl(rs.getString(4));
            crawlVisit.setTimeCrawled(rs.getTimestamp(5));
            crawlVisit.setLevel(rs.getInt(6));
            crawlVisit.setFilename(rs.getString(7));
            return crawlVisit;
        }
    }
    
    

}
