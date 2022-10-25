package com.example.goodfood.service.inf;

import com.example.goodfood.dto.request.CategoryDto;
import com.example.goodfood.entity.Category;

import java.util.List;
public interface ICategoryService {
    List<Category> getAllCategory();
    CategoryDto saveCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto, Long cateId);
    boolean deleteCategory(Long id);
}
