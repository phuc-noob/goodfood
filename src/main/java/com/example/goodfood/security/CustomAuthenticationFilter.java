package com.example.goodfood.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        log.info("Username : {}",username); log.info("Password : {}",password);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        return authenticationManager.authenticate(authenticationToken);
    }

    // send access_token and refresh_token
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // user of userDetail -> get user was authentication
        User user = (User) authResult.getPrincipal();

        // jwt
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        String access_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10*60*1000)) // set time for access token
                .withIssuer(request.getRequestURI().toString())
                .withClaim("roles",user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))     // get roles for the token
                .sign(algorithm);
        String refresh_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10*60*1000)) // set time for access token
                .withIssuer(request.getRequestURI().toString())
                .sign(algorithm);

        Map<String,Object> tokens =new HashMap<>();
        tokens.put("access_token",access_token);
        tokens.put("refresh_token",refresh_token);

        Map<String,Object> apiResponse = new HashMap<>();
        apiResponse.put("status",response.getStatus());
        apiResponse.put("message","Login Success");
        apiResponse.put("data",tokens);
        response.setContentType(APPLICATION_JSON_VALUE);

        // sent tokens to the body
        new ObjectMapper().writeValue(response.getOutputStream(),apiResponse);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        Map<String,Object> tokens =new HashMap<>();

        Map<String,Object> apiResponse = new HashMap<>();
        apiResponse.put("status",response.getStatus());
        apiResponse.put("message","Login Fail");
        apiResponse.put("data",tokens);
        response.setContentType(APPLICATION_JSON_VALUE);

        // sent tokens to the body
        new ObjectMapper().writeValue(response.getOutputStream(),apiResponse);
    }
}
