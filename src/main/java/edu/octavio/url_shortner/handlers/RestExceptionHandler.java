package edu.octavio.url_shortner.handlers;

import edu.octavio.url_shortner.exceptions.IdNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

/**
 * A handler to Exceptions thrown by the application
 * @author octavio
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);
    /**
     * handler for {@link IdNotFoundException}
     * @param exception exception's instance
     * @return {@link ResponseEntity} with HTTP status Not Found and body ID not found
     */
    @ExceptionHandler(IdNotFoundException.class)
    private ResponseEntity<String> idNotFoundHandler(IdNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID not found");
    }

    @ExceptionHandler(IOException.class)
    private ResponseEntity<String> ioExceptionHandler(IOException exception) {
        String message = "Unexpected server error.";
        LOGGER.error(message, exception);
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
