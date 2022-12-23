package com.harsha.springbootblogapp.service;

import com.harsha.springbootblogapp.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto, int categoryId);

    void deleteCategory(int categoryId);

    List<CategoryDto> getAllCategories();

    CategoryDto getCategoryById(int categoryId);

}
