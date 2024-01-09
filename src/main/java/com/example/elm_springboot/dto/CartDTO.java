package com.example.elm_springboot.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CartDTO {

    private Long cartId;

    private Long businessId;

    private String businessName;

    private Long userId;

    private String userName;

    private Long foodId;

    private String foodName;

    private Integer foodPrice;

    private String foodImg;

    private Integer quantity;

}
