package eu.openminted.toolkit.storage;

import eu.openminted.toolkit.storage.exceptions.StorageException;

/**
 *
 * @author lucasanastasiou
 */
public interface StorageDAO {

    public String storeSitemapFile(Integer id, int level, String url, String contents) throws StorageException;
}
