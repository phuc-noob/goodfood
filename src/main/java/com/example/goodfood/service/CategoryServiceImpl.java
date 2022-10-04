package com.example.goodfood.service;

import com.example.goodfood.dto.request.CategoryDto;
import com.example.goodfood.entity.Category;
import com.example.goodfood.repo.ICategoryRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service @RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService{
    private final ICategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<Category> getAllCategory() {
        return categoryRepo.findAll();
    }

    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {
        Category category = new Category(categoryDto);
        if(categoryRepo.findByCateName(category.getCateName())==null){
            return new CategoryDto(categoryRepo.save(category));
        }else{
            return null;
        }
    }
}
