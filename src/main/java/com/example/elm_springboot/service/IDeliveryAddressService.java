package com.example.elm_springboot.service;

import com.example.elm_springboot.entity.DeliveryAddress;

import java.util.List;

public interface IDeliveryAddressService {
    List<DeliveryAddress> listDeliveryAddressByUserId(Long userId);
    DeliveryAddress getDeliveryAddressById(Long daId);

    DeliveryAddress saveDeliveryAddress(DeliveryAddress deliveryAddress);

    DeliveryAddress updateDeliveryAddress(DeliveryAddress deliveryAddress);

    Integer removeDeliveryAddress(Long daId);
}
