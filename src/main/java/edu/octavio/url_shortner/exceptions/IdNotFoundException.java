package edu.octavio.url_shortner.exceptions;

public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException() {
        super("ID not found");
    }

    public IdNotFoundException(String message) {
        super(message);
    }
}
