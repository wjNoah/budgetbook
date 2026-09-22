package com.example.budgetbook.service;

import com.example.budgetbook.model.Category;
import com.example.budgetbook.model.TransactionType;
import com.example.budgetbook.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public List<Category> listActive() {
        return categoryRepository.findByIsActiveTrue();
    }

    public Category create(String name, TransactionType type) {
        if (categoryRepository.existsByName(name)) {
            throw new IllegalArgumentException("Category name already exists: " + name);
        }

        Category category = new Category();
        category.setName(name);
        category.setType(type);
        category.setActive(true);

        return categoryRepository.save(category);
    }

    public Category update(Long id, String name, TransactionType type) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + id));

        if (categoryRepository.existsByNameAndIdNot(name, id)) {
            throw new IllegalArgumentException("Category name already exists: " + name);
        }

        category.setName(name);
        category.setType(type);

        return categoryRepository.save(category);
    }

    public Category setActive(Long id, boolean active) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + id));

        category.setActive(active);

        return categoryRepository.save(category);
    }
}
