package com.example.goodfood.controller;

import com.example.goodfood.dto.response.Response;
import com.example.goodfood.entity.Category;
import com.example.goodfood.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryController {
    private final ICategoryService categoryService;
    @GetMapping("/categorys")
    public void getCategory(HttpServletResponse response) throws IOException {
        Response.ResponseHttp(response,200,"list category",categoryService.getAllCategory());
    }
}
