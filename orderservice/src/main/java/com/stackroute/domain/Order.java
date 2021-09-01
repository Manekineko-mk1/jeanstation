package com.stackroute.domain;

import com.stackroute.enums.OrderStatus;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootApplication(scanBasePackages = "com.stackroute")
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    private String userId;
    private int priceTotal;
    private LocalDate creationDate;
    private LocalDate deliveryDate;
    private OrderStatus status;
    List<Product> orderItems;

    public Order(String userId, int priceTotal, List<Product> orderItems) {
        this.userId = userId;
        this.priceTotal = priceTotal;
        this.orderItems = orderItems;

        log.info("A new order is created: {} | {}", this.getId());
    }

    public Order(String userId, int priceTotal, List<Product> orderItems, LocalDate creationDate, LocalDate deliveryDate) {
        this.userId = userId;
        this.priceTotal = priceTotal;
        this.orderItems = orderItems;

        this.creationDate = creationDate;
        this.deliveryDate = deliveryDate;
        this.status = OrderStatus.SUBMITTED;

        log.info("A new order is created: {} | {}", this.getId());
    }
}