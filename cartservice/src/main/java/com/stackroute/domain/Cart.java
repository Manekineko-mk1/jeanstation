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
    private float priceTotal;
    ArrayList<String> cartItems;
//    ArrayList<Product> cartItems;

    public Cart(float priceTotal, ArrayList<String> cartItems) {
        this.priceTotal = priceTotal;
        this.cartItems = cartItems;

        log.info("A new cart is created: {} | {}", this.id);
    }
}