package com.samir.blog.payloads;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class CategoryDto {
    private Integer categoryId;
    @NotEmpty
    @Size(min = 4, message = "Minimum size of category title is 4")
    private String categoryTitle;
    @NotEmpty
    @Size(min = 10, message = "Minimum size of category description is 10")
    private String categoryDescription;
}
