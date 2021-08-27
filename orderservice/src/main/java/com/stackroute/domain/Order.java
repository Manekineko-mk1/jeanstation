package com.stackroute.domain;

import com.stackroute.enums.OrderStatus;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDate;
import java.util.ArrayList;

@Slf4j
@SpringBootApplication(scanBasePackages = "com.stackroute")
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    private float priceTotal;
    private String userId;
    private LocalDate deliveryDate;
    private LocalDate creationDate;
    private OrderStatus status;
    ArrayList<String> orderItems;
//    ArrayList<Product> orderItems;

    public Order(float priceTotal, ArrayList<String> orderItems, String userId) {
        this.priceTotal = priceTotal;
        this.orderItems = orderItems;
        this.userId = userId;
        //this.creationDate = LocalDate.now();
        //this.deliveryDate = this.creationDate.plusDays(3l);
        //this.status = OrderStatus.SUBMITTED;

        log.info("A new order is created: {} | {}", this.getId());
    }
}