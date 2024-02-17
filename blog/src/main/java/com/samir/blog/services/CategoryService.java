package com.samir.blog.services;

import com.samir.blog.payloads.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CategoryService {
//    Create
    public CategoryDto createCategory(CategoryDto categoryDto);
//    Update
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

//    Get
    public CategoryDto getCategory(Integer categoryId);

//    GetAll
    public List<CategoryDto> getAllCategory();

//    Delete
    public void deleteCategory(Integer categoryId);

}
