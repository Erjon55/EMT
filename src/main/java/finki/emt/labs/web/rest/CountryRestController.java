package finki.emt.labs.web.rest;

import finki.emt.labs.model.Country;
import finki.emt.labs.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = {"/api/countries"})
@AllArgsConstructor
public class CountryRestController {

    private final CountryService countryService;

    @GetMapping
    public List<Country> findAll() {
        return this.countryService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> findById(@PathVariable Long id) {
        return this.countryService.findById(id)
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/add")
    public ResponseEntity<Country> save(@RequestParam String name, @RequestParam String continent) {
        return this.countryService.save(name, continent)
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Country> save(@PathVariable Long id,
                                        @RequestParam String name, @RequestParam String continent) {
        return this.countryService.update(id, name, continent)
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping(value = {"/delete/{id}"})
    public ResponseEntity delete(@PathVariable Long id) {
        this.countryService.deleteById(id);
        if(this.countryService.findById(id).isPresent())
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }
}
