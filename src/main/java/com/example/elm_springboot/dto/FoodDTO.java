package com.example.elm_springboot.dto;

import com.example.elm_springboot.entity.Business;
import com.example.elm_springboot.entity.Cart;
import com.example.elm_springboot.entity.OrderDetailet;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodDTO {
    private Long foodId;

    private String foodName;

    private String foodExplain; //介绍

    private String foodImg;

    private Double foodPrice;

    private Long businessId;

    private String businessName;

    private String remarks;
}
