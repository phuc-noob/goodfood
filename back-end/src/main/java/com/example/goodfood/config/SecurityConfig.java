package com.example.goodfood.config;

import com.example.goodfood.filler.CustomAuthenticationFiller;
import com.example.goodfood.filler.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import java.util.Arrays;

import static org.springframework.http.HttpMethod.*;

@Configuration @EnableWebSecurity @RequiredArgsConstructor @RestController @CrossOrigin(value = "http://localhost:3000")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and();
        CustomAuthenticationFiller customAuthenticationFilter = new CustomAuthenticationFiller(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/auth/login");

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests().antMatchers(GET,"/home/hello").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers("/api/register").permitAll().and().httpBasic();
        http.authorizeRequests().antMatchers(GET,"/api/categorys").permitAll().and().httpBasic();
        http.authorizeRequests().antMatchers(GET,"/api/products").permitAll().and().httpBasic();
        http.authorizeRequests().antMatchers(GET,"/api/users").hasAnyAuthority("ROLE_ADMIN").and().httpBasic();

        http.authorizeRequests().antMatchers("/api/login/**","/token/refresh/**").permitAll();
        http.authorizeRequests().antMatchers(POST,"/api/user/**").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(POST,"/api/category/**").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(POST,"/api/product/**").hasAnyAuthority("ROLE_SELLER");

        http.authorizeRequests().antMatchers(PUT,"api/category/**").hasAnyAuthority("ROLE_ADMIN","ROLE_SELLER");
        http.authorizeRequests().antMatchers(PUT,"api/product/**").hasAnyAuthority("ROLE_ADMIN","ROLE_SELLER");;

        http.authorizeRequests().antMatchers(DELETE,"/api/category/**").hasAnyAuthority("ROLE_SELLER","ROLE_ADMIN");
        http.authorizeRequests().antMatchers(DELETE,"/api/product/**").hasAnyAuthority("ROLE_SELLER","ROLE_ADMIN");
        http.authorizeRequests().anyRequest().authenticated();      // check roles -> allow all user
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource()
    {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.addAllowedHeader("*");
        configuration.addAllowedOrigin("*");
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
}
