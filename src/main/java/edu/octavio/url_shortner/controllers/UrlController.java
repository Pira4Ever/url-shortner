package edu.octavio.url_shortner.controllers;

import edu.octavio.url_shortner.dtos.UrlDto;
import edu.octavio.url_shortner.services.UrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
     */
    @Operation(summary = "Redirect to the original url based on id", method = "GET",
            parameters = @Parameter(name = "id", in = ParameterIn.PATH))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Found original url with specified id"),
            @ApiResponse(responseCode = "404", description = "Not found original url with specified id"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public void redirectToUrlLong(@PathVariable("id") String id, HttpServletResponse response) throws IOException{
        response.sendRedirect(urlService.findById(id));
    }

    /**
     * create a short url and return it
     * @param urlData the data of the original url
     * @return {@link ResponseEntity} containing the created short url and the original url
     */
    @Operation(summary = "Create short url and return it", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created shor url"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<UrlDto> createUrl(@RequestBody UrlDto urlData) {
        return new ResponseEntity<>(urlService.createUrl(urlData), HttpStatus.CREATED);
    }
}
