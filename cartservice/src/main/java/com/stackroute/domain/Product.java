package com.stackroute.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Slf4j
@Data
@NoArgsConstructor
public class Product {
    private String id;
    private String productName;
    private String productDescription;
    private String picture;
    private float priceCAD;
    private float discount;
    private int quantity;
    ArrayList<String> productCategories;

    public Product(String productName, String productDescription, String picture, float priceCAD, float discount, int quantity, ArrayList<String> productCategories) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.picture = picture;
        this.priceCAD = priceCAD;
        this.discount = discount;
        this.quantity = quantity;
        this.productCategories = productCategories;

        log.info("A new product is created: {} | {}", this.id, this.productName);
    }
}