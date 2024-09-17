package edu.octavio.url_shortner.repositories;

import edu.octavio.url_shortner.models.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * A repository that manipulate the database
 * @author octavio
 */
public interface UrlRepository extends JpaRepository<Url, Integer> {
    /**
     *
     * @param id the identifier of the url that will be searched
     * @return the url wrapped in an {@link Optional}
     */
    Optional<Url> findById(String id);
}
