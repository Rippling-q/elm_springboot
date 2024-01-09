package com.example.elm_springboot.controller;

import com.example.elm_springboot.utils.OrderMapper;
import com.example.elm_springboot.dto.OrdersDTO;
import com.example.elm_springboot.entity.Orders;
import com.example.elm_springboot.service.IOrdersService;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/OrdersController")
public class OrdersController {
    @Resource
    private IOrdersService ordersService;
    @Resource
    private OrderMapper orderMapper;

    @PostMapping("/createOrders")
    @Transactional
    public OrdersDTO createOrders(@RequestBody OrdersDTO ordersDTO){
        Orders order = ordersService.createOrders(ordersDTO);
        return orderMapper.mapOrderToDTO(order);
    }

    @GetMapping("/getOrdersById")
    @Transactional
    public OrdersDTO getOrdersById(@RequestParam Long ordersId){
        Orders order = ordersService.getOrdersById(ordersId);
        return orderMapper.mapOrderToDTO(order);
    }

    @GetMapping("/getOrdersByUserIdAndBusinessId")
    @Transactional
    public List<OrdersDTO> getOrdersByUserIdAndBusinessId(@RequestParam Long userId,@RequestParam Long businessId){
        List<Orders> orders = ordersService.getNotPayOrder(userId, businessId);
        if (!orders.isEmpty()) return orders.stream().map(order -> orderMapper.mapOrderToDTO(order)).collect(Collectors.toList());
        return null;
    }

    @GetMapping("/listOrdersByUserId")
    @Transactional
    public List<OrdersDTO> listOrdersByUserId(@RequestParam Long userId){
        List<Orders> orders = ordersService.listOrdersByUserId(userId);
        return orders.stream().map(order -> orderMapper.mapOrderToDTO(order)).collect(Collectors.toList());
    }

    @DeleteMapping("/deleteOrderById")
    public Integer deleteOrderById(@RequestParam Long orderId){
        return ordersService.deleteOrderById(orderId);
    }
}
