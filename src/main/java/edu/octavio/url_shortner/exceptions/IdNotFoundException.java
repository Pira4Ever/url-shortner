package edu.octavio.url_shortner.exceptions;

/**
 * An exception when the id specified is not found in database
 * @author octavio
 */
public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException() {
        super("ID not found");
    }

    public IdNotFoundException(String message) {
        super(message);
    }
}
