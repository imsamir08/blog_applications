package com.samir.blog.controllers;

import com.samir.blog.payloads.ApiResponse;
import com.samir.blog.payloads.CategoryDto;
import com.samir.blog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

//    create
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
        CategoryDto createdCategoryDto=categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(createdCategoryDto, HttpStatus.CREATED);
    }
//    update
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId){
        CategoryDto updateCategoryDto=categoryService.updateCategory(categoryDto, categoryId);
        return ResponseEntity.ok(updateCategoryDto);
    }
//    delete
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new ApiResponse("Category Deleted Succesfully", true), HttpStatus.OK);
    }
//    get
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId){
        return ResponseEntity.ok(categoryService.getCategory(categoryId));
    }
//    getAll
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        return ResponseEntity.ok(categoryService.getAllCategory());
    }
}
