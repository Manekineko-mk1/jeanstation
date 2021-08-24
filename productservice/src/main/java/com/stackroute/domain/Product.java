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
    private float price;
    private float discount;
    private int quantity;
    private ProductSize size;
    private ProductColor color;
    List<String> categories;

    public Product(String name, String description, String picture, float price, float discount,
                   int quantity, List<String> categories) {
        this.name = name;
        this.description = description;
        this.picture = picture;
        this.price = price;
        this.discount = discount;
        this.quantity = quantity;
        this.categories = categories;

        log.info("A new product is created: {} | {}", this.getId(), this.name);
    }

    public Product(String name, String description, String picture, float price, float discount,
                   int quantity, ProductSize size, ProductColor color, List<String> categories) {
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
