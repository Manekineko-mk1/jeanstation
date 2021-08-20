package com.stackroute.test.controller;

import com.stackroute.domain.Order;
import com.stackroute.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value = "/api/v1")
public class OrderControllerIntegrationTest {

    private final OrderService orderService;

    @Autowired
    public OrderControllerIntegrationTest(OrderService orderService) {
        this.orderService = orderService;
    }


    /**
     * save a new Order
     */
    @PostMapping("/order")
    public ResponseEntity<Order> saveOrder(@RequestBody Order order) {
        Order savedOrder = orderService.saveOrder(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }


    /**
     * retrieve all Orders
     */
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);

    }

    /**
     * retrieve Order by id
     */
    @GetMapping("order/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable("orderId") String orderId) {
        return new ResponseEntity<>(orderService.getOrderById(orderId), HttpStatus.FOUND);
    }

    /**
     * delete Order by id
     */
    @DeleteMapping("order/{orderId}")
    public ResponseEntity<Order> getOrderAfterDeleting(@PathVariable("OrderId") String orderId) {
        return new ResponseEntity<>(orderService.deleteOrder(orderId), HttpStatus.OK);
    }

    /**
     * update Order
     */
    @PutMapping("order")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
        Order updatedOrder = orderService.updateOrder(order);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }
    
}

