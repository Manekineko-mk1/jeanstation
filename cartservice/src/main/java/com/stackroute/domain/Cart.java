package com.stackroute.domain;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Slf4j
// @SpringBootApplication(scanBasePackages = "com.stackroute")
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "carts")
public class Cart {
    @Id
    private String id;
    private int priceTotalBeforeTax;
    private int priceTotalAfterTax;
    List<Product> cartItems;

    public Cart(int priceTotalBeforeTax) {
        this.priceTotalBeforeTax = priceTotalBeforeTax;
        this.cartItems = new ArrayList<>();

        log.info("A new cart is created: {} | {}", this.getId());
    }

    public Cart(int priceTotalBeforeTax, List<Product> cartItems) {
        this.priceTotalBeforeTax = priceTotalBeforeTax;
        this.cartItems = cartItems;

        log.info("A new cart is created: {} | {}", this.getId());
    }

    public Cart(int priceTotalBeforeTax, int priceTotalAfterTax, List<Product> cartItems) {
        this.priceTotalBeforeTax = priceTotalBeforeTax;
        this.priceTotalAfterTax = priceTotalAfterTax;
        this.cartItems = cartItems;

        log.info("A new cart is created: {} | {}", this.getId());
    }
}