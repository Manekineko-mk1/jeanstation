package com.stackroute.test.service;

import com.stackroute.domain.Order;

import com.stackroute.domain.Product;
import com.stackroute.repository.OrderRepository;
import com.stackroute.service.OrderServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;
    private Order order, order1;
    private List<Order> orderList;
    private Optional optional;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        List<Product> itemsList = new ArrayList<Product>();
        Product product1 = new Product();
        itemsList.add(product1);

        String userId = "userId";
        int priceTotal = 1000;
        LocalDate creationDate = LocalDate.now();
        LocalDate deliveryDate = creationDate.plusDays(3l);
        order = new Order(userId, priceTotal, itemsList, creationDate, deliveryDate);
        order1 = new Order(userId, priceTotal, itemsList, creationDate, deliveryDate);
        optional = Optional.of(order);
    }

    @AfterEach
    public void tearDown() {
        order = null;
    }

    @Test
    public void givenOrderToSaveThenShouldReturnSavedOrder() {
        when(orderRepository.save(any())).thenReturn(order);
        assertEquals(order, orderService.saveOrder(order));
        verify(orderRepository, times(1)).save(any());
    }

    @Test
    public void givenOrderToSaveThenShouldNotReturnSavedOrder() {
        when(orderRepository.save(any())).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () -> {
            orderService.saveOrder(order);
        });
        verify(orderRepository, times(1)).save(any());
    }

    @Test
    public void getAllOrders() {
        orderRepository.save(order);
        //stubbing the mock to return specific data
        when(orderRepository.findAll()).thenReturn(orderList);
        List<Order> orderList1 = orderService.getAllOrders();
        assertEquals(orderList, orderList1);
        verify(orderRepository, times(1)).save(order);
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    public void givenOrderIdThenShouldReturnRespectiveOrder() {
        when(orderRepository.findById(any())).thenReturn(Optional.of(order));
        Order retrievedOrder = orderService.getOrderById(order.getId());
        verify(orderRepository, times(1)).findById(any());

    }

    @Test
    void givenOrderIdToDeleteThenShouldReturnDeletedOrder() {
        when(orderRepository.findById(order.getId())).thenReturn(optional);
        Order deletedOrder = orderService.deleteOrder("1");
        assertEquals(1, deletedOrder.getId());

        verify(orderRepository, times(2)).findById(order.getId());
        verify(orderRepository, times(1)).deleteById(order.getId());
    }

    @Test
    void givenOrderIdToDeleteThenShouldNotReturnDeletedOrder() {
        when(orderRepository.findById(order.getId())).thenReturn(Optional.empty());
        Order deletedOrder = orderService.deleteOrder("1");
        verify(orderRepository, times(1)).findById(order.getId());
    }

    @Test
    public void givenOrderToUpdateThenShouldReturnUpdatedOrder() {
        when(orderRepository.findById(order.getId())).thenReturn(optional);
        when(orderRepository.save(order)).thenReturn(order);
        List<Product> itemsList = new ArrayList<Product>();
        Product product1 = new Product();
        itemsList.add(product1);
        order.setOrderItems(itemsList);
        Order order1 = orderService.updateOrder(order);
        assertEquals(order1.getOrderItems(), itemsList);
        verify(orderRepository, times(1)).save(order);
        verify(orderRepository, times(2)).findById(order.getId());
    }
}