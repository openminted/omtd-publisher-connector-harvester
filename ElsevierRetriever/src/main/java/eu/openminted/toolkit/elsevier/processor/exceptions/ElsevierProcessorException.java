package eu.openminted.toolkit.elsevier.processor.exceptions;

/**
 *
 * @author lucasanastasiou
 */
public class ElsevierProcessorException extends Exception {

    public ElsevierProcessorException() {
    }

    public ElsevierProcessorException(String message) {
        super("Elsevier processor excpetion : " + message);
    }

    public ElsevierProcessorException(String message, Throwable cause) {
        super("Elsevier processor excpetion : " + message, cause);
    }

}
