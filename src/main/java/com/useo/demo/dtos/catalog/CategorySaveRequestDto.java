package com.useo.demo.dtos.catalog;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.List;

public class CategorySaveRequestDto {

    @NotEmpty(message = "The name is required.")
    @Size(min = 2, max = 100, message = "The length of name must be between 2 and 100 characters.")
    private String name;

    private List<Long> associatedServiceIds;

    public String getName() {
        return name;
    }

    public CategorySaveRequestDto setName(String name) {
        this.name = name;
        return this;
    }

    public List<Long> getAssociatedServiceIds() {
        return associatedServiceIds;
    }

    public CategorySaveRequestDto setAssociatedServiceIds(List<Long> associatedServiceIds) {
        this.associatedServiceIds = associatedServiceIds;
        return this;
    }
}