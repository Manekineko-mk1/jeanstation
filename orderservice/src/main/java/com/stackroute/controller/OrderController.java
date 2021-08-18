package com.stackroute.controller;

import com.stackroute.domain.Order;
import com.stackroute.service.OrderService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RestController
@RequestMapping(value = "/api/v1")
@Slf4j
public class OrderController {

    private final OrderService orderService;
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/uuuu - HH:mm:ss z");

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    /**
     * save a new Order
     */
    @PostMapping("/order")
    @ApiOperation(value = "POST a new Order", notes = "Add a new Order entry to the Order database " +
            "using a provided JSON Order object. Returns the newly created entry " +
            "if the operation is a success.", response = ResponseEntity.class)
    public ResponseEntity<Order> saveOrder(@RequestBody Order order) {
        Order savedOrder = orderService.saveOrder(order);

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        log.info("Added a order to orders collection | Order ID: {} | Timestamp(EST): {}",
                order.getId(), timeStamp);

        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }


    /**
     * retrieve all Orders
     */
    @GetMapping("/orders")
    @ApiOperation(value = "GET all Orders", notes = "GET all Order entries from the Order database. " +
            "Returns the result as a List of Order object in JSON format " +
            "if any entry is found.", response = ResponseEntity.class)
    public ResponseEntity<List<Order>> getAllOrders() {

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        log.info("Query to get all order entries | Timestamp: {}", timeStamp);

        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    /**
     * retrieve Order by id
     */
    @GetMapping("order/{orderId}")
    @ApiOperation(value = "GET a Order by ID", notes = "GET a Order entry from the Order database " +
            "by a provided Order ID. Returns a Order object if found.", response = ResponseEntity.class)
    public ResponseEntity<Order> getOrderById(@PathVariable("orderId") String orderId) {

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        log.info("Query to get a order | Order ID: {} | Timestamp: {}" ,orderId ,timeStamp);

        return new ResponseEntity<>(orderService.getOrderById(orderId), HttpStatus.FOUND);
    }

    /**
     * delete Order by id
     */
    @DeleteMapping("order/{orderId}")
    @ApiOperation(value = "DELETE an existing Order", notes = "Remove a Order entry from the Order database " +
            "by a provided Order ID. Returns the deleted Order object " +
            "if the operation is successful.", response = ResponseEntity.class)
    public ResponseEntity<Order> getOrderAfterDeleting(@PathVariable("OrderId") String orderId) {

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        log.info("Request to DELETE a order | Order ID: {} | Timestamp: {}" ,orderId ,timeStamp);

        return new ResponseEntity<>(orderService.deleteOrder(orderId), HttpStatus.OK);
    }

    /**
     * update Order
     */
    @PutMapping("order")
    @ApiOperation(value = "UPDATE an existing Order", notes = "Update an existing Order entry " +
            "from the Order database using a provided JSON Order object. " +
            "Returns the updated entry if the operation is a success.", response = ResponseEntity.class)
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
        Order updatedOrder = orderService.updateOrder(order);

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        log.info("Request to UPDATE a order| Order ID: {} | Timestamp: {}",order.getId() ,timeStamp);

        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }
}
