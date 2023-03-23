package finki.emt.labs.service;

import finki.emt.labs.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();
    Optional<Author> save (String name, String surname, Long countryId);
    Optional<Author> findById(Long id);
    Optional<Author> update(Long id, String name, String username, Long countryId);
    void deleteById(Long id);
}
