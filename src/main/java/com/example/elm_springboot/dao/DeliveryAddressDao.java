package com.example.elm_springboot.dao;

import com.example.elm_springboot.entity.DeliveryAddress;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryAddressDao extends JpaRepository<DeliveryAddress,Long> {

    @Query("select deliveryAddress from DeliveryAddress deliveryAddress where deliveryAddress.user.userId = :userId")
    List<DeliveryAddress> findAllByUserId(@Param(value = "userId")Long userId);

    DeliveryAddress findDeliveryAddressByDaId(Long daId);

    @Transactional
    Integer removeDeliveryAddressByDaId(Long daId);
}
