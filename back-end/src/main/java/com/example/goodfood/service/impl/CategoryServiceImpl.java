package com.example.goodfood.service.impl;

import com.example.goodfood.dto.request.CategoryDto;
import com.example.goodfood.entity.Category;
import com.example.goodfood.repo.ICategoryRepo;
import com.example.goodfood.service.inf.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
@Service @RequiredArgsConstructor @Transactional
public class CategoryServiceImpl implements ICategoryService {
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


    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto,Long cateId) {
        Category category = new Category(categoryDto);
        if(categoryRepo.updateCategoryName(cateId,categoryDto.getCateName())==1){
            return categoryDto;
        }else{
            return null;
        }
    }

    @Override
    public boolean deleteCategory(Long id) {
        try {
            categoryRepo.deleteById(id);
            return true;
        }catch (Exception ex){
            return false;
        }
    }
}
