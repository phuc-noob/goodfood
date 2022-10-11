package com.example.goodfood.service.inf;

import javax.servlet.http.HttpServletRequest;

public interface IUserRegisterService {
    boolean tryToRegisterUser(HttpServletRequest request);
}
