package com.harsha.springbootblogapp.service.impl;

import com.harsha.springbootblogapp.entity.Category;
import com.harsha.springbootblogapp.exception.ResourceNotFoundException;
import com.harsha.springbootblogapp.payloads.CategoryDto;
import com.harsha.springbootblogapp.repository.CategoryRepository;
import com.harsha.springbootblogapp.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category;
        category = modelMapper.map(categoryDto, Category.class);
        Category savedCategory = this.categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {

        if (categoryRepository.findById(categoryId).isPresent()) {
            Category existingCategory = categoryRepository.findById(categoryId).get();
            existingCategory.setCategoryName(categoryDto.getCategoryName());
            existingCategory.setCategoryDescription(categoryDto.getCategoryDescription());
            Category updatedCategory = categoryRepository.save(existingCategory);

            return modelMapper.map(updatedCategory, CategoryDto.class);
        } else {
            throw new ResourceNotFoundException("Resource is not found");
        }

    }


    @Override
    public void deleteCategory(int categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category provided is not present"));
        categoryRepository.delete(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        return modelMapper.map(categoryList, new TypeToken<List<CategoryDto>>() {
        }.getType());
    }

    @Override
    public CategoryDto getCategoryById(int categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category provided is not present"));
        return modelMapper.map(category, CategoryDto.class);


    }
}
