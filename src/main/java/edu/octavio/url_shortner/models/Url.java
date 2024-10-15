package edu.octavio.url_shortner.models;

import edu.octavio.url_shortner.dtos.UrlDtoIn;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A class to represent an url on Database
 * @author octavio
 */
@Entity
@Table(name = "urls", indexes = {@Index(name = "idx_urlLong", columnList = "urlLong")})
@Getter
@Setter
@NoArgsConstructor
public class Url {
    @Id
    private String id;
    private String urlLong;

    /**
     * A constructor that receives UrlDto to create an Url
     * @param urlData an UrlDtoIn instance that contains id and urlLong
     */
    public Url(UrlDtoIn urlData) {
        this.id = urlData.id();
        this.urlLong = urlData.urlLong();
    }
}
