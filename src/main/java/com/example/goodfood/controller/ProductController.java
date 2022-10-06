package com.example.goodfood.controller;

import com.example.goodfood.dto.request.ProductDto;
import com.example.goodfood.dto.response.Response;
import com.example.goodfood.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService productService;
    @PostMapping("/product/save")
    public void saveProduct(@RequestBody ProductDto productDto, HttpServletResponse response) throws IOException {
        ProductDto test = productService.saveProduct(productDto);
        if(test!=null)
        {
            Response.ResponseHttp(response, HttpStatus.OK.value(), "save product success",productDto);
        }else{
            Response.ResponseHttp(response, HttpStatus.FORBIDDEN.value(), "save product fail",null);
        }
    }
    @GetMapping("/products")
    private void getAllproduct(HttpServletResponse response) throws IOException {
        List<ProductDto> productDtos = new ArrayList<>();
        productDtos = productService.getAllproduct();
        Response.ResponseHttp(response,200,"all product",productDtos);
    }
    @PutMapping("/product/put/{id}")
    private void updateProduct(@RequestBody ProductDto productDto,@PathVariable Long id,HttpServletResponse response)
    {
        productService.updateProduct(id, productDto);
    }

    @DeleteMapping("/product/delete/{id}")
    private void deleteProduct(@PathVariable Long id,HttpServletResponse response) throws IOException {
        if(productService.deleteProductById(id))
        {
            Response.ResponseHttp(response,HttpStatus.OK.value(), "delete product done",null);
        }else{
            Response.ResponseHttp(response,HttpStatus.FORBIDDEN.value(), "delete product fail",null);
        }
    }
}
