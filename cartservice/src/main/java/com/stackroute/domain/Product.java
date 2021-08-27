package com.stackroute.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    private String id;
    private String productId;
    private String name;
    private String description;
    private String picture;
    private Money price;
    private int discount;
    private int quantity;
    private String size;
    private String color;

    public Product(String productId, String name, String description, String picture, Money price, int discount, int quantity, String size, String color) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.picture = picture;
        this.price = price;
        this.discount = discount;
        this.quantity = quantity;
        this.size = size;
        this.color = color;
    }
}
