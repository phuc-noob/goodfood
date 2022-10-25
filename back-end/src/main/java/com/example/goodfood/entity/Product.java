package com.example.goodfood.entity;

import lombok.*;
import org.w3c.dom.Text;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "product_name", unique = true)
    private String productName;
    @Column(name = "picture", unique = true)
    private String picture;
    @Column(name = "description")
    private String description;
    @Column(name = "unit_price",unique = false)
    private float unitPrice;
    @Column(name = "discount")
    private float discount;
    @Column(name = "create_at")
    private LocalDateTime createAt;
    @Column(name = "state")
    private String state;
    @ManyToMany(fetch = FetchType.EAGER)
    Collection<Category> categories= new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private User user;
}
