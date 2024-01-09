package com.example.elm_springboot.dao;

import com.example.elm_springboot.entity.OrderDetailet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailetDao extends JpaRepository<OrderDetailet,Long> {
}
