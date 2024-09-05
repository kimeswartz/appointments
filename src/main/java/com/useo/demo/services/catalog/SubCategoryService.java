package com.useo.demo.services.catalog;

import com.useo.demo.entities.catalog.SubCategory;
import com.useo.demo.entities.catalog.Category;
import com.useo.demo.dtos.catalog.SubCategoryResponseDto;
import com.useo.demo.dtos.catalog.SubCategorySaveRequestDto;
import com.useo.demo.repositories.catalog.SubCategoryRepository;
import com.useo.demo.repositories.catalog.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;

    public SubCategoryService(SubCategoryRepository subCategoryRepository, CategoryRepository categoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    // Find all sub-categories
    @Transactional(readOnly = true)
    public List<SubCategoryResponseDto> findAll() {
        Iterable<SubCategory> subCategories = subCategoryRepository.findAll();
        return StreamSupport.stream(subCategories.spliterator(), false)
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Find sub-category by ID
    @Transactional(readOnly = true)
    public SubCategoryResponseDto findById(Long id) {
        SubCategory subCategory = findSubCategoryById(id);
        return convertToDto(subCategory);
    }

    // Create a new sub-category
    @Transactional
    public SubCategoryResponseDto createSubCategory(SubCategorySaveRequestDto input) {
        Category category = findCategoryById(input.getCategoryId());
        SubCategory subCategory = new SubCategory();
        return convertToDto(saveSubCategory(input, category, subCategory));
    }

    // Update an existing sub-category
    @Transactional
    public SubCategoryResponseDto updateSubCategory(Long id, SubCategorySaveRequestDto input) {
        SubCategory existingSubCategory = findSubCategoryById(id);
        Category category = findCategoryById(input.getCategoryId());

        existingSubCategory.setCategory(category);
        existingSubCategory.setDescription(input.getDescription());
        existingSubCategory.setName(input.getName());

        subCategoryRepository.save(existingSubCategory);

        return convertToDto(existingSubCategory);
    }

    // Delete a specific sub-category by ID
    @Transactional
    public void deleteSubCategory(Long id) {
        SubCategory subCategory = findSubCategoryById(id);
        subCategoryRepository.delete(subCategory);
    }

    // Check if a sub-category exists by ID
    public boolean existsById(Long id) {
        return subCategoryRepository.existsById(id);
    }

    // Save or update a sub-category
    private SubCategory saveSubCategory(SubCategorySaveRequestDto input, Category category, SubCategory subCategory) {
        subCategory.setName(input.getName());
        subCategory.setDescription(input.getDescription());
        subCategory.setCategory(category);

        return subCategoryRepository.save(subCategory);
    }

    // Convert SubCategory entity to DTO
    private SubCategoryResponseDto convertToDto(SubCategory subCategory) {
        SubCategoryResponseDto dto = new SubCategoryResponseDto();
        dto.setId(subCategory.getId());
        dto.setName(subCategory.getName());
        dto.setDescription(subCategory.getDescription());
        dto.setCategoryId(subCategory.getCategory().getId());
        return dto;
    }

    // Find sub-category by ID with exception handling
    @Transactional(readOnly = true)
    protected SubCategory findSubCategoryById(Long id) {
        return subCategoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sub-category not found with ID: " + id));
    }

    // Find category by ID with exception handling
    @Transactional(readOnly = true)
    protected Category findCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + id));
    }
}