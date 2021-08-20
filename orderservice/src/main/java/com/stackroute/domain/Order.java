package com.stackroute.domain;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.ArrayList;

@Slf4j
@Data
@NoArgsConstructor
@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    private float priceTotal;
    ArrayList<String> orderItems;
//    ArrayList<Product> orderItems;

    public Order(float priceTotal, ArrayList<String> orderItems) {
        this.priceTotal = priceTotal;
        this.orderItems = orderItems;

        log.info("A new order is created: {} | {}", this.id);
    }
}