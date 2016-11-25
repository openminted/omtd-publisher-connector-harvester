package eu.openminted.toolkit.database.services;

import eu.openminted.toolkit.database.exceptions.DatabaseException;

/**
 *
 * @author lucasanastasiou
 */
public interface NodeProcessingDAO {

    public long addNewNodeProcessing(String url) throws DatabaseException;

    public void setNodeProcessingEnd(String url) throws DatabaseException;

    public void setNodeProcessingEnd(long id) throws DatabaseException;

}
