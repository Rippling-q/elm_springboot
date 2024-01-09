package com.example.elm_springboot.dao;

import com.example.elm_springboot.entity.Business;
import com.example.elm_springboot.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FoodDao extends JpaRepository<Food,Long> {
    Food findFoodByFoodId(Long foodId);
    @Query("select f from Food f where f.business.businessId = :businessId")
    List<Food> findAllByBusiness(@Param(value = "businessId") Long businessId);

}
