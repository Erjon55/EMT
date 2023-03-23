package finki.emt.labs.web.rest;

import finki.emt.labs.model.Author;
import finki.emt.labs.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/author")
@AllArgsConstructor
public class AuthorRestController {

    private final AuthorService authorService;

    @GetMapping
    public List<Author> findAll(){
        return this.authorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> findById(@PathVariable Long id){
        return this.authorService.findById(id)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/add")
    public ResponseEntity<Author> save(@RequestParam String name,
                                       @RequestParam String surname,
                                       @RequestParam Long countryId) {
        return this.authorService.save(name, surname, countryId)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Author> update(@PathVariable Long id,
                                         @RequestParam String name,
                                         @RequestParam String surname,
                                         @RequestParam Long countryId) {
        return this.authorService.update(id,name, surname, countryId)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        this.authorService.deleteById(id);
        if(this.authorService.findById(id).isPresent())
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

}
