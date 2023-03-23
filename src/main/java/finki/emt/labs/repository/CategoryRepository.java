package finki.emt.labs.repository;

import finki.emt.labs.model.enums.Category;

import java.util.Arrays;
import java.util.List;

public class CategoryRepository {
    public List<Category> findAll(){
        return Arrays.asList(Category.values().clone());
    }
}
