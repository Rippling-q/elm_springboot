package com.example.elm_springboot.service.impl;

import com.example.elm_springboot.dao.DeliveryAddressDao;
import com.example.elm_springboot.entity.DeliveryAddress;
import com.example.elm_springboot.service.IDeliveryAddressService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryAddressService implements IDeliveryAddressService {
    @Resource
    private DeliveryAddressDao dDao;

    @Override
    public List<DeliveryAddress> listDeliveryAddressByUserId(Long userId) {
        return dDao.findAllByUserId(userId);
    }

    @Override
    public DeliveryAddress getDeliveryAddressById(Long daId) {
        return dDao.findDeliveryAddressByDaId(daId);
    }

    @Override
    public DeliveryAddress saveDeliveryAddress(DeliveryAddress deliveryAddress) {
        return dDao.save(deliveryAddress);
    }

    @Override
    public DeliveryAddress updateDeliveryAddress(DeliveryAddress deliveryAddress) {
        return dDao.save(deliveryAddress);
    }

    @Override
    public Integer removeDeliveryAddress(Long daId) {
        return dDao.removeDeliveryAddressByDaId(daId);
    }
}
