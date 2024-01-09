package com.example.elm_springboot;

import com.example.elm_springboot.dao.BusinessDao;
import com.example.elm_springboot.entity.Business;
import com.example.elm_springboot.entity.Food;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.function.Consumer;

@SpringBootTest
public class BusinessDaoTest {
    @Resource
    private BusinessDao businessDao;

    @Test
    void listFood(){
        Business business = businessDao.findBusinessByBusinessId(1l);
        List<Food> foods = business.getFoods();
        System.out.println(foods);
    }
    @Test
    void listBusinessId(){
        List<Business> businesses = businessDao.searchBusinesses("蒸饺");
        System.out.println(businesses.size());
        businesses.forEach(business -> System.out.println(business.getBusinessName()));
    }
}
