package com.example.elm_springboot.controller;

import com.example.elm_springboot.dto.FoodDTO;
import com.example.elm_springboot.entity.Food;
import com.example.elm_springboot.service.IFoodService;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/FoodController")
public class FoodController {
    @Resource
    private IFoodService foodService;

    @Resource
    private ModelMapper modelMapper;

    @GetMapping("/listFoodByBusinessId")
    @Transactional
    public List<FoodDTO> listFoodByBusinessId(Long businessId) {
        List<Food> foods = foodService.listFoodByBusinessId(businessId);
        return foods.stream().map(food -> modelMapper.map(food,FoodDTO.class)).collect(Collectors.toList());
    }

}

