package edu.octavio.url_shortner.controllers;

import edu.octavio.url_shortner.dtos.UrlDto;
import edu.octavio.url_shortner.services.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * A controller that receives the application's request
 * @author octavio
 */
@RestController
@RequestMapping("/")
public class UrlController {
    @Autowired
    UrlService urlService;

    /**
     * redirect to the url with the id, if it doesn't exist return ID not found
     * @param id the id of that will be searched on db
     * @return {@link ResponseEntity} that redirect to the original url
     */
    @GetMapping("/{id}")
    public ResponseEntity<String> redirectToUrlLong(@PathVariable("id") String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", urlService.findById(id));
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    /**
     * create a short url and return it
     * @param urlData the data of the original url
     * @return {@link ResponseEntity} containing the created short url and the original url
     */
    @PostMapping
    public ResponseEntity<UrlDto> createUrl(@RequestBody UrlDto urlData) {
        return new ResponseEntity<>(urlService.createUrl(urlData), HttpStatus.CREATED);
    }
}
