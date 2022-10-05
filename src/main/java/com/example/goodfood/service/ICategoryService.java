package com.example.goodfood.service;

import com.example.goodfood.dto.request.CategoryDto;
import com.example.goodfood.entity.Category;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
public interface ICategoryService {
    List<Category> getAllCategory();
    CategoryDto saveCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto, Long cateId);
    boolean deleteCategory(Long id);
}
