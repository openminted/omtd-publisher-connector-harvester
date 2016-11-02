package eu.openminted.toolkit.database.exceptions;

/**
 *
 * @author lucasanastasiou
 */
public class DatabaseException extends Exception {

    public DatabaseException(String message) {
        super("Database excpetion : " + message);
    }

    public DatabaseException(String message, Throwable cause) {
        super("Database excpetion : " + message, cause);
    }

}
