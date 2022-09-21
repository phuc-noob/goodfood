package com.example.goodfood;

import com.example.goodfood.model.Role;
import com.example.goodfood.model.User;
import com.example.goodfood.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class GoodfoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodfoodApplication.class, args);
	}

	// create bean for password encoder
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run (UserService userService){
		return args -> {
			userService.saveRole(new Role(null,"ROLE_USER"));
			userService.saveRole(new Role(null,"ROLE_SELLER"));
			userService.saveRole(new Role(null,"ROLE_MANAGER"));
			userService.saveRole(new Role(null,"ROLE_ADMIN"));
			userService.saveRole(new Role(null,"ROLE_SUPPER_ADMIN"));

			userService.saveUser(new User(null,"My Hoai Le","MyHoaiLe","00000000","0000000000","myhoaile@gmail.com",new ArrayList<>()));
			userService.saveUser(new User(null,"The Hieu","HieuPc","00000000","000000000","hieu@gmail.com",new ArrayList<>()));
			userService.saveUser(new User(null,"Vinh Phuc","pucnoob","00000000","0000000000","myhoaile@gmail.com",new ArrayList<>()));
			userService.saveUser(new User(null,"David","David","00000000","0000000000","myhoaile@gmail.com",new ArrayList<>()));
			userService.saveUser(new User(null,"Ronaldo","Ronaldo","00000000","0000000000","myhoaile@gmail.com",new ArrayList<>()));
		};
	}

}
