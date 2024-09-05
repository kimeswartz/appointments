package com.useo.demo.controllers.catalog;

import com.useo.demo.dtos.catalog.CategorySaveRequestDto;
import com.useo.demo.dtos.catalog.CategoryResponseDto;
import com.useo.demo.services.catalog.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/category")
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody CategorySaveRequestDto categorySaveRequestDto) {
        CategoryResponseDto categoryResponseDto = categoryService.createCategory(categorySaveRequestDto);
        return ResponseEntity.ok(categoryResponseDto);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<CategoryResponseDto> updateCategory(@PathVariable Long id, @RequestBody CategorySaveRequestDto categorySaveRequestDto) {
        CategoryResponseDto categoryResponseDto = categoryService.updateCategory(id, categorySaveRequestDto);
        return ResponseEntity.ok(categoryResponseDto);
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {
        List<CategoryResponseDto> categoryResponseDtos = categoryService.findAll();
        return ResponseEntity.ok(categoryResponseDtos);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}