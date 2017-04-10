package eu.openminted.toolkit.storage;

import eu.openminted.toolkit.storage.exceptions.FileDoesNotExistException;
import eu.openminted.toolkit.storage.exceptions.StorageException;
import java.io.IOException;

/**
 *
 * @author lucasanastasiou
 */
public interface StorageDAO {

    public String storeSitemapFile(Integer id, int level, String url, String contents) throws StorageException;
    
    public String getSitemapFileContents(String filename) throws FileDoesNotExistException,StorageException;
    
    public String getFileContents(String filename) throws FileDoesNotExistException,StorageException;

    public String getMetadataFileLocation(String articleUrl,String extension);
    
    public void storeFile(String fullFileName, String fileContent) throws IOException;
    public String storeMetadataFile(String articleUrl, String articleMetadata, String extension) throws StorageException;
    
    public String getPdfFileLocation(String articleUrl);
    
    public String storePdfFile(String articleUrl, String articlePdf) throws StorageException;
    
    public String getPdfFileLocation(String publisherPrefix, String doi,String articleUrl) throws StorageException;
    public String getMetadataFileLocation(String publisherPrefix, String doi,String articleUrl, String extension) throws StorageException;
}
