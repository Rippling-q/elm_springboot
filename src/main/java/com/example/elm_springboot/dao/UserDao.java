package com.example.elm_springboot.dao;

import com.example.elm_springboot.entity.Cart;
import com.example.elm_springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Long> {
    User findAllByUserIdAndPassword(Long id,String UserPwd);
    User findUserByTelephoneAndPassword(String telephone,String userPwd);
    User findUserByUserId(Long userId);
    @Query("select u from User u where u.userId = :userId")
    User findUserById(@Param(value = "userId") Long Id);

    User findUserByTelephone(String telephone);
}
