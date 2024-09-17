package edu.octavio.url_shortner.dtos;

/**
 * A Record containing all needed to create an url entity
 * @author octavio
 * @param id the identifier of the url
 * @param urlLong the url that will be shorted
 */
public record UrlDto(String id, String urlLong) {
    /**
     *
     * @param id an identifier for url
     * @return a new urlDto with the specified id
     */
    public UrlDto withId(String id) {
        return new UrlDto(id, urlLong());
    }
}
