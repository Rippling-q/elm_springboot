package com.example.elm_springboot.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DeliveryAddressDTO {
    private Long daId;

    private String contactName;

    private Integer contactSex;

    private String contactTel;

    private String address;

    private Long userId;
}
