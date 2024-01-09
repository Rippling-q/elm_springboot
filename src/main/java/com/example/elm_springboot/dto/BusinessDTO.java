package com.example.elm_springboot.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BusinessDTO {
    private Long businessId;

    private String businessName;

    private String businessAddress;

    private String businessExplain; //介绍

    private String businessImg;

    private Integer orderTypeId;

    private double starPrice; //起送费

    private double deliveryPrice; //配送费

    private String remarks; //备注

}
