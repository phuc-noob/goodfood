package com.example.goodfood.service;

import com.example.goodfood.entity.Role;
import com.example.goodfood.entity.User;
import com.example.goodfood.repo.IRoleRepo;
import com.example.goodfood.repo.IUserRepo;
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
        LocalDateTime now = LocalDateTime.now();
        Date d = java.sql.Timestamp.valueOf(now);
        user.setCreateAt(d);

        log.info("Saving new user {} to the database",user.getUsername());
        // encode password before save user to the database
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if(user == null)
        {
            log.error("User not found in the database ");
            throw new UsernameNotFoundException("User not found in the database ");
        }else{
            log.info("User found in the database : {}",user.getUsername());
        }
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
