package com.example.elm_springboot.dao;

import com.example.elm_springboot.dto.CartDTO;
import com.example.elm_springboot.entity.Business;
import com.example.elm_springboot.entity.Cart;
import com.example.elm_springboot.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.sql.In;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDao extends JpaRepository<Cart,Long> {
    Cart findCartByCartId(Long cartId);
    @Query("select c from Cart c where c.user.userId = :userId and c.business.businessId = :businessId")
    List<Cart> findCarstByUserIdAndBusinessId(@Param(value = "userId") Long userId,@Param(value = "businessId") Long businessId);

    List<Cart> findCartsByUserAndBusiness(User user,Business business);

//    自定义更新删除功能要开启事务
    @Modifying
    @Query("UPDATE Cart c SET c.quantity = :newQuantity WHERE c.user.userId = :userId AND c.business.businessId = :businessId AND c.food.foodId = :foodId")
    Integer updateCarts(@Param("newQuantity")Integer newQuantity,@Param("userId")Long userId,@Param("businessId")Long businessId,@Param("foodId")Long foodId);

    @Query("select c from Cart c where c.user.userId = :userId")
    List<Cart> findAllByUser(@Param(value = "userId") Long userId);

    @Transactional
    Integer removeByCartId(Long cartId);

    @Transactional
    Integer removeAllByUserAndBusiness(User user,Business business);

    @Query("SELECT c FROM Cart c where c.user.userId = :userId and c.business.businessId = :businessId and c.food.foodId = :foodId")
    Cart find(@Param("userId")Long userId,@Param("businessId")Long businessId,@Param("foodId")Long foodId);

    @Modifying
    @Transactional
    @Query("delete from Cart c where c.user.userId = :userId and c.business.businessId = :businessId and c.food.foodId = :foodId")
    Integer deleteCart(@Param("userId") Long userId, @Param("businessId") Long businessId, @Param("foodId") Long foodId);
}
