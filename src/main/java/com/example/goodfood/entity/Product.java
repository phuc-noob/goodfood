package com.example.goodfood.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.Text;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long product_id;
    private Long seller_id;
    private String product_name;
    private String picture;
    private String description;
    private float unit_price;
    private float discount;
    private LocalDateTime create_at;
    private String state;
    @ManyToMany(fetch = FetchType.EAGER)
    Collection<Category> categories= new ArrayList<>();
}
