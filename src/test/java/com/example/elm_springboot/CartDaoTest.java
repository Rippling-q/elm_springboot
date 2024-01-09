package com.example.elm_springboot;

import com.example.elm_springboot.dao.CartDao;
import com.example.elm_springboot.service.impl.CartService;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CartDaoTest {
    @Resource
    private CartService cartService;
    @Resource
    private CartDao cartDao;

    @Test
    @Transactional
    void deleteTest(){
        cartDao.deleteCart(2l,1l,14l);
        cartDao.flush();
//        System.out.println(cartDao.find(2l, 1l, 14l).getBusiness().getBusinessName());
    }
}
