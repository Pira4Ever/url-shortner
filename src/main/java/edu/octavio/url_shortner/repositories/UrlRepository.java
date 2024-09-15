package edu.octavio.url_shortner.repositories;

import edu.octavio.url_shortner.models.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Integer> {
    Optional<Url> findById(String id);
}
