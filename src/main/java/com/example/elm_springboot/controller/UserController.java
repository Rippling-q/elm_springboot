package com.example.elm_springboot.controller;

import com.example.elm_springboot.VO.Exception.MessageException;
import com.example.elm_springboot.config.jwt.AuthAccess;
import com.example.elm_springboot.VO.Exception.PayException;
import com.example.elm_springboot.dto.OrderDetailetDTO;
import com.example.elm_springboot.dto.OrdersDTO;
import com.example.elm_springboot.dto.UserDTO;
import com.example.elm_springboot.entity.Orders;
import com.example.elm_springboot.entity.User;
import com.example.elm_springboot.service.IUserService;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/UserController")
@CrossOrigin(value = "*")
public class UserController {
    @Resource
    private ModelMapper modelMapper;
    @Resource
    private IUserService userService;

    @AuthAccess
    @PostMapping("/login")
    public UserDTO getUserByIdByPass(@RequestBody User user) throws MessageException {
        User user1 = userService.login(user);
        return modelMapper.map(user1,UserDTO.class);
    }

    @AuthAccess
    @PostMapping("/register")
    public boolean saveUser(@RequestBody User user) throws MessageException {
        return userService.register(user);
    }

    @GetMapping("/getUserById")
    public UserDTO getUserById(@RequestParam Long userId){
        User user = userService.getUserById(userId);
        return modelMapper.map(user,UserDTO.class);
    }

    @PostMapping("/payOrder")
    @Transactional
    public OrdersDTO payOrder(@RequestBody OrdersDTO ordersDTO) throws PayException {
        Orders orders = userService.payOrder(ordersDTO);
        OrdersDTO ordersDTO1 = modelMapper.map(orders, OrdersDTO.class);
        List<OrderDetailetDTO> collect = orders.getOrderDetailets().stream().
                map(orderDetailet -> modelMapper.map(orderDetailet, OrderDetailetDTO.class)).collect(Collectors.toList());
        ordersDTO1.setOrderDetailetsDTO(collect);
        return ordersDTO1;
    }

    @PutMapping("/updateInfo")
    public UserDTO updateInfo(@RequestBody User user){
        User user1 = userService.updateInfo(user);
        return modelMapper.map(user1,UserDTO.class);
    }
}
