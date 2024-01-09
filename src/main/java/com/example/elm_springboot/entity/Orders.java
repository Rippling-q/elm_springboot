package com.example.elm_springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne
    //多对一：所属商家
    @JsonIgnoreProperties(value = {"orders"})
    private Business business;

    @Column(nullable = false)
    private String orderDate;

    @Column(nullable = false)
    private Double orderTotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"orders"})
    private DeliveryAddress deliveryAddress; //送货地址

    @Column(nullable = false)
    private Integer orderState; //订单状态（0：未支付； 1：已支付）

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "orders",cascade = CascadeType.ALL)  //级联操作订单详情
    @JsonIgnoreProperties(value = {"orders"})
    private List<OrderDetailet> orderDetailets;

    public Orders(){
        this.setOrderState(0);
        this.setOrderDetailets(new ArrayList<>());
    }
}