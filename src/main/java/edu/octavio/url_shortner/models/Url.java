package edu.octavio.url_shortner.models;

import edu.octavio.url_shortner.dtos.UrlDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A class to represent an url on Database
 * @author octavio
 */
@Entity
@Table(name = "urls")
@Getter
@Setter
@NoArgsConstructor
public class Url {
    @Id
    private String id;
    private String urlLong;

    /**
     * A constructor that receives UrlDto to create an Url
     * @param urlData an UrlDto instance that contains id and urlLong
     */
    public Url(UrlDto urlData) {
        this.id = urlData.id();
        this.urlLong = urlData.urlLong();
    }
}
