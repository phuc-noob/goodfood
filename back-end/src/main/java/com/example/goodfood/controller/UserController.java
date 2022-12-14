package com.example.goodfood.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.goodfood.dto.request.UserDto;
import com.example.goodfood.dto.response.Response;
import com.example.goodfood.entity.Role;
import com.example.goodfood.entity.User;
import com.example.goodfood.filler.jwt.JwtUtils;

import com.example.goodfood.service.impl.UserRegisterServiceImpl;
import com.example.goodfood.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@ComponentScan({"com.example.goodfood.service"})
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final UserRegisterServiceImpl userRegisterService;
    @PostMapping("/register")
    public void registerUser(HttpServletRequest request,HttpServletResponse response) throws IOException {
        if(userRegisterService.tryToRegisterUser(request)){
            Response.ResponseHttp(response,200,"Register Success",null);
        }else{
            Response.ResponseHttp(response,401,"Username is existed",null);
        }
    }
    @GetMapping("/auth")
    public void getUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = JwtUtils.getUsername(request);
        UserDto userDto = new UserDto(userService.getUser(username));
        Response.ResponseHttp(response,200,"user detail",userDto);
    }
    @GetMapping("/users")
    public void getUsers(HttpServletResponse response) throws IOException {
        Map<String,Object> tokens = new HashMap<>();
        tokens.put("access_token", userService.getUsers());
        tokens.put("refresh_token","this is hello refresh_token");
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(),tokens);
    }
    @PostMapping("/user")
    public ResponseEntity<User> saveUser(@RequestBody User user)
    {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }
    @PostMapping("/role")
    public ResponseEntity<Role> saveUser(@RequestBody Role role)
    {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }
    @PostMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authoricationHeader = request.getHeader("AUTHORIZATION");
        if(authoricationHeader != null && authoricationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authoricationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                User user = userService.getUser(username);
                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10*60*1000))
                        .withIssuer(request.getRequestURI().toString())
                        .withClaim("roles",user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))     // get roles for the token
                        .sign(algorithm);

                Map<String,String> tokens = new HashMap<>();
                tokens.put("access_token",access_token);
                tokens.put("refresh_token",refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),tokens);
            } catch (Exception ex) {
                System.out.println("Error logging to : " + ex.toString());
                response.setHeader("error", ex.getMessage().toString());
                response.setStatus(FORBIDDEN.value());

                Map<String, String> error = new HashMap<>();
                error.put("error_message", ex.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }else{
            throw new RuntimeException("Refresh token is missing ");
        }
    }
}

