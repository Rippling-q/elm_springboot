package com.example.elm_springboot.controller;

import com.example.elm_springboot.dto.CartDTO;
import com.example.elm_springboot.entity.Cart;
import com.example.elm_springboot.service.ICartService;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/CartController")
public class CartController {
    @Resource
    private ModelMapper modelMapper;

    @Resource
    private ICartService cartService;

    @PostMapping("/saveCart")
    public CartDTO saveCart(@RequestBody CartDTO cartDTO){
        Cart cart = modelMapper.map(cartDTO, Cart.class);
        Cart cart1 = cartService.saveCart(cart);
        return modelMapper.map(cart1,CartDTO.class);
    }

    @PostMapping("/updateCart")
    public Integer updateCart(@RequestBody CartDTO cartDTO){
        return cartService.updateCart(cartDTO);
    }

    @DeleteMapping("/removeCart")
    public Integer removeCart(@RequestBody CartDTO cartDTO){
        return cartService.removeCart(cartDTO);
    }

    @GetMapping("/getCart")
    @Transactional
    public List<CartDTO> getCart(@RequestParam Long userId,@RequestParam Long businessId){
        List<Cart> cartsInfo = cartService.getCartInfoByUserIdAndBusinessId(userId, businessId);
        return cartsInfo.stream().map(cart -> modelMapper.map(cart,CartDTO.class)).collect(Collectors.toList());
    }


    @PostMapping("/listCart")
    public List<Cart> listCart(@RequestParam Long userId){
        return cartService.listCart(userId);
    }
}
