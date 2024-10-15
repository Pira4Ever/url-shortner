package edu.octavio.url_shortner.dtos;

/**
 * A Record containing all needed to return the information that controller need
 * @author octavio
 * @param id the identifier of the url
 * @param qrCode a byte[] containing the QrCode
 */
public record UrlDtoOut(String id, byte[] qrCode) {

}
