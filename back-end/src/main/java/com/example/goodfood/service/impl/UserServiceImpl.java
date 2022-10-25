package com.example.goodfood.service.impl;

import com.example.goodfood.entity.Role;
import com.example.goodfood.entity.User;
import com.example.goodfood.repo.IRoleRepo;
import com.example.goodfood.repo.IUserRepo;
import com.example.goodfood.service.inf.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements IUserService, UserDetailsService {
    public final IUserRepo userRepo;
    public final IRoleRepo roleRepo;
    public final PasswordEncoder passwordEncoder;
    @Override
    public User saveUser(User user) {

        if(getUser(user.getUsername()) ==null){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            LocalDateTime now = LocalDateTime.now();
            Date d = java.sql.Timestamp.valueOf(now);
            user.setCreateAt(d);
            return userRepo.save(user);
        }
        else{
            log.info("user alredy exist {}",user.getUsername());
            return null;
        }
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database ",role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String name) {
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
        return userRepo.findAll();
    }
}
