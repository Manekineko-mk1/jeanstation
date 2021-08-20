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
    private String productName;
    private String productDescription;
    private String picture;
    private float priceCAD;
    private float discount;
    private int quantity;
    private String productSize;
    private String productColor;

    public Product(String productId, String productName, String productDescription, String picture, float priceCAD, float discount, int quantity, String productSize, String productColor) {
        this.productId = productId;
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
