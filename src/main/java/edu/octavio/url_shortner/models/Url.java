package edu.octavio.url_shortner.models;

import edu.octavio.url_shortner.dtos.UrlDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "urls")
@Getter
@Setter
public class Url {
    @Id
    private String id;
    private String urlLong;

    public Url(UrlDto urlData) {
        this.id = urlData.id();
        this.urlLong = urlData.urlLong();
    }
}
