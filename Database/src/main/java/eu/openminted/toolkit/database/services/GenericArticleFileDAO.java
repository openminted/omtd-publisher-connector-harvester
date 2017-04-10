package eu.openminted.toolkit.database.services;

import eu.openminted.toolkit.database.exceptions.DatabaseException;

/**
 *
 * @author lucasanastasiou
 */
public interface GenericArticleFileDAO {

   
    public void insertNewArticle(String publisherPrefix, String doi, String metadata) throws DatabaseException;

    public boolean isArticleDownloaded(String publisherPrefix, String doi) throws DatabaseException;

    public void updatePdfFileLocation(String doi, String pdfFileLocation) throws DatabaseException;
    
    public void updateMetaFileLocation(String doi, String metaFileLocation) throws DatabaseException;

    public void updateMetadata(String doi, String metadata) throws DatabaseException;
}
