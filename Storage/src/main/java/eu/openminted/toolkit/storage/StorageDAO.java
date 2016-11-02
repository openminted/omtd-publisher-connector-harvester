package eu.openminted.toolkit.storage;

import eu.openminted.toolkit.storage.exceptions.FileDoesNotExistException;
import eu.openminted.toolkit.storage.exceptions.StorageException;

/**
 *
 * @author lucasanastasiou
 */
public interface StorageDAO {

    public String storeSitemapFile(Integer id, int level, String url, String contents) throws StorageException;
    
    public String getFileContents(String filename) throws FileDoesNotExistException,StorageException;
}
