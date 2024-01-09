package com.example.elm_springboot.service.impl;

import com.example.elm_springboot.utils.TokenUtils;
import com.example.elm_springboot.VO.Exception.AppException;
import com.example.elm_springboot.VO.Exception.MessageException;
import com.example.elm_springboot.VO.Exception.PayException;
import com.example.elm_springboot.dao.OrdersDao;
import com.example.elm_springboot.dao.UserDao;
import com.example.elm_springboot.dto.OrdersDTO;
import com.example.elm_springboot.entity.Orders;
import com.example.elm_springboot.entity.User;
import com.example.elm_springboot.service.IUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserService implements IUserService {
    @Resource
    private UserDao uDao;
    @Resource
    private OrdersDao oDao;

    @Override
    public Boolean register(User user) throws MessageException {
        if ((uDao.findUserByTelephone(user.getTelephone())) != null){
            throw new MessageException();
        }
        try {
            uDao.save(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }


    @Override
    public User login(String telephone, String userPwd) throws MessageException {
        User user = uDao.findUserByTelephoneAndPassword(telephone, userPwd);
        if (user == null) throw new MessageException();
        TokenUtils.createToken(user.getUserId().toString(),user.getPassword());
        return user;
    }

    @Override
    public User login(User user) throws MessageException {
        String telephone = user.getTelephone();
        String userPwd = user.getPassword();
        User user1 = uDao.findUserByTelephoneAndPassword(telephone,DigestUtils.md5DigestAsHex(userPwd.getBytes()));
        if (user1 == null) throw new MessageException();
        String token = TokenUtils.createToken(user1.getUserId().toString(),user1.getPassword());
        String refreshToken = TokenUtils.createRefreshToken(user1.getUserId().toString(),user1.getTelephone());
        user1.setToken(token);
        user1.setRefreshToken(refreshToken);
        uDao.save(user1);
        return user1;
    }

    @Override
    public User getUserById(Long userId) {
        return uDao.findUserByUserId(userId);
    }

    @Override
    public User updateInfo(User user) {
        return uDao.save(user);
    }

    @Override
    public Orders payOrder(OrdersDTO ordersDTO) throws PayException {
        Long orderId = ordersDTO.getOrderId();
        Orders orders = oDao.findOrdersByOrderId(orderId);
        if (orders.getOrderState() !=1){
            orders.setOrderState(1);
            return oDao.save(orders);
        }
        throw new PayException();
    }
}
