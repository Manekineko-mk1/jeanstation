package com.stackroute.test.repository;

import com.stackroute.domain.Order;
import com.stackroute.repository.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderRepositoryIntegrationTest {

    @Autowired
    private OrderRepository orderRepository;
    private Order order;
    private ArrayList orderItems;

    @BeforeEach
    public void setUp() {
        orderItems = new ArrayList<String>();
        order = new Order();
        order.setId("1l");
    }

    @AfterEach
    public void tearDown() {
        orderRepository.deleteAll();
        order = null;
    }

    @Test
    public void givenOrderToSaveThenShouldReturnSavedOrder() {
        orderRepository.save(order);
        Order fetchedorder = orderRepository.findById(order.getId()).get();
        assertEquals("1", fetchedorder.getId());
    }

    @Test
    public void givenGetAllOrdersThenShouldReturnListOfAllOrders() {
        Order order = new Order(10, orderItems, "A1");
        Order order1 = new Order(20, orderItems, "A2");
        orderRepository.save(order);
        orderRepository.save(order1);

        List<Order> orderList = orderRepository.findAll();
        assertEquals(20, orderList.get(1).getPriceTotal());
    }

    @Test
    public void givenOrderIdThenShouldReturnRespectiveOrder() {
        Order order = new Order(10, orderItems, "A1");
        Order order1 = orderRepository.save(order);
        Optional<Order> optional = orderRepository.findById(order1.getId());
        assertEquals(order1.getId(), optional.get().getId());
        assertEquals(order1.getPriceTotal(), optional.get().getPriceTotal());
        assertEquals(order1.getOrderItems(), optional.get().getOrderItems());
    }

    @Test
    public void givenOrderIdToDeleteThenShouldReturnDeletedOrder() {
        Order order = new Order(20, orderItems, "A2");
        orderRepository.save(order);
        orderRepository.deleteById(order.getId());
        Optional optional = orderRepository.findById(order.getId());
        assertEquals(Optional.empty(), optional);
    }
    
}
