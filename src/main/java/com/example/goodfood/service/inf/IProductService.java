package com.example.goodfood.service.inf;

import com.example.goodfood.dto.request.ProductDto;

import java.util.List;

public interface IProductService {
    ProductDto saveProduct(ProductDto productDto);
    List<ProductDto> getAllproduct();
    void updateProduct(Long id,ProductDto productDto);
    boolean deleteProductById(Long id);
    List<ProductDto> getProduct(int page,int size);
}
