package finki.emt.labs.service;

import finki.emt.labs.model.enums.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
}
