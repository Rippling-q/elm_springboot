package com.example.elm_springboot;

import com.example.elm_springboot.VO.Exception.AppException;
import com.example.elm_springboot.dao.UserDao;
import com.example.elm_springboot.entity.User;
import com.example.elm_springboot.service.impl.UserService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserDaoTest {
    @Resource
    private UserDao uDao;
    @Resource
    private UserService userService;
    @Test
    void register(){
        User user = new User();
        user.setUserName("小翎");
        user.setPassword("xiaoling123");
        user.setUserSex(0);
        user.setTelephone("15576753939");
        System.out.println(uDao.save(user));
    }
    @Test
    void register2(){
        User user = new User();
        user.setUserName("小蓝");
        user.setPassword("xiaolan123");
        user.setUserSex(0);
        user.setTelephone("15176753939");
        System.out.println(uDao.save(user));
    }
    @Test
    void login() throws AppException {
        System.out.println(userService.login("15376753935", "xiaoling123"));
    }

    @Test
    void getById(){
        System.out.println(userService.getUserById(1l));
    }

    @Test
    void getByid(){
        System.out.println(uDao.findUserById(1l));
    }

    @Test
    void updateTest(){
        User user = uDao.findUserById(2l);
        user.setTelephone("15576753939");
        System.out.println(userService.updateInfo(user));
    }
}
