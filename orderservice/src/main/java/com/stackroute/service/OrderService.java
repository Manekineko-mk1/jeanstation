package com.stackroute.service;

import com.stackroute.domain.Order;
import java.util.List;

public interface OrderService {
    /**
     * AbstractMethod to save a order
     */
    Order saveOrder(Order order);

    /**
     * AbstractMethod to get all orders
     */
    List<Order> getAllOrders();

    /**
     * AbstractMethod to get order by id
     */
    Order getOrderById(String id);

    /**
     * AbstractMethod to delete order by id
     */
    Order deleteOrder(String id);

    /**
     * AbstractMethod to update a order
     */
    Order updateOrder(Order order);
}
