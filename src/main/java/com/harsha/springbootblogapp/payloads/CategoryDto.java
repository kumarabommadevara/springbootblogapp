package com.harsha.springbootblogapp.payloads;


import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CategoryDto {
    private Integer categoryId;
    @NotNull
    @Size(min = 3, max = 10, message = "Category name should be min 3 characters atleast and maximum 10")
    private String categoryName;
    @NotNull(message = "Category Description cannot be null")
    private String categoryDescription;
}
