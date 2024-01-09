package com.example.elm_springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jdk.dynalink.linker.LinkerServices;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Entity
@Getter
@Setter
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long businessId;

    @Column(nullable = false)
    private String businessName;

    @Column
    private String businessAddress;

    @Column
    private String businessExplain; //介绍

    @Column(nullable = false)
    private String businessImg;

    @Column(nullable = false)
/*    点餐分类： 1：美食、2：
    早餐、3：跑腿代购、4：汉堡
    披萨、5：甜品饮品、6：速食
    简餐、7：地方小吃、8：米粉
    面馆、9：包子粥铺、10：炸
            鸡炸串*/
    private Integer orderTypeId;

    @Column
    private double starPrice; //起送费

    @Column
    private double deliveryPrice; //配送费

    @Column
    private String remarks; //备注

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "business")
    @JsonIgnoreProperties(value = {"business"})  //忽略与business关联的foods中的business属性
    private List<Food> foods;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "business")
    @JsonIgnoreProperties(value = {"business"})
    private List<Orders> orders;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "business")
    @JsonIgnoreProperties(value = {"business"})
    private List<Cart> carts;
}