package com.samir.blog.services.impl;

import com.samir.blog.entities.Category;
import com.samir.blog.exceptions.ResourceNotFoundException;
import com.samir.blog.payloads.CategoryDto;
import com.samir.blog.repositories.CategoryRepo;
import com.samir.blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category=modelMapper.map(categoryDto, Category.class);
        Category savedCategory=categoryRepo.save(category);
        return modelMapper.map(savedCategory, CategoryDto.class);
    }
    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category=categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", categoryId));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updatedCategory=categoryRepo.save(category);
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category category=categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", categoryId));
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories=categoryRepo.findAll();
        List<CategoryDto> categoriesDtos=categories.stream().map((category)-> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
        return categoriesDtos;
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category=categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", categoryId));
        categoryRepo.delete(category);
    }
}
