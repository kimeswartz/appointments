package com.useo.demo.dtos.catalog;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SubCategorySaveRequestDto {

    @JsonProperty("name")
    @NotEmpty(message = "The name is required.")
    @Size(min = 2, max = 100, message = "The length of name must be between 2 and 100 characters.")
    private String name;

    @JsonProperty("description")
    @NotEmpty(message = "The description is required.")
    @Size(min = 2, max = 500, message = "The length of description must be between 2 and 500 characters.")
    private String description;

    @JsonProperty("categoryId")
    @NotNull(message = "The category ID is required.")
    private Long categoryId;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public SubCategorySaveRequestDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SubCategorySaveRequestDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public SubCategorySaveRequestDto setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
        return this;
    }
}
