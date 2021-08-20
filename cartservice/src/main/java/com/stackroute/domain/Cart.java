package com.stackroute.domain;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Slf4j
@SpringBootApplication(scanBasePackages = "com.stackroute")
@Data
@NoArgsConstructor
@Document(collection = "carts")
public class Cart {
    @Id
    private String id;
    private float priceTotal;
    List<Product> cartItems;

    public Cart(float priceTotal, List<Product> cartItems) {
        this.priceTotal = priceTotal;
        this.cartItems = cartItems;

        log.info("A new cart is created: {} | {}", getId());
    }
}