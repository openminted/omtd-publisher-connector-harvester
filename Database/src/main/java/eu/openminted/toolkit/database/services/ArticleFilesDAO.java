package eu.openminted.toolkit.database.services;

import eu.openminted.toolkit.database.exceptions.DatabaseException;

/**
 *
 * @author lucasanastasiou
 */
public interface ArticleFilesDAO {

    public void insertNewArticleFiles(long id, String doi) throws DatabaseException;
    
    public void setMetadataFilename(long id, String doi, String metadataFilenamePath) throws DatabaseException;
    
    public void setPdfFilename(long id, String doi, String pdfFilenamePath) throws DatabaseException;
}
