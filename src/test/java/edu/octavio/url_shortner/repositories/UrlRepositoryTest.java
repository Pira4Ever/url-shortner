package edu.octavio.url_shortner.repositories;

import edu.octavio.url_shortner.dtos.UrlDto;
import edu.octavio.url_shortner.models.Url;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class UrlRepositoryTest {

    @Autowired
    UrlRepository repository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should get Url successfully from DB")
    void findByIdCase1() {
        String id = "abccba";
        UrlDto data = new UrlDto(id, "https://www.github.com/Pira4Ever");
        this.createUrl(data);

        Optional<Url> result = this.repository.findById(id);

        assertTrue(result.isPresent());
    }

    @Test
    @DisplayName("Should not get Url from DB when url does not exist")
    void findByIdCase2() {
        String id = "abccba";

        Optional<Url> result = this.repository.findById(id);

        assertTrue(result.isEmpty());
    }

    private Url createUrl(UrlDto data) {
        Url newUrl = new Url(data);
        this.entityManager.persist(newUrl);
        return newUrl;
    }
}