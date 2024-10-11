package edu.octavio.url_shortner.services;

import edu.octavio.url_shortner.dtos.UrlDto;
import edu.octavio.url_shortner.exceptions.IdNotFoundException;
import edu.octavio.url_shortner.models.Url;
import edu.octavio.url_shortner.repositories.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static org.apache.commons.lang3.RandomStringUtils.secure;

/**
 * Class containing the application's business rule
 * @author octavio
 */
@Service
public class UrlService {
    @Autowired
    UrlRepository urlRepository;

    @Value("${config.url.minSize:5}")
    Integer idMinSize;

    @Value("${config.url.maxSize:10}")
    Integer idMaxSize;

    /**
     * create url and save it in db
     * @param urlData the data used to create an url
     * @return return an {@link UrlDto} containing the info about the created url
     */
    public UrlDto createUrl(UrlDto urlData) {
        UrlDto newUrlData = urlData.withId(generateRandomId());
        Url newUrl = new Url(newUrlData);
        urlRepository.save(newUrl);
        return newUrlData;
    }

    /**
     * An auxiliary method to generate a random id
     * @return a random generated id
     */
    private String generateRandomId() {
        return secure().nextAlphanumeric(idMinSize, idMaxSize);
    }

    /**
     * return the urlLong with specified id, if it doesn't exist throw {@link IdNotFoundException}
     * @param id the id that will be searched
     * @return urlLong with the specified id, if exists
     */
    public String findById(String id) {
        Url url = urlRepository.findById(id).orElseThrow(IdNotFoundException::new);
        return url.getUrlLong();
    }
}
