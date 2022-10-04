package com.example.goodfood.service;

import com.example.goodfood.entity.Category;
import com.example.goodfood.repo.ICategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service @RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService{
    private final ICategoryRepo categoryRepo;
    @Override
    public List<Category> getAllCategory() {
        return categoryRepo.findAll();
    }
}
