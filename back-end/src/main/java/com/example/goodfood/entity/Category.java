package com.example.goodfood.entity;

import com.example.goodfood.dto.request.CategoryDto;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cate_id", nullable = false)
    private Long cateId;
    @Column(name = "cate_name", nullable = true)
    private String cateName;

    public Category(CategoryDto categoryDto)
    {
        this.cateId = categoryDto.getCateId();
        this.cateName = categoryDto.getCateName();
    }
}
