package com.example.elm_springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

@Entity
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"carts"})
    private Food food;

    @Column
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"carts"})
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"carts"})
    private Business business;

}