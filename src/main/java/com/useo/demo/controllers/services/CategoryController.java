package com.useo.demo.controllers.services;

import com.useo.demo.dtos.services.ServiceCategoryDto;
import com.useo.demo.entities.services.Category;
import com.useo.demo.services.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/edit")
@RestController
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/category")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Category> createCategory(@RequestBody ServiceCategoryDto categoryDto) {
        Category category = categoryService.createCategory(categoryDto);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/category/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody ServiceCategoryDto categoryDto) {
        Category category = categoryService.updateCategory(id, categoryDto);
        return ResponseEntity.ok(category);
    }

    @GetMapping("/category")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

    @DeleteMapping("/category/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
