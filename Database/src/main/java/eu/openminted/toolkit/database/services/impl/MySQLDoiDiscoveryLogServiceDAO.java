package eu.openminted.toolkit.database.services.impl;

import eu.openminted.toolkit.database.exceptions.DatabaseException;
import eu.openminted.toolkit.database.services.DoiDiscoveryLogServiceDAO;
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
public class MySQLDoiDiscoveryLogServiceDAO implements DoiDiscoveryLogServiceDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Logger logger = LoggerFactory.getLogger("MySQLDoiDiscoveryLogServiceDAO");

    @Override
    public long insertNewDiscovery(String publisher, String day) throws DatabaseException {
        String INSERT_NEW_DOI_DISCOVERY_SQL = "INSERT INTO publisher_doi_discovery_log(publisher, day, time_commenced) VALUES(?,?,CURRENT_TIMESTAMP)";
        try {
            PreparedStatementCreator psc = new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection cnctn) throws SQLException {
                    PreparedStatement ps = cnctn.prepareStatement(INSERT_NEW_DOI_DISCOVERY_SQL, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, publisher);
                    ps.setString(2, day);
                    return ps;
                }
            };
            KeyHolder holder = new GeneratedKeyHolder();
            jdbcTemplate.update(psc, holder);
            return holder.getKey().longValue();
        } catch (DataAccessException e) {
            logger.error("Cannot insert new article file", e);
            throw new DatabaseException("Cannot insert new doi discovery log");
        }
    }

    @Override
    public void finishDoiDiscovery(long id, String publisher) throws DatabaseException {
        String SQL = "UPDATE publisher_doi_discovery_log SET time_finished=NOW() WHERE id=? AND publisher=?";
        try {
            jdbcTemplate.update(SQL, id, publisher);
        } catch (DataAccessException e) {
            logger.error("Cannot update publisher doi discovery", e);
            throw new DatabaseException("Cannot update publisher doi discovery time fininshed");
        }
    }

    @Override
    public void updateDiscoveredDoisCount(long id, long count) throws DatabaseException {
        String SQL = "UPDATE publisher_doi_discovery_log SET retrieved_count=? WHERE id=?";
        try {
            jdbcTemplate.update(SQL, count, id);
        } catch (DataAccessException e) {
            logger.error("Cannot update publisher doi discovered count", e);
            throw new DatabaseException("Cannot update publisher doi discovery count");
        }
    }

}
