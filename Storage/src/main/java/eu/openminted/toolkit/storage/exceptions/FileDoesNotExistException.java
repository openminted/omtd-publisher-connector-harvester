package eu.openminted.toolkit.storage.exceptions;

/**
 *
 * @author lucasanastasiou
 */
public class FileDoesNotExistException extends StorageException {
    
    public FileDoesNotExistException(String message) {
        super("FileDoesNotExistException : "+message);
    }

    public FileDoesNotExistException(String message, Throwable cause) {
        super("FileDoesNotExistException : "+message, cause);
    }
    

}
