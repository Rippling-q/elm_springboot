package com.example.elm_springboot.service.impl;

import com.example.elm_springboot.dao.BusinessDao;
import com.example.elm_springboot.dao.FoodDao;
import com.example.elm_springboot.entity.Business;
import com.example.elm_springboot.entity.Food;
import com.example.elm_springboot.service.IFoodService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService implements IFoodService {
    @Resource
    private FoodDao fDao;
    @Resource
    private BusinessDao bDao;
    @Override
    public List<Food> listFoodByBusinessId(Long businessId) {
        return fDao.findAllByBusiness(businessId);
    }
}
