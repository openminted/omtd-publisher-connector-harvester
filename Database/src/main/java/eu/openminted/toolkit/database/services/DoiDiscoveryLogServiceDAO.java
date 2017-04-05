package eu.openminted.toolkit.database.services;

import eu.openminted.toolkit.database.exceptions.DatabaseException;

/**
 *
 * @author lucasanastasiou
 */
public interface DoiDiscoveryLogServiceDAO {

    public long insertNewDiscovery(String publisher, String day)throws DatabaseException;
    
    public void finishDoiDiscovery(long id, String publisher)throws DatabaseException;
    
    public void updateDiscoveredDoisCount(long id, long count)throws DatabaseException;
}
