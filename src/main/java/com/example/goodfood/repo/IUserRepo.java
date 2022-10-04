package com.example.goodfood.repo;

import com.example.goodfood.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
