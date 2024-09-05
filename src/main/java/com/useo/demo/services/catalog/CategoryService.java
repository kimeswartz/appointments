package com.useo.demo.services.catalog;

import com.useo.demo.dtos.catalog.CategorySaveRequestDto;
import com.useo.demo.dtos.catalog.CategoryResponseDto;
import com.useo.demo.entities.catalog.Category;
import com.useo.demo.entities.catalog.SubCategory;
import com.useo.demo.repositories.catalog.CategoryRepository;
import com.useo.demo.repositories.catalog.SubCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;

    public CategoryService(CategoryRepository categoryRepository, SubCategoryRepository subCategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.subCategoryRepository = subCategoryRepository;
    }

    public List<CategoryResponseDto> findAll() {
        List<Category> categories = new ArrayList<>();
        categoryRepository.findAll().forEach(categories::add);

        List<CategoryResponseDto> categoryDtos = new ArrayList<>();
        for (Category category : categories) {
            categoryDtos.add(convertToDto(category));
        }

        return categoryDtos;
    }

    public CategoryResponseDto createCategory(CategorySaveRequestDto input) {
        var category = new Category()
                .setName(input.getName());

        return convertToDto(saveCategoryWithServices(input, category));
    }

    public CategoryResponseDto updateCategory(Long id, CategorySaveRequestDto input) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            throw new IllegalArgumentException("Category not found with ID: " + id);
        }

        var category = optionalCategory.get()
                .setName(input.getName());

        return convertToDto(saveCategoryWithServices(input, category));
    }

    private Category saveCategoryWithServices(CategorySaveRequestDto input, Category category) {
        if (input.getAssociatedServiceIds() != null && !input.getAssociatedServiceIds().isEmpty()) {
            List<SubCategory> services = new ArrayList<>();
            for (Long serviceId : input.getAssociatedServiceIds()) {
                Optional<SubCategory> optionalService = subCategoryRepository.findById(serviceId);
                optionalService.ifPresent(services::add);
            }
            category.setTreatments(services);
        }

        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new IllegalArgumentException("Category not found with ID: " + id);
        }
        categoryRepository.deleteById(id);
    }

    private CategoryResponseDto convertToDto(Category category) {
        CategoryResponseDto dto = new CategoryResponseDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }
}