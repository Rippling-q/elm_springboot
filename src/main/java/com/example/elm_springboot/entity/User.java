package com.example.elm_springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.springframework.util.DigestUtils;

import java.util.List;

@Entity
@Getter
@Setter
@Where(clause = "del_tag = 1")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String telephone;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private Integer userSex;

    @Column
    private String userImg; //头像

    @Column
    private String token;

    @Column
    private String refreshToken;

    @Column(nullable = false,columnDefinition = "INT default 1")
    private Integer delTag; //删除标记（1：正常； 0：删除）

    @OneToMany(fetch = FetchType.LAZY,
               mappedBy = "user")
    @JsonIgnoreProperties(value = {"user"})
    private List<DeliveryAddress> deliveryAddresses;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,
               mappedBy = "user")
    @JsonIgnoreProperties(value = {"user"})
    private List<Orders> orders;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    @JsonIgnoreProperties(value = {"user"})
    private List<Cart> carts;

    public User(){
        this.delTag = 1;
    }

    @PrePersist
    public void setUserPassword(){
        this.password = DigestUtils.md5DigestAsHex(password.getBytes());
    }
}