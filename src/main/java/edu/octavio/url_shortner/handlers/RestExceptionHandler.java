package edu.octavio.url_shortner.handlers;

import edu.octavio.url_shortner.exceptions.IdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * A handler to Exceptions thrown by the application
 * @author octavio
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * handler for {@link IdNotFoundException}
     * @param exception exception's instance
     * @return {@link ResponseEntity} with HTTP status Not Found and body ID not found
     */
    @ExceptionHandler(IdNotFoundException.class)
    private ResponseEntity<String> idNotFoundHandler(IdNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID not found");
    }
}
