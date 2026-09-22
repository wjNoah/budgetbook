package com.example.budgetbook.controller;

import com.example.budgetbook.model.Category;
import com.example.budgetbook.model.TransactionType;
import com.example.budgetbook.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public String list(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "categories/list";
    }

    @GetMapping("/categories/new")
    public String newCategory(Model model) {
        model.addAttribute("categories", new Category());
        return "category/new";
    }

    @GetMapping("/categories/{id}/edit")
    public String editCategory(Model model, @PathVariable Long id) {
        model.addAttribute("categories", categoryService.findById(id));
        return "categories/edit";
    }

    @PostMapping("/categories")
    public String save(@RequestParam String name, @RequestParam TransactionType type) {
        categoryService.create(name, type);
        return "redirect:/categories";
    }

    @PostMapping("/categories/{id}")
    public String update(@PathVariable Long id, @RequestParam String name, @RequestParam TransactionType type) {
        categoryService.update(id, name, type);
        return "redirect:/categories";
    }

    @PostMapping("/categories/{id}/toggle-active")
    public String toggleActiveCategory(@PathVariable Long id, @RequestParam boolean active) {
        categoryService.setActive(id, active);
        return "redirect:/categories";
    }
}
