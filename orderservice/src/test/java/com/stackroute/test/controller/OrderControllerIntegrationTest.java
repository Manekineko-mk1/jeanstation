package com.stackroute.test.controller;

import com.stackroute.domain.Money;
import com.stackroute.domain.Order;
import com.stackroute.domain.Product;
import com.stackroute.enums.Currency;
import com.stackroute.exceptions.OrderAlreadyExistException;
import com.stackroute.exceptions.OrderNotFoundException;
import com.stackroute.service.OrderService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderControllerIntegrationTest {
    @Autowired
    private OrderService orderService;
    private Order order;
    private List<Order> orderList;

    @BeforeEach
    void setUp() {
        order = new Order();
        order.setId("orderId123");
        order.setUserId("userId123");
        order.setPriceTotalBeforeTax(1000);
        order.setPriceTotalAfterTax(2000);
        ArrayList<Product> orderItems = new ArrayList<>();
        orderItems.add(new Product());
        orderItems.add(new Product());
        order.setOrderItems(orderItems);
    }

    @AfterEach
    void tearDown() {
        order = null;
    }

    @Test
    void givenProductToSaveThenShouldReturnTheSavedProduct() throws OrderAlreadyExistException {
        Order savedOrder = orderService.saveOrder(order);
        assertNotNull(savedOrder);
        assertEquals(order.getId(), savedOrder.getId());
    }

    @Test
    void givenProductToSaveThenThrowException() throws OrderAlreadyExistException {
        assertNotNull(orderService.saveOrder(order));
        assertThrows(OrderAlreadyExistException.class, () -> orderService.saveOrder(order));
    }

    @Test
    void givenProductToDeleteThenShouldReturnTheDeletedProduct() throws OrderNotFoundException {
        assertNotNull(orderService.saveOrder(order));
        Order deletedOrder = orderService.deleteOrder(order.getId());
        assertNotNull(deletedOrder);
    }

    @Test
    void givenProductToDeleteThenThrowException() throws OrderNotFoundException {
        assertThrows(OrderNotFoundException.class, () -> orderService.deleteOrder("NonExistProduct"));
    }

    @Test
    void givenCallToGetAllProductsThenListShouldNotBeNull() throws Exception {
        List<Order> retrievedOrders = orderService.getAllOrders();
        assertNotNull(retrievedOrders);
    }

    @Test
    void givenProductToUpdateThenShouldReturnUpdatedProduct() throws OrderNotFoundException {
        Order savedOrder = orderService.saveOrder(order);
        savedOrder.setPriceTotalBeforeTax(1234);
        savedOrder.setPriceTotalAfterTax(5678);
        Order updatedOrder = orderService.updateOrder(savedOrder);
        assertNotNull(updatedOrder);
        assertEquals(1234, updatedOrder.getPriceTotalBeforeTax());
        assertEquals(5678, updatedOrder.getPriceTotalAfterTax());
    }

    @Test
    void givenProductToUpdateThenThrowException() throws OrderNotFoundException {
        order.setId("NonExistProductId");
        assertThrows(OrderNotFoundException.class, () -> orderService.updateOrder(order));
    }

    @Test
    void givenProductIdThenShouldReturnRespectiveProduct() throws OrderNotFoundException {
        assertNotNull(orderService.saveOrder(order));
        Order retrievedOrder = orderService.getOrderById(order.getId());
        assertNotNull(retrievedOrder);
    }

    @Test
    void givenProductIdThenShouldThrowException() throws OrderNotFoundException {
        order.setId("NonExistProductId");
        assertThrows(OrderNotFoundException.class, () -> orderService.getOrderById(order.getId()));
    }
}

