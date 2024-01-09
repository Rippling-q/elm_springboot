package com.example.elm_springboot.service;

import com.example.elm_springboot.dto.OrdersDTO;
import com.example.elm_springboot.entity.Orders;

import java.util.List;

public interface IOrdersService {
    Orders createOrders(OrdersDTO ordersDTO);
    Orders getOrdersById(Long orderId);
    List<Orders> listOrdersByUserId(Long userId);
    List<Orders> getNotPayOrder(Long userId,Long businessId);
    Integer deleteOrderById(Long orderId);
}
