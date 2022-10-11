package com.example.goodfood.service.impl;

import com.example.goodfood.dto.request.ProductDto;
import com.example.goodfood.entity.Product;
import com.example.goodfood.repo.IProductRepo;
import com.example.goodfood.service.inf.IProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductSeviceImpl implements IProductService {
    private final ModelMapper modelMapper;
    private final IProductRepo productRepo;
    @Override
    public ProductDto saveProduct(ProductDto productDto) {
        Product product = modelMapper.map(productDto,Product.class);
        return modelMapper.map(productRepo.save(product), ProductDto.class);
    }
    @Override
    public List<ProductDto> getAllproduct() {
        return productRepo.findAll().stream().map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
    }

    @Override
    public void updateProduct(Long id,ProductDto productDto) {
        productRepo.findById(id).ifPresent(product -> {
            product.setProductName(productDto.getProductName());
            product.setPicture(productDto.getPicture());
            product.setDiscount(productDto.getDiscount());
            product.setState(productDto.getState());
            product.setDescription(productDto.getDescription());
            product.setState(productDto.getState());
            product.setUnitPrice(productDto.getUnitPrice());
            productRepo.save(product);
        });
    }

    @Override
    public boolean deleteProductById(Long id) {
        try {
            productRepo.deleteById(id);
            return true;
        }
        catch (Exception exception)
        {
            return false;
        }
    }

    @Override
    public List<ProductDto> getProduct(int page, int size) {
        return productRepo.getProducts(page, size)
                .stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }
}
