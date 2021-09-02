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
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    private String userId;
    private int priceTotalBeforeTax;
    private int priceTotalAfterTax;
    private LocalDate creationDate;
    private LocalDate deliveryDate;
    private OrderStatus status;
    List<Product> orderItems;

    public Order(String userId, int priceTotalBeforeTax, int priceTotalAfterTax, List<Product> orderItems) {
        this.userId = userId;
        this.priceTotalBeforeTax = priceTotalBeforeTax;
        this.priceTotalAfterTax = priceTotalAfterTax;
        this.orderItems = orderItems;

        this.creationDate = LocalDate.now();
        this.deliveryDate = this.creationDate.plusDays(3l);
        this.status = OrderStatus.SUBMITTED;

        log.info("A new order is created: {} | {}", this.getId());
    }
}