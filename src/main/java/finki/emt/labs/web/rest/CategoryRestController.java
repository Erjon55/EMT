package finki.emt.labs.web.rest;

import finki.emt.labs.model.enums.Category;
import finki.emt.labs.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = {"/api/categories"})
@AllArgsConstructor
public class CategoryRestController {

    private final CategoryService categoryService;

    @GetMapping
    public List<Category> findAll() {
        return this.categoryService.findAll();
    }
}
