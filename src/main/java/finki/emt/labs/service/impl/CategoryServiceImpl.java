package finki.emt.labs.service.impl;

import finki.emt.labs.model.enums.Category;
import finki.emt.labs.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryService categoryService;
    @Override
    public List<Category> findAll() {
        return this.categoryService.findAll();
    }
}
