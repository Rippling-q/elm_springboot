package com.example.elm_springboot.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailetDTO {
    private Long odId;

    private Integer quantity;

    private String foodName;

    private Double foodPrice;

//    计算属性
    public Double getLineItemPrice(){
        return foodPrice*quantity;
    }

}
