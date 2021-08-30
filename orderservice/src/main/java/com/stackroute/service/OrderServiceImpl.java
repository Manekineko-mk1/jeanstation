package com.stackroute.service;

import com.stackroute.domain.Order;

import com.stackroute.enums.OrderStatus;
import com.stackroute.exceptions.OrderAlreadyExistException;
import com.stackroute.exceptions.OrderNotFoundException;
import com.stackroute.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/uuuu - HH:mm:ss z");
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderRepository orderRepository;

    /**
     * Implementation of saveOrder method
     */
    @Override
    public Order saveOrder(Order order) {
        boolean isOrderExist;

        if(order.getId() == null){
            isOrderExist = false;
        } else {
            isOrderExist = orderRepository.findById(order.getId()).isPresent();
        }

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        if(isOrderExist) {
            log.error("ERROR: Unable to add order. Order already existed in database | Order ID: {} | Timestamp(EST): {}",
                    order.getId(), timeStamp);

            throw new OrderAlreadyExistException(order.getId());
        } else {
            Order newOrder = new Order(order.getUserId(), order.getPriceTotalBeforeTax(),
                    order.getPriceTotalAfterTax(), order.getOrderItems());
            Order addedCart = orderRepository.save(newOrder);

            log.info("SUCCESS: Add an order to the \"orders\" collection | Order ID: {} | Timestamp(EST): {}",
                    addedCart.getId(), timeStamp);

            return addedCart;
        }
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
        boolean isOrderExist = orderRepository.findById(id).isPresent();

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        if(isOrderExist) {
            log.info("SUCCESS: Cart found by ID | Cart ID: {} | Timestamp(EST): {}", id, timeStamp);

            return orderRepository.findById(id).get();
        } else {
            log.error("ERROR: Unable to find cart. Cart ID not found | Cart ID: {} | Timestamp(EST): {}", id, timeStamp);

            throw new OrderNotFoundException(id);
        }
    }

    /**
     * Implementation of deleteOrderById method
     */
    @Override
    public Order deleteOrder(String id) {
        Order order;
        Optional<Order> optional = orderRepository.findById(id);

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        if (optional.isPresent()) {
            order = orderRepository.findById(id).get();
            orderRepository.deleteById(id);
            log.info("SUCCESS: Deleted order by ID | Order ID: {} | Timestamp(EST): {}", id, timeStamp);

            return order;
        } else {
            log.error("ERROR: Unable to delete order. Order ID not found | Cart ID: {} | Timestamp(EST): {}", id, timeStamp);

            throw new OrderNotFoundException(id);
        }
    }

    /**
     * Implementation of updateOrder method
     */
    @Override
    public Order updateOrder(Order order) {
        Order updatedOrder;
        Optional<Order> optional = orderRepository.findById(order.getId());

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        if (optional.isPresent()) {
            // Locate the existing order with same order ID
            Order getOrder = orderRepository.findById(order.getId()).get();

            // Update the existing order with the new info
            getOrder.setOrderItems(order.getOrderItems());
            getOrder.setPriceTotalBeforeTax(order.getPriceTotalBeforeTax());
            getOrder.setPriceTotalAfterTax(order.getPriceTotalAfterTax());
            getOrder.setDeliveryDate(order.getDeliveryDate());
            getOrder.setStatus(order.getStatus());

            // Update the existing order to the DB
            orderRepository.save(getOrder);

            updatedOrder = orderRepository.findById(order.getId()).get();

            log.info("SUCCESS: Updated order to the \"orders\" collection | Order ID: {} | Timestamp(EST): {}",
                    order.getId(), timeStamp);

            return updatedOrder;
        } else {
            log.error("ERROR: Unable to update order. Order ID not found | Cart ID: {} | Timestamp(EST): {}", order.getId(), timeStamp);

            throw new OrderNotFoundException(order.getId());
        }
    }

    @Override
    public List<Order> getOrderByUserId(String id) {
        return orderRepository.findByUserId(id);
    }
}

