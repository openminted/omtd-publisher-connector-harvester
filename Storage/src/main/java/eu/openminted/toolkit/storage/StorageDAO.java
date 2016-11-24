package eu.openminted.toolkit.storage;

import eu.openminted.toolkit.storage.exceptions.FileDoesNotExistException;
import eu.openminted.toolkit.storage.exceptions.StorageException;

/**
 *
 * @author lucasanastasiou
 */
public interface StorageDAO {

    public String storeSitemapFile(Integer id, int level, String url, String contents) throws StorageException;
    
    public String getSitemapFileContents(String filename) throws FileDoesNotExistException,StorageException;
    
    public String getFileContents(String filename) throws FileDoesNotExistException,StorageException;

    public String storeMetadataFile(String articleUrl, String articleMetadata) throws StorageException;
    
    public String storePdfFile(String articleUrl, String articlePdf) throws StorageException;
}
