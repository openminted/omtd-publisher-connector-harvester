package eu.openminted.toolkit.database.services;

import eu.openminted.toolkit.database.exceptions.DatabaseException;

/**
 *
 * @author lucasanastasiou
 */
public interface GenericArticleFileDAO {

    public void insertNewArticle(String publisherPrefix, String doi, String metadata) throws DatabaseException;
    public boolean isArticleDownloaded(String publisherPrefix,String doi) throws DatabaseException;
}
