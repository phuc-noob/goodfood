package com.example.goodfood.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int cate_id;
    private String cate_name;
}
