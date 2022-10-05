package com.example.goodfood.filler;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.goodfood.filler.jwt.ExtractToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class CustomAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // check case user is not login
        if(request.getServletPath().equals("/api/login") ||request.getServletPath().equals("/token/refresh"))
        {
            filterChain.doFilter(request,response);
        }else // user was loged
        {
            String authoricationHeader = request.getHeader("AUTHORIZATION");
            if(authoricationHeader != null && authoricationHeader.startsWith("Bearer "))
            {
                try{
                    System.out.println("Try to authorization " );
                    String username = ExtractToken.getUsername(request);
                    String roles[] = ExtractToken.getRoles(request);
                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    stream(roles).forEach(role ->{
                        authorities.add(new SimpleGrantedAuthority(role));
                    });
                    // password : null value
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,null,authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                    filterChain.doFilter(request,response);
                }catch (Exception ex){
                    System.out.println("Error logging to : " + ex.toString());
                    response.setHeader("error" ,ex.getMessage().toString());
                    response.setStatus(UNAUTHORIZED.value());
                    // response.sendError(FORBIDDEN.value());

                    Map<String,String> error = new HashMap<>();
                    error.put("error_message",ex.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);

                    // sent error to the body
                    new ObjectMapper().writeValue(response.getOutputStream(),error);
                }

            }else{
                filterChain.doFilter(request,response);
            }
        }
    }

}
