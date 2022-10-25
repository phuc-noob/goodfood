package com.example.goodfood.repo;

import com.example.goodfood.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepo extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
