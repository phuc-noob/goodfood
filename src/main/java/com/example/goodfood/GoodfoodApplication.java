package com.example.goodfood;

import com.example.goodfood.entity.Role;
import com.example.goodfood.entity.User;
import com.example.goodfood.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@SpringBootApplication
@RestController
@RequestMapping("/home")
public class GoodfoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodfoodApplication.class, args);
	}

	@GetMapping("/hello")
	public void hello( HttpServletResponse response) throws IOException {
		Map<String,String> tokens = new HashMap<>();
		tokens.put("access_token","this is hello access token");
		tokens.put("refresh_token","this is hello refresh_token");
		response.setContentType(APPLICATION_JSON_VALUE);
		new ObjectMapper().writeValue(response.getOutputStream(),tokens);
	}
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
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
//			userService.saveUser(new User(null,"My Hoai Le","MyHoaiLe","00000000","0000000000","myhoaile@gmail.com",10,null,new ArrayList<>()));
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
