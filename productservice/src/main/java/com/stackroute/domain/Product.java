package com.stackroute.domain;

import com.stackroute.enums.ProductColor;
import com.stackroute.enums.ProductSize;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "products")
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private String picture;
    private Money price;
    private Money finalPrice;
    private int discount;
    private int quantity;
    private String size;
    private String color;
    List<String> categories;

    public Product(String name, String description, String picture, Money price, int discount,
                   int quantity, List<String> categories) {
        this.name = name;
        this.description = description;
        this.picture = picture;
        this.price = price;
        this.discount = discount;
        this.quantity = quantity;
        this.categories = categories;

        int finPrice = this.price.getAmount() * (1 - this.discount);
        this.finalPrice = new Money(finPrice, price.getCurrency());

        log.info("A new product is created: {} | {}", this.getId(), this.name);
    }

    public Product(String name, String description, String picture, Money price, int discount,
                   int quantity, String size, String color, List<String> categories) {
        this.name = name;
        this.description = description;
        this.picture = picture;
        this.price = price;
        this.discount = discount;
        this.quantity = quantity;
        this.size = size;
        this.color = color;
        this.categories = categories;

        log.info("A new product is created: {} | {}", this.getId(), this.name);
    }
}
