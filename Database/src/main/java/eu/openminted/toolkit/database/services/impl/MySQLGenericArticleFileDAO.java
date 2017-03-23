package eu.openminted.toolkit.database.services.impl;

import eu.openminted.toolkit.database.exceptions.DatabaseException;
import eu.openminted.toolkit.database.services.GenericArticleFileDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author lucasanastasiou
 */
public class MySQLGenericArticleFileDAO implements GenericArticleFileDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Logger logger = LoggerFactory.getLogger("MySQLGenericArticleFileDAO");

    @Override
    public void insertNewArticle(String publisherPrefix, String doi, String metadata) throws DatabaseException {
        String INSERT_NEW_ARTICLE_FILES_SQL = "INSERT INTO generic_files(publisher_prefix,doi,metadata) VALUES(?,?,?)";
        try {
            jdbcTemplate.update(INSERT_NEW_ARTICLE_FILES_SQL, publisherPrefix, doi, metadata);
        } catch (DataAccessException e) {
            logger.error("Cannot insert new article file", e);
            throw new DatabaseException("Cannot insert new article file");
        }
    }

    @Override
    public boolean isArticleDownloaded(String publisherPrefix, String doi) throws DatabaseException {
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM generic_files WHERE doi = ? AND pdf_filename IS NOT NULL)", Integer.class, doi);
        return (cnt != null && cnt > 0);
    }

}
