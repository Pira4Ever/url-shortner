package edu.octavio.url_shortner.controllers;

import edu.octavio.url_shortner.dtos.UrlDto;
import edu.octavio.url_shortner.services.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UrlController {
    @Autowired
    UrlService urlService;

    @GetMapping("/{id}")
    public ResponseEntity<String> redirectToUrlLong(@PathVariable("id") String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", urlService.findById(id));
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<UrlDto> createUrl(@RequestBody UrlDto urlData) {
        return new ResponseEntity<>(urlService.createUrl(urlData), HttpStatus.CREATED);
    }
}
