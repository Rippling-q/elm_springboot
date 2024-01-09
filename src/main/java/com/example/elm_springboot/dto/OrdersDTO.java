package com.example.elm_springboot.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrdersDTO {
    private Integer orderState; //订单状态（0：未支付； 1：已支付）

    private Long orderId;

    private Long userId; //？

    private String userName;

    private Long businessId;

    private String businessName;

    private Double deliveryPrice;

    private String orderDate;

    private Double orderTotal;

    private Long deliveryAddressId; //送货地址

    private String contactName;

    private Integer contactSex;

    private String contactTel;

    private String address;

    private List<OrderDetailetDTO> orderDetailetsDTO;
}
