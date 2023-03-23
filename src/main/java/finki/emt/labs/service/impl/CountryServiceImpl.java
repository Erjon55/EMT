package finki.emt.labs.service.impl;

import finki.emt.labs.model.Country;
import finki.emt.labs.model.exceptions.CountryNotFoundException;
import finki.emt.labs.model.exceptions.InvalidArgumentsException;
import finki.emt.labs.repository.CountryRepository;
import finki.emt.labs.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    @Override
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Optional<Country> save(String name, String continent) {
        if(name.equals(""))
            throw new InvalidArgumentsException();
        Country country = new Country(name, continent);
        return Optional.of(this.countryRepository.save(country));
    }

    @Override
    public Optional<Country> findById(Long id) {
        return this.countryRepository.findById(id);
    }

    @Override
    public Optional<Country> update(Long id, String name, String continent) {
        Optional<Country> country = this.findById(id);
        if(country.isEmpty())
            throw new CountryNotFoundException(id);

        Country c = country.get();
        c.setName(name);
        c.setContinent(continent);
        return Optional.of(this.countryRepository.save(c));
    }

    @Override
    public void deleteById(Long id) {
        if(this.findById(id).isEmpty())
            throw new CountryNotFoundException(id);
        this.countryRepository.deleteById(id);
    }
}
