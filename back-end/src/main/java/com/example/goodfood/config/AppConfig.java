package com.example.goodfood.config;

import com.example.goodfood.entity.User;
import com.example.goodfood.service.inf.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
@SpringBootConfiguration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    CommandLineRunner run (IUserService userService){
        return args -> {
//			userService.saveRole(new Role(null,"ROLE_USER"));
//			userService.saveRole(new Role(null,"ROLE_SELLER"));
//			userService.saveRole(new Role(null,"ROLE_MANAGER"));
//			userService.saveRole(new Role(null,"ROLE_ADMIN"));
//			userService.saveRole(new Role(null,"ROLE_SUPPER_ADMIN"));
//
			userService.saveUser(new User(null,"My Hoai Le","guest","00000000","0000000000","myhoaile@gmail.com",10,null,new ArrayList<>()));
//			userService.saveUser(new User(null,"User ","user","00000000","0000000000","myhoaile@gmail.com",10,null,new ArrayList<>()));
//			userService.saveUser(new User(null,"Seller ","seller","00000000","0000000000","myhoaile@gmail.com",10,null,new ArrayList<>()));
//			userService.saveUser(new User(null,"Admin ","admin","00000000","0000000000","myhoaile@gmail.com",10,null,new ArrayList<>()));
//
//			userService.addRoleToUser("admin","ROLE_ADMIN");
//			userService.addRoleToUser("user","ROLE_USER");
//			userService.addRoleToUser("seller","ROLE_SELLER");
        };
    }

}
