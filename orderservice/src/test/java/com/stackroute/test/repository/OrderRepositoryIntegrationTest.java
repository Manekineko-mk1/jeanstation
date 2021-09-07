package com.stackroute.test.repository;

import com.stackroute.domain.Order;
import com.stackroute.domain.Product;
import com.stackroute.repository.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderRepositoryIntegrationTest {

    @Autowired
    private OrderRepository orderRepository;
    private Order order;
    private List orderItems;

    @BeforeEach
    public void setUp() {
        orderItems = new ArrayList<Product>();
        order = new Order();
        order.setId("1l");
    }

    @AfterEach
    public void tearDown() {
        orderRepository.deleteAll();
        order = null;
    }

//    @Test
//    public void givenOrderToSaveThenShouldReturnSavedOrder() {
//        orderRepository.save(order);
//        Order fetchedorder = orderRepository.findById(order.getId()).get();
//        assertEquals("1", fetchedorder.getId());
//    }
//
//    @Test
//    public void givenGetAllOrdersThenShouldReturnListOfAllOrders() {
//        Product product1 = new Product();
//        String userId = "userId";
//        int priceTotalBeforeTax = 1000;
//        int priceTotalAfterTax = 1100;
//        Order order = new Order(userId, priceTotalBeforeTax, priceTotalAfterTax, orderItems);
//        Order order1 = new Order(userId, priceTotalBeforeTax, priceTotalAfterTax, orderItems);
//        orderRepository.save(order);
//        orderRepository.save(order1);
//
//        List<Order> orderList = orderRepository.findAll();
//        assertEquals(1000, orderList.get(1).getPriceTotalBeforeTax());
//        assertEquals(1100, orderList.get(1).getPriceTotalAfterTax());
//    }
//
//    @Test
//    public void givenOrderIdThenShouldReturnRespectiveOrder() {
//        Product product1 = new Product();
//        String userId = "userId";
//        int priceTotal = 1000;
//        int priceTotalBeforeTax = 1000;
//        int priceTotalAfterTax = 1100;
//        Order order = new Order(userId, priceTotalBeforeTax, priceTotalAfterTax, orderItems);
//        Order order1 = orderRepository.save(order);
//        Optional<Order> optional = orderRepository.findById(order1.getId());
//        assertEquals(order1.getId(), optional.get().getId());
//        assertEquals(order1.getPriceTotalBeforeTax(), optional.get().getPriceTotalBeforeTax());
//        assertEquals(order1.getPriceTotalAfterTax(), optional.get().getPriceTotalAfterTax());
//        assertEquals(order1.getOrderItems(), optional.get().getOrderItems());
//    }

    @Test
    public void givenOrderIdToDeleteThenShouldReturnDeletedOrder() {
        Product product1 = new Product();
        String userId = "userId";
        int priceTotalBeforeTax = 1000;
        int priceTotalAfterTax = 1100;
        Order order = new Order(userId, priceTotalBeforeTax, priceTotalAfterTax, orderItems);
        orderRepository.save(order);
        orderRepository.deleteById(order.getId());
        Optional optional = orderRepository.findById(order.getId());
        assertEquals(Optional.empty(), optional);
    }
    
}
