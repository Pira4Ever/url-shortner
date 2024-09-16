package edu.octavio.url_shortner.dtos;

public record UrlDto(String id, String urlLong) {
    public UrlDto withId(String id) {
        return new UrlDto(id, urlLong());
    }
}
