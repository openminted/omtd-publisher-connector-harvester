package eu.openminted.toolkit.storage.exceptions;

/**
 *
 * @author lucasanastasiou
 */
public class StorageException extends Exception {

    public StorageException(String message) {
        super("Storage exception : "+message);
    }

    public StorageException(String message, Throwable cause) {
        super("Storage exception : "+message, cause);
    }
}
