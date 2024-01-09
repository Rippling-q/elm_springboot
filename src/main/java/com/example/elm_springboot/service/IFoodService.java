package com.example.elm_springboot.service;


import com.example.elm_springboot.entity.Business;
import com.example.elm_springboot.entity.Food;

import java.util.List;

public interface IFoodService {
    List<Food> listFoodByBusinessId(Long businessId);
}
