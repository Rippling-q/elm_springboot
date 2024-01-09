package com.example.elm_springboot.utils;

import com.example.elm_springboot.dto.OrderDetailetDTO;
import com.example.elm_springboot.dto.OrdersDTO;
import com.example.elm_springboot.entity.Business;
import com.example.elm_springboot.entity.DeliveryAddress;
import com.example.elm_springboot.entity.Orders;
import jakarta.annotation.Resource;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    @Resource
    private ModelMapper modelMapper;

    public OrdersDTO mapOrderToDTO(Orders order){
        OrdersDTO ordersDTO = modelMapper.map(order, OrdersDTO.class);
        List<OrderDetailetDTO> collect = order.getOrderDetailets().stream()
                .map(orderDetailet -> modelMapper.map(orderDetailet, OrderDetailetDTO.class)).collect(Collectors.toList());
        ordersDTO.setOrderDetailetsDTO(collect);
        DeliveryAddress deliveryAddress = order.getDeliveryAddress();
        Business business = order.getBusiness();
        ordersDTO.setContactName(deliveryAddress.getContactName());
        ordersDTO.setContactSex(deliveryAddress.getContactSex());
        ordersDTO.setContactTel(deliveryAddress.getContactTel());
        ordersDTO.setAddress(deliveryAddress.getAddress());
        ordersDTO.setDeliveryPrice(business.getDeliveryPrice());
        return ordersDTO;
    }
}
