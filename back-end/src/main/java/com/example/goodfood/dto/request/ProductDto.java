package com.example.goodfood.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter @Setter
public class ProductDto {
    private Long productId;
    private String productName;
    private String picture;
    private String description;
    private float unitPrice;
    private float discount;
    private LocalDateTime createAt;
    private String state;
}
