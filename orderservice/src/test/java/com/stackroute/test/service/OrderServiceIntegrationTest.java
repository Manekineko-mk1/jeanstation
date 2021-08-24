package com.stackroute.test.service;

import com.stackroute.domain.Order;
import com.stackroute.repository.OrderRepository;
import com.stackroute.service.OrderServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
        ArrayList<String> itemsList = new ArrayList<>();
        itemsList.add("cat1");
        itemsList.add("cat2");
        order1 = new Order(10, itemsList, "A1");
        order2 = new Order(20, itemsList, "A2");
        order3 = new Order(30, itemsList, "A1");
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