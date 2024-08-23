package com.useo.demo.controllers.services;

import com.useo.demo.dtos.services.ServiceCategoryDto;
import com.useo.demo.dtos.services.CategoryDto;
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

    @PostMapping("/category/upload")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody ServiceCategoryDto serviceCategoryDto) {
        CategoryDto categoryDto = categoryService.createCategory(serviceCategoryDto);
        return ResponseEntity.ok(categoryDto);
    }

    @PutMapping("/category/update/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id, @RequestBody ServiceCategoryDto serviceCategoryDto) {
        CategoryDto categoryDto = categoryService.updateCategory(id, serviceCategoryDto);
        return ResponseEntity.ok(categoryDto);
    }

    @GetMapping("/category/list")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categoryDtos = categoryService.findAll();
        return ResponseEntity.ok(categoryDtos);
    }

    @DeleteMapping("/category/delete/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
