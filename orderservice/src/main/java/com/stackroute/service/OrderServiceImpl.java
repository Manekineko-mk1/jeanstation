package com.stackroute.service;

import com.stackroute.domain.Order;

import com.stackroute.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    /**
     * Constructor based Dependency injection to inject OrderRepository here
     */
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Implementation of saveOrder method
     */
    @Override
    public Order saveOrder(Order order) {
        boolean isOrderExist = orderRepository.findById(order.getId()).isPresent();
        return orderRepository.save(order);
    }

    /**
     * Implementation of getAllOrders method
     */
    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Implementation of getOrderById method
     */
    @Override
    public Order getOrderById(String id) {
        Order order;
        order = orderRepository.findById(id).get();
        return order;
    }

    /**
     * Implementation of deleteOrderById method
     */
    @Override
    public Order deleteOrder(String id) {
        Order order = null;
        Optional<Order> optional = orderRepository.findById(id);
        if (optional.isPresent()) {
            order = orderRepository.findById(id).get();
            orderRepository.deleteById(id);
        }
        return order;
    }

    /**
     * Implementation of updateOrder method
     */
    @Override
    public Order updateOrder(Order order) {
        Order updatedOrder = null;
        Optional<Order> optional = orderRepository.findById(order.getId());
        if (optional.isPresent()) {
            Order getOrder = orderRepository.findById(order.getId()).get();
            getOrder.setPriceTotal(order.getPriceTotal());
            updatedOrder = saveOrder(getOrder);
            System.out.println(updatedOrder);
        }
        return updatedOrder;
    }
}

