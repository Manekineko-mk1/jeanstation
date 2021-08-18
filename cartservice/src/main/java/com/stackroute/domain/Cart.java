package com.stackroute.domain;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.ArrayList;

@Slf4j
@Data
@NoArgsConstructor
@Document(collection = "carts")
public class Cart {
    @Id
    private String id;
    private String cartName;
    private String cartDescription;
    private String picture;
    private float priceCAD;
    private float discount;
    private int quantity;
    ArrayList<String> cartCategories;

    public Cart(String cartName, String cartDescription, String picture, float priceCAD, float discount, int quantity, ArrayList<String> cartCategories) {
        this.cartName = cartName;
        this.cartDescription = cartDescription;
        this.picture = picture;
        this.priceCAD = priceCAD;
        this.discount = discount;
        this.quantity = quantity;
        this.cartCategories = cartCategories;

        log.info("A new cart is created: {} | {}", this.id, this.cartName);
    }
}