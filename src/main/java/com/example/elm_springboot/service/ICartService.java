package com.example.elm_springboot.service;

import com.example.elm_springboot.dto.CartDTO;
import com.example.elm_springboot.entity.Cart;

import java.util.List;

public interface ICartService {
    List<Cart> listCart(Long userId);
    List<Cart> getCartInfoByUserIdAndBusinessId(Long userId,Long businessId);
    Cart saveCart(Cart cart);
    Integer updateCart(CartDTO cartDTO);
    Integer removeCart(CartDTO cartDTO);
}
