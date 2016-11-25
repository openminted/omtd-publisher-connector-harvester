package eu.openminted.toolkit.database.services.impl;

import eu.openminted.toolkit.database.exceptions.DatabaseException;
import eu.openminted.toolkit.database.services.NodeProcessingDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucasanastasiou
 */
@Service
public class MySQLNodeProcessingDAO implements NodeProcessingDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private Logger logger = LoggerFactory.getLogger("MySQLNodeProcessingDAO");

    @Override
    public long addNewNodeProcessing(final String url) throws DatabaseException {
        String ADD_NEW_NODE_PROCESSING_SQL = "INSERT INTO node_processing(url_processed,time_started) VALUES(?,NOW())";
        try {
            PreparedStatementCreator psc = new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection cnctn) throws SQLException {
                    PreparedStatement ps = cnctn.prepareStatement(ADD_NEW_NODE_PROCESSING_SQL, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, url);
                    return ps;
                }
            };
            KeyHolder holder = new GeneratedKeyHolder();
            jdbcTemplate.update(psc, holder);
            return holder.getKey().longValue();
        } catch (DataAccessException e) {
            logger.error("Cannot insert new node processing ", e);
            throw new DatabaseException("Cannot insert new node processing");
        }
    }

    @Override
    public void setNodeProcessingEnd(String url) throws DatabaseException {
        String SET_PROCESSING_END_SQL = "UPDATE node_processing SET time_finished=NOW() WHERE url_processed=?";
        try {
            jdbcTemplate.update(SET_PROCESSING_END_SQL, url);
        } catch (DataAccessException e) {
            logger.error("Cannot set end time of node processing ", e);
            throw new DatabaseException("Cannot set end time of node processing");
        }
    }

    @Override
    public void setNodeProcessingEnd(long id) throws DatabaseException {
        String SET_PROCESSING_END_SQL = "UPDATE node_processing SET time_finished=NOW() WHERE id=?";
        try {
            jdbcTemplate.update(SET_PROCESSING_END_SQL, id);
        } catch (DataAccessException e) {
            logger.error("Cannot set end time of node processing ", e);
            throw new DatabaseException("Cannot set end time of node processing");
        }
    }

}
