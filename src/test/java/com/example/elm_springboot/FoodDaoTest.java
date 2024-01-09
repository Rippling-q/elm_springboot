package com.example.elm_springboot;

import com.example.elm_springboot.dao.BusinessDao;
import com.example.elm_springboot.dao.FoodDao;
import com.example.elm_springboot.entity.Business;
import com.example.elm_springboot.entity.Food;
import com.example.elm_springboot.service.impl.FoodService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.function.Consumer;

@SpringBootTest
public class FoodDaoTest {
    @Resource
    private FoodDao fDao;

    @Resource
    private FoodService foodService;

    @Resource
    private BusinessDao bDao;
    @Test
    void addFood(){
        Food food = new Food();
        Business business = bDao.findBusinessByBusinessId(1l);
        food.setBusiness(business);
        food.setFoodPrice(15.0);
        food.setFoodName("纯肉鲜肉（水饺）");
        food.setFoodImg("Img/sp01.png");
        food.setFoodExplain("非常好吃");
        System.out.println(fDao.save(food));
    }

//    @Test
//    void listFood(){
//        List<Food> allByBusiness = fDao.findAllByBusiness(1l);
//        allByBusiness.forEach(new Consumer<Food>() {
//            @Override
//            public void accept(Food food) {
//                System.out.println(food);
//            }
//        });
//    }

    @Test
    void findFood(){
        List<Food> foods= fDao.findAllByBusiness(1l);
        System.out.println(foods);
    }

    @Test
    void listFood(){
        System.out.println(foodService.listFoodByBusinessId(1l));
    }

}
