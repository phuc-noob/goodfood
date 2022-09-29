package com.example.goodfood.filler.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.servlet.http.HttpServletRequest;

public class ExtractToken {
    public static String getUsername(HttpServletRequest request)
    {
        String username;
        String token = request.getHeader("AUTHORIZATION").substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();          //the same algorithm used for authentication
        DecodedJWT decodedJWT = verifier.verify(token);
        username = decodedJWT.getSubject();
        return username;
    }
    public static String[] getRoles (HttpServletRequest request)
    {
        String token = request.getHeader("AUTHORIZATION").substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();          //the same algorithm used for authentication
        DecodedJWT decodedJWT = verifier.verify(token);
        String roles[] = decodedJWT.getClaim("roles").asArray(String.class);
        return roles;
    }
}
