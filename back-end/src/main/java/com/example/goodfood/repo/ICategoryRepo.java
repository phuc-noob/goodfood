package com.example.goodfood.repo;

import com.example.goodfood.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ICategoryRepo extends JpaRepository<Category,Long> {
    Category findByCateName(String name);
    @Modifying
    @Query("update Category c set c.cateName = :name where c.cateId = :id")
    int updateCategoryName(@Param(value = "id") long cateId, @Param(value = "name") String cateName);
}
