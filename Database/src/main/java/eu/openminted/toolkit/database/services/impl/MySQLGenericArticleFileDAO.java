package eu.openminted.toolkit.database.services.impl;

import eu.openminted.toolkit.database.exceptions.DatabaseException;
import eu.openminted.toolkit.database.model.GenericFile;
import eu.openminted.toolkit.database.services.GenericArticleFileDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucasanastasiou
 */
@Service
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
                "SELECT count(*) FROM generic_files WHERE doi = ? AND pdf_filename IS NOT NULL", Integer.class, doi);
        return (cnt != null && cnt > 0);
    }

    @Override
    public void updatePdfFileLocation(String doi, String fileLocation) throws DatabaseException {
        String SET_PDF_FILENAME_SQL = "UPDATE generic_files SET pdf_filename=?,time_accessed=NOW() WHERE doi=?";
        try {
            jdbcTemplate.update(SET_PDF_FILENAME_SQL, fileLocation, doi);
        } catch (DataAccessException e) {
            logger.error("Cannot set pdf filename", e);
            throw new DatabaseException("Cannot set pdf filename");
        }
    }

    @Override
    public void updateMetaFileLocation(String doi, String fileLocation) throws DatabaseException {
        String SET_META_FILENAME_SQL = "UPDATE generic_files SET metadata_filename=?,time_accessed=NOW() WHERE doi=?";
        try {
            jdbcTemplate.update(SET_META_FILENAME_SQL, fileLocation, doi);
        } catch (DataAccessException e) {
            logger.error("Cannot set metadata filename", e);
            throw new DatabaseException("Cannot set metadata filename");
        }
    }

    @Override
    public void updateMetadata(String doi, String metadata) throws DatabaseException {

    }

    @Override
    public List<GenericFile> retrievePublisherGenericFilePage(String publisherPrefix, Integer start, Integer pageSize) throws DatabaseException {
        String SQL = "SELECT * FROM generic_files WHERE publisher_prefix=? LIMIT ?,?";
        
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1,publisherPrefix);
                ps.setInt(2, start);
                ps.setInt(3, pageSize);
            }
        };

        RowMapper<GenericFile> rowMapper = new GenericFileRowMapper();

        try {
            List<GenericFile> genericFiles = jdbcTemplate.query(SQL, preparedStatementSetter, rowMapper);
            logger.info("returning :"+genericFiles.size()+" records from db");
            return genericFiles;
        } catch (DataAccessException dataAccessException) {
            logger.error("Sql exception : " + dataAccessException.getMessage());
            throw new DatabaseException("Error while retrieving generic files of publisher", dataAccessException);
        }
    }

    private static class GenericFileRowMapper implements RowMapper<GenericFile> {

        @Override
        public GenericFile mapRow(ResultSet rs, int i) throws SQLException {
            GenericFile genericFile = new GenericFile();
            genericFile.setId(rs.getInt(1));
            genericFile.setPublisher_prefix(rs.getString(2));
            genericFile.setDoi(rs.getString(3));
            genericFile.setMetadata(rs.getString(4));
            genericFile.setMetadata_filename(rs.getString(5));
            genericFile.setPdf_filename(rs.getString(6));
            genericFile.setTime_accessed(rs.getTimestamp(7));
            return genericFile;
        }
    }
}
