package com.useo.demo.controllers.catalog;

import com.useo.demo.dtos.catalog.SubCategorySaveRequestDto;
import com.useo.demo.dtos.catalog.SubCategoryResponseDto;
import com.useo.demo.services.catalog.SubCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/subcategories")
@RestController
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    public SubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    // Create a new sub-category
    @PostMapping("/create")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<SubCategoryResponseDto> createSubCategory(@RequestBody SubCategorySaveRequestDto subCategoryDto) {
        try {
            SubCategoryResponseDto createdSubCategoryDto = subCategoryService.createSubCategory(subCategoryDto);
            return ResponseEntity.ok(createdSubCategoryDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Update an existing sub-category
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<SubCategoryResponseDto> updateSubCategory(@PathVariable Long id, @RequestBody SubCategorySaveRequestDto subCategoryDto) {
        if (!subCategoryService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        try {
            SubCategoryResponseDto updatedSubCategoryDto = subCategoryService.updateSubCategory(id, subCategoryDto);
            return ResponseEntity.ok(updatedSubCategoryDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Get all sub-categories
    @GetMapping("/list")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<List<SubCategoryResponseDto>> getAllSubCategories() {
        List<SubCategoryResponseDto> subCategoryDtos = subCategoryService.findAll();
        return ResponseEntity.ok(subCategoryDtos);
    }

    // Get a specific sub-category by ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<SubCategoryResponseDto> getSubCategoryById(@PathVariable Long id) {
        if (!subCategoryService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        try {
            SubCategoryResponseDto subCategoryDto = subCategoryService.findById(id);
            return ResponseEntity.ok(subCategoryDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a specific sub-category by ID
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<Void> deleteSubCategory(@PathVariable Long id) {
        if (!subCategoryService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        subCategoryService.deleteSubCategory(id);
        return ResponseEntity.noContent().build();
    }
}
