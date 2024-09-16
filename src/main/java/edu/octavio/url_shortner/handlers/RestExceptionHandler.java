package edu.octavio.url_shortner.handlers;

import edu.octavio.url_shortner.exceptions.IdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(IdNotFoundException.class)
    private ResponseEntity<String> idNotFoundHandler(IdNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID not found");
    }
}
