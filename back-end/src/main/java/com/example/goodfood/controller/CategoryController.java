package com.example.goodfood.controller;

import com.example.goodfood.dto.request.CategoryDto;
import com.example.goodfood.dto.response.Response;
import com.example.goodfood.service.inf.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryController {
    private final ICategoryService categoryService;
    @GetMapping("/categorys")
    public void getCategory(HttpServletResponse response) throws IOException {
        Response.ResponseHttp(response,200,"list category",categoryService.getAllCategory());
    }

    @PutMapping("category/{id}")
    private void updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable Long id,HttpServletResponse response) throws IOException {
        if(categoryService.updateCategory(categoryDto,id)==null){
            Response.ResponseHttp(response,HttpStatus.BAD_REQUEST.value(), "update fail ,id was wrong",null);
        }else{
            Response.ResponseHttp(response,HttpStatus.OK.value(), "update success",categoryDto);
        }

    }
    @DeleteMapping("/category/{id}")
    private void deleteCategory(@PathVariable Long id,HttpServletResponse response) throws IOException {
        if(categoryService.deleteCategory(id)){
            Response.ResponseHttp(response,HttpStatus.OK.value(), "delete success",null);
        }else{
            Response.ResponseHttp(response,HttpStatus.FORBIDDEN.value(), "delete fail",null);
        }
    }
    @PostMapping("/category")
    private void saveCategory(@RequestBody CategoryDto categoryDto,HttpServletResponse response) throws IOException {
        if(categoryService.saveCategory(categoryDto)==null){
            Response.ResponseHttp(response, HttpStatus.CREATED.value(), "Category is existed",null);
        }else{
            Response.ResponseHttp(response,200,"save category success",categoryDto);
        }
    }
}
