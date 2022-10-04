package com.example.goodfood.service;

import com.example.goodfood.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;
public interface ICategoryService {
    List<Category> getAllCategory();
}
