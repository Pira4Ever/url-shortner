package edu.octavio.url_shortner.services;

import edu.octavio.url_shortner.dtos.UrlDto;
import edu.octavio.url_shortner.exceptions.IdNotFoundException;
import edu.octavio.url_shortner.models.Url;
import edu.octavio.url_shortner.repositories.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

@Service
public class UrlService {
    @Autowired
    UrlRepository urlRepository;

    @Value("${config.url.minSize:5}")
    Integer idMinSize;

    @Value("${config.url.maxSize:10}")
    Integer idMaxSize;

    public UrlDto createUrl(UrlDto urlData) {
        urlData = urlData.withId(generateRandomId());
        Url newUrl = new Url(urlData);
        urlRepository.save(newUrl);
        return urlData;
    }

    private String generateRandomId() {
        return randomAlphanumeric(idMinSize, idMaxSize);
    }

    public String findById(String id) {
        Url url = urlRepository.findById(id).orElseThrow(IdNotFoundException::new);
        return url.getUrlLong();
    }
}
