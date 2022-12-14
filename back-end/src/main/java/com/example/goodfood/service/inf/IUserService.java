package com.example.goodfood.service.inf;

import com.example.goodfood.entity.Role;
import com.example.goodfood.entity.User;

import java.util.List;

public interface IUserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username,String name);
    User getUser(String username);
    List<User> getUsers();
}
