package com.stackroute.test.service;

import com.stackroute.domain.Order;
import com.stackroute.domain.Product;
import com.stackroute.repository.OrderRepository;
import com.stackroute.service.OrderServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class OrderServiceIntegrationTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderServiceImpl orderService;
    private Order order1, order2, order3;
    private List<Order> orderList;
    private Optional optional;

    @BeforeEach
    public void setUp() {

        orderList = new ArrayList<>();
        ArrayList<Product> itemsList = new ArrayList<Product>();
        Product product1 = new Product();
        String userId = "userId";
        int priceTotalBeforeTax = 1000;
        int priceTotalAfterTax = 1100;
        order1 = new Order(userId, priceTotalBeforeTax, priceTotalAfterTax, itemsList);
        order2 = new Order(userId, priceTotalBeforeTax, priceTotalAfterTax, itemsList);
        order3 = new Order(userId, priceTotalBeforeTax, priceTotalAfterTax, itemsList);
        orderList.add(order1);
        orderList.add(order2);
        orderList.add(order3);
    }

    @AfterEach
    public void tearDown() {
        order1 = order2 = order3 = null;
        orderList = null;
    }

    @Test
    public void givenOrderToSaveThenShouldReturnSavedOrder() {
        Order savedOrder = orderRepository.save(order1);
        assertNotNull(savedOrder);
        assertEquals(order1.getId(), savedOrder.getId());
    }

    @Test
    public void givenGetAllOrdersThenShouldReturnListOfAllOrders() {
        List<Order> orderList = (List<Order>) orderRepository.findAll();
        assertNotNull(orderList);
    }
}