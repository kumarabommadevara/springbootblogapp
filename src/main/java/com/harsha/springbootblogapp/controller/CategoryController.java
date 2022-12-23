package com.harsha.springbootblogapp.controller;

import com.harsha.springbootblogapp.payloads.CategoryDto;
import com.harsha.springbootblogapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping()
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {

        CategoryDto createdCategory = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);

    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable("categoryId") int categoryId) {

        CategoryDto updatedCategory = categoryService.updateCategory(categoryDto, categoryId);
        return new ResponseEntity<>(updatedCategory, HttpStatus.ACCEPTED);

    }

    @GetMapping()
    public ResponseEntity<List<CategoryDto>> getAllCategories() {

        List<CategoryDto> categoriesList = categoryService.getAllCategories();
        return new ResponseEntity<>(categoriesList, HttpStatus.OK);

    }

    @GetMapping("/id/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("categoryId") int categoryId) {

        CategoryDto category = categoryService.getCategoryById(categoryId);
        return new ResponseEntity<>(category, HttpStatus.OK);

    }

    @DeleteMapping("/id/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable("categoryId") int categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>("Deleted Categpry Successfully", HttpStatus.OK);
    }
}
