package com.example.goodfood;

import com.example.goodfood.entity.Role;
import com.example.goodfood.entity.User;
import com.example.goodfood.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
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

		// sent tokens to the body
		new ObjectMapper().writeValue(response.getOutputStream(),tokens);
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

			userService.addRoleToUser("pucnoob","ROLE_ADMIN");
		};
	}

}
