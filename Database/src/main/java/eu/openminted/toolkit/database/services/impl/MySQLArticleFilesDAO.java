package eu.openminted.toolkit.database.services.impl;

import eu.openminted.toolkit.database.exceptions.DatabaseException;
import eu.openminted.toolkit.database.services.ArticleFilesDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucasanastasiou
 */
@Service
public class MySQLArticleFilesDAO implements ArticleFilesDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Logger logger = LoggerFactory.getLogger("MySQLArticleFilesDAO");

    @Override
    public void insertNewArticleFiles(long id, String doi) throws DatabaseException {
        String INSERT_NEW_ARTICLE_FILES_SQL = "INSERT INTO files(id,doi) VALUES(?,?)";
        try {
            jdbcTemplate.update(INSERT_NEW_ARTICLE_FILES_SQL, id, doi);
        } catch (DataAccessException e) {
            logger.error("Cannot insert new article file", e);
            throw new DatabaseException("Cannot insert new article file");
        }
    }

    @Override
    public void setMetadataFilename(long id, String doi, String metadataFilenamePath) throws DatabaseException {
        String SET_METADATA_FILENAME_SQL = "UPDATE files SET metadata_filename=? WHERE id=? AND doi=?";
        try {
            jdbcTemplate.update(SET_METADATA_FILENAME_SQL, metadataFilenamePath, id, doi);
        } catch (DataAccessException e) {
            logger.error("Cannot set metadata filename", e);
            throw new DatabaseException("Cannot set metadata filename");
        }
    }

    @Override
    public void setPdfFilename(long id, String doi, String pdfFilenamePath) throws DatabaseException {
        String SET_PDF_FILENAME_SQL = "UPDATE files SET pdf_filename=? WHERE id=? AND doi=?";
        try {
            jdbcTemplate.update(SET_PDF_FILENAME_SQL, pdfFilenamePath, id, doi);
        } catch (DataAccessException e) {
            logger.error("Cannot set pdf filename", e);
            throw new DatabaseException("Cannot set pdf filename");
        }
    }

}
