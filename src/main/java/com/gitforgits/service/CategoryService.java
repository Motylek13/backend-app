package com.gitforgits.service;

import com.gitforgits.model.Category;
import com.gitforgits.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category create(final Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> getBooksByCategoryName(final String getBooksByCategoryName) {
        return categoryRepository.findAll(); // placeholder until a real method is implemented

    }


}
