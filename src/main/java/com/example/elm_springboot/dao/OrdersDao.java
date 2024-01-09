package com.example.elm_springboot.dao;

import com.example.elm_springboot.entity.Orders;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersDao extends JpaRepository<Orders,Long> {

    Orders findOrdersByOrderId(Long ordersId);

    @Query("select o from Orders o where o.user.userId = :userId")
    List<Orders> findAllByUserId(@Param(value = "userId") Long userId);

    @Query("select o from Orders o where o.user.userId = :userId and o.business.businessId = :businessId and o.orderState = 0")
    List<Orders> getNotPayOrder(@Param(value = "userId") Long userId,@Param(value = "businessId") Long businessId);

    @Transactional
    Integer removeOrdersByOrderId(Long orderId);
}
