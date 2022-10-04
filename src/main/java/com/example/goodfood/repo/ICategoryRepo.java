package com.example.goodfood.repo;

import com.example.goodfood.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ICategoryRepo extends JpaRepository<Category,Long> {
    Category findByCateName(String name);
}
