package com.example.elm_springboot.service.impl;

import com.example.elm_springboot.dao.*;
import com.example.elm_springboot.dto.OrdersDTO;
import com.example.elm_springboot.entity.*;
import com.example.elm_springboot.service.IOrdersService;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class OrdersService implements IOrdersService {
    @Resource
    private OrdersDao oDao;
    @Resource
    private BusinessDao bDao;
    @Resource
    private DeliveryAddressDao deDao;
    @Resource
    private UserDao uDao;
    @Resource
    private CartDao cDao;


    @Override
    @Transactional
    public Orders createOrders(OrdersDTO ordersDTO) {
        User user = uDao.findUserById(ordersDTO.getUserId());
        Business business = bDao.findBusinessByBusinessId(ordersDTO.getBusinessId());
        List<Cart> carts = cDao.findCartsByUserAndBusiness(user, business);
        if (!carts.isEmpty()){
            Orders orders = new Orders();
            orders.setUser(user);
            orders.setBusiness(business);
            orders.setDeliveryAddress(deDao.findDeliveryAddressByDaId(ordersDTO.getDeliveryAddressId()));
            orders.setOrderDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).toString());
            orders.setOrderTotal(ordersDTO.getOrderTotal());

            List<OrderDetailet> orderDetailets = orders.getOrderDetailets();
//            orders.setOrderDetailets(orderDetailets);
            carts.forEach(cart -> {
                OrderDetailet orderDetailet = new OrderDetailet();
                orderDetailet.setFood(cart.getFood());
                orderDetailet.setQuantity(cart.getQuantity());
                orderDetailet.setOrders(orders);
                orderDetailets.add(orderDetailet);
            });
            cDao.removeAllByUserAndBusiness(user,business);
            return oDao.save(orders);
        }
        return null;
    }

    @Override
    public Orders getOrdersById(Long orderId) {
        return oDao.findOrdersByOrderId(orderId);
    }

    @Override
    public List<Orders> listOrdersByUserId(Long userId) {
        return oDao.findAllByUserId(userId);
    }

    @Override
    public List<Orders> getNotPayOrder(Long userId, Long businessId) {
        return oDao.getNotPayOrder(userId, businessId);
    }

    @Override
    public Integer deleteOrderById(Long orderId) {
        return oDao.removeOrdersByOrderId(orderId);
    }


}
