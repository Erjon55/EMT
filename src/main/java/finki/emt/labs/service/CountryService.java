package finki.emt.labs.service;

import finki.emt.labs.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();
    Optional<Country> save(String name, String continent);
    Optional<Country> findById(Long id);
    Optional<Country> update(Long id, String name, String continent);
    void deleteById(Long id);
}
