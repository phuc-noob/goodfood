package com.example.goodfood.service;

import com.example.goodfood.model.Role;
import com.example.goodfood.model.User;
import com.example.goodfood.repo.RoleRepo;
import com.example.goodfood.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService{
    public final UserRepo userRepo;
    public final RoleRepo roleRepo;
    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to the database",user.getUsername());
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database ",role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String name) {
        log.info("Adding the role {} to the user {}",name,username);
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(name);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        log.info("Fetching the user {} ",username);
        return userRepo.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching the all user ");
        return userRepo.findAll();
    }
}
