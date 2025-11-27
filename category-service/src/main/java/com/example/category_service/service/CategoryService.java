package com.example.category_service.service;

import com.example.category_service.dto.CategoryRequest;
import com.example.category_service.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {

    CategoryResponse createCategory(CategoryRequest categoryRequest);
    CategoryResponse updateCategory(CategoryRequest categoryRequest, Long id);
    void deleteCategory(Long id);
    CategoryResponse getCategoryById(Long id);
    List<CategoryResponse> getAllCategories();
}