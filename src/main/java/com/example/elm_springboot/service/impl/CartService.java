package com.example.elm_springboot.service.impl;

import com.example.elm_springboot.dao.CartDao;
import com.example.elm_springboot.dto.CartDTO;
import com.example.elm_springboot.entity.Cart;
import com.example.elm_springboot.service.ICartService;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService implements ICartService {
    @Resource
    private CartDao cDao;

    @Override
    public List<Cart> listCart(Long userId) {
        try {
            return cDao.findAllByUser(userId);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public List<Cart> getCartInfoByUserIdAndBusinessId(Long userId, Long businessId) {
       return cDao.findCarstByUserIdAndBusinessId(userId,businessId);
    }

    @Override
    public Cart saveCart(Cart cart) {
        try {
            return cDao.save(cart);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    @Transactional
    public Integer updateCart(CartDTO cartDTO) {
        Long userId = cartDTO.getUserId();
        Long businessId = cartDTO.getBusinessId();
        Long foodId = cartDTO.getFoodId();
        Integer quantity = cartDTO.getQuantity();
//        System.out.println(userId+" "+businessId+" "+foodId+" "+quantity);
        try {
            Integer i = cDao.updateCarts(quantity, userId, businessId, foodId);
            cDao.flush();
            return i;
        }catch (Exception e){
            throw e;
        }
    }

    @Transactional
    @Override
    public Integer removeCart(CartDTO cartDTO) {
        Long userId = cartDTO.getUserId();
        Long businessId = cartDTO.getBusinessId();
        Long foodId = cartDTO.getFoodId();
        Integer i = cDao.deleteCart(userId, businessId, foodId);
        cDao.flush();
        return i;
    }
}
