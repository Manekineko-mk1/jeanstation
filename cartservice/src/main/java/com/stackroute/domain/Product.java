package com.stackroute.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "products")
public class Product {
    @Id
    private String id;
    private String productName;
    private String productDescription;
    private String picture;
    private float priceCAD;
    private float discount;
    private int quantity;
    private String productSize;
    private String productColor;

    public Product(String productName, String productDescription, String picture, float priceCAD, float discount, int quantity, String productSize, String productColor) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.picture = picture;
        this.priceCAD = priceCAD;
        this.discount = discount;
        this.quantity = quantity;
        this.productSize = productSize;
        this.productColor = productColor;
    }
}
