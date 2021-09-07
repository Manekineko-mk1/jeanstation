package com.stackroute.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.controller.OrderController;
import com.stackroute.domain.Order;
import com.stackroute.exceptions.OrderAlreadyExistException;
import com.stackroute.exceptions.OrderNotFoundException;
import com.stackroute.service.OrderService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    private MockMvc mockMvc;
    @Mock
    OrderService orderService;
    @InjectMocks
    private OrderController orderController;
    private Order order;
    private List<Order> orderList;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
        ArrayList itemsList = new ArrayList();
        order = new Order();
        order.setId("1l");
        order.setPriceTotalBeforeTax(420);
        order.setPriceTotalAfterTax(420);
        orderList = new ArrayList<>();
        orderList.add(order);
    }

    @AfterEach
    public void tearDown() {
       order = null;
    }

//    @Test
//    public void givenOrderToSaveThenShouldReturnSavedOrder() throws Exception {
//        when(orderService.saveOrder(any())).thenReturn(order);
//        mockMvc.perform(post("/api/v1/order")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(order)))
//                .andExpect(status().isCreated())
//                .andDo(MockMvcResultHandlers.print());
//        verify(orderService).saveOrder(any());
//    }
//
//    @Test
//    public void givenGetAllOrdersThenShouldReturnListOfAllOrders() throws Exception {
//        when(orderService.getAllOrders()).thenReturn(orderList);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orders")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(order)))
//                .andDo(MockMvcResultHandlers.print());
//        verify(orderService).getAllOrders();
//        verify(orderService, times(1)).getAllOrders();
//
//    }
//
//    @Test
//    void givenOrderIdThenShouldReturnRespectiveOrder() throws Exception {
//        when(orderService.getOrderById(order.getId())).thenReturn(order);
//        mockMvc.perform(get("/api/v1/order/1"))
//                .andExpect(MockMvcResultMatchers.status()
//                        .isFound())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    public void givenOrderIdToDeleteThenShouldNotReturnDeletedOrder() throws Exception {
//        when(orderService.deleteOrder(order.getId())).thenReturn(order);
//        mockMvc.perform(delete("/api/v1/order/1"))
//                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    public void givenOrderToUpdateThenShouldReturnUpdatedOrder() throws Exception {
//        when(orderService.updateOrder(any())).thenReturn(order);
//        mockMvc.perform(put("/api/v1/order").contentType(MediaType.APPLICATION_JSON).content(asJsonString(order)))
//                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
//    }

    /**
     * Test POST with OrderAlreadyExistException
     * @throws OrderAlreadyExistException
     */
    @Test
    public void givenOrderAlreadyExistThenTryToSaveThenShouldThrowException() throws OrderAlreadyExistException {
//        when(orderService.saveOrder(any())).thenReturn(order);
//        mockMvc.perform(post("/api/v1/order")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(order)))
//                .andExpect(status().isCreated())
//                .andDo(MockMvcResultHandlers.print());
//        verify(orderService).saveOrder(any());
    }

    /**
     * Test GET Order by ID with OrderNotFoundException
     * @throws OrderNotFoundException
     */
    @Test
    public void givenNoOrderExistThenGetOrderByIDShouldThrowException() throws OrderNotFoundException {

    }

    /**
     * Test DELETE Order by ID with OrderNotFoundException
     * @throws OrderNotFoundException
     */
    @Test
    public void givenNoOrderExistThenDeleteOrderByIDShouldThrowException() throws OrderNotFoundException {

    }

    /**
     * Test UPDATE Order with OrderNotFoundException
     * @throws OrderNotFoundException
     */
    @Test
    public void givenNoOrderExistThenUpdateOrderShouldThrowException() throws OrderNotFoundException {

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}

