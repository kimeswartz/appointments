package com.useo.demo.services.services;

import com.useo.demo.dtos.services.ServiceCategoryDto;
import com.useo.demo.dtos.services.CategoryDto;
import com.useo.demo.entities.services.Category;
import com.useo.demo.entities.services.ServiceName;
import com.useo.demo.repositories.services.CategoryRepository;
import com.useo.demo.repositories.services.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ServiceRepository serviceRepository;

    public CategoryService(CategoryRepository categoryRepository, ServiceRepository serviceRepository) {
        this.categoryRepository = categoryRepository;
        this.serviceRepository = serviceRepository;
    }

    public List<CategoryDto> findAll() {
        List<Category> categories = new ArrayList<>();
        categoryRepository.findAll().forEach(categories::add);

        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (Category category : categories) {
            categoryDtos.add(convertToDto(category));
        }

        return categoryDtos;
    }

    public CategoryDto createCategory(ServiceCategoryDto input) {
        var category = new Category()
                .setName(input.getName());

        return convertToDto(saveCategoryWithServices(input, category));
    }

    public CategoryDto updateCategory(Long id, ServiceCategoryDto input) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            throw new IllegalArgumentException("Category not found with ID: " + id);
        }

        var category = optionalCategory.get()
                .setName(input.getName());

        return convertToDto(saveCategoryWithServices(input, category));
    }

    private Category saveCategoryWithServices(ServiceCategoryDto input, Category category) {
        if (input.getServiceIds() != null && !input.getServiceIds().isEmpty()) {
            List<ServiceName> services = new ArrayList<>();
            for (Long serviceId : input.getServiceIds()) {
                Optional<ServiceName> optionalService = serviceRepository.findById(serviceId);
                optionalService.ifPresent(services::add);
            }
            category.setServices(services);
        }

        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new IllegalArgumentException("Category not found with ID: " + id);
        }
        categoryRepository.deleteById(id);
    }

    private CategoryDto convertToDto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }
}
