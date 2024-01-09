package com.example.elm_springboot.service;

import com.example.elm_springboot.VO.Exception.AppException;
import com.example.elm_springboot.VO.Exception.MessageException;
import com.example.elm_springboot.VO.Exception.PayException;
import com.example.elm_springboot.dao.UserDao;
import com.example.elm_springboot.dto.OrdersDTO;
import com.example.elm_springboot.entity.Orders;
import com.example.elm_springboot.entity.User;
import jakarta.annotation.Resource;

public interface IUserService {
    Boolean register(User user) throws MessageException;
    User login(String telephone,String userPwd) throws AppException;
    User login(User user) throws MessageException;
    User getUserById(Long userId);
    User updateInfo(User user);
    Orders payOrder(OrdersDTO ordersDTO) throws PayException;
}
