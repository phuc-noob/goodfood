package com.example.goodfood.dto.request;

import com.example.goodfood.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private int cateId;
    private String cateName;
    public CategoryDto(Category category)
    {
        this.cateId = category.getCateId();
        this.cateName = category.getCateName();
    }
}
