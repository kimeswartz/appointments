package com.useo.demo.dtos.catalog;

public class SubCategoryResponseDto {

    private Long id;
    private String name;
    private String description;
    private Long categoryId;

    public Long getId() {
        return id;
    }

    public SubCategoryResponseDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SubCategoryResponseDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SubCategoryResponseDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public SubCategoryResponseDto setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
        return this;
    }
}