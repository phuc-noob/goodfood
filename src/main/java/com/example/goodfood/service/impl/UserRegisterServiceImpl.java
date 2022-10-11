package com.example.goodfood.service.impl;

import com.example.goodfood.entity.ERole;
import com.example.goodfood.entity.User;
import com.example.goodfood.service.inf.IUserRegisterService;
import com.example.goodfood.service.inf.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor @Service @Slf4j
public class UserRegisterServiceImpl implements IUserRegisterService {
    private final IUserService userService;
    @Override
    public boolean tryToRegisterUser(HttpServletRequest request) {
        String username ;
        String password,confirmPassword ;
        List<String> roles = new ArrayList<>();
        roles.add(ERole.ROLE_USER.toString());
        try {
            byte[] inputStreamBytes = StreamUtils.copyToByteArray(request.getInputStream());
            Map<String, String> jsonRequest = new ObjectMapper().readValue(inputStreamBytes, Map.class);

            username = jsonRequest.get("username");
            password = jsonRequest.get("password");
            confirmPassword = jsonRequest.get("confirm_password");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(userService.getUser(username) ==null && password.equals(confirmPassword)){
            userService.saveUser(new User(null,"",username,password,"","",0,null,new ArrayList<>()));
            return true;
        }else{

            return false;
        }
    }

}
