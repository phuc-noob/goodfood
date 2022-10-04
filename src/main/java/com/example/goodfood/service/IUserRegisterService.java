package com.example.goodfood.service;

import javax.servlet.http.HttpServletRequest;

public interface IUserRegisterService {
    boolean tryToRegisterUser(HttpServletRequest request);
}
