package edu.octavio.url_shortner.services;

import edu.octavio.url_shortner.dtos.UrlDto;
import edu.octavio.url_shortner.exceptions.IdNotFoundException;
import edu.octavio.url_shortner.models.Url;
import edu.octavio.url_shortner.repositories.UrlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UrlServiceTest {
    @Mock
    private UrlRepository repository;

    @InjectMocks
    UrlService service;

    @BeforeEach
    void setup() {
        try {
            MockitoAnnotations.openMocks(this).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Should create url successfully")
    void createUrl() {
        ReflectionTestUtils.setField(service, "idMinSize", 5);
        ReflectionTestUtils.setField(service, "idMaxSize", 10);
        UrlDto urlData = new UrlDto(null, "https://www.github.com/Pira4Ever");
        Url url = new Url(urlData);
        when(repository.save(any())).thenReturn(url);
        UrlDto returned = service.createUrl(urlData);
        assertEquals(returned.urlLong(), "https://www.github.com/Pira4Ever");
    }

    @Test
    @DisplayName("Should get url successfully from DB")
    void findByIdCase1() {
        Optional<Url> urlOptional;
        Url url = new Url();
        url.setUrlLong("https://www.github.com/Pira4Ever");
        url.setId("abccba");
        urlOptional = Optional.of(url);
        when(repository.findById("abccba")).thenReturn(urlOptional);
        assertEquals("https://www.github.com/Pira4Ever", service.findById("abccba"));
    }

    @Test
    @DisplayName("Should throw IdNotFoundException when id not founded on DB")
    void findByIdCase2() {
        when(repository.findById((String) any())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, () -> service.findById("gsdfadWE"));
    }
}