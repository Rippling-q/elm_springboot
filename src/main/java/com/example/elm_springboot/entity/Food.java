package com.example.elm_springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodId;

    @Column(nullable = false)
    private String foodName;

    @Column(nullable = false)
    private String foodExplain; //介绍

    @Column(nullable = false)
    private String foodImg;

    @Column(nullable = false)
    private Double foodPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"foods"})
    private Business business;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "food")
    @JsonIgnoreProperties(value = {"food"})
    private List<Cart> carts;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "food")
    @JsonIgnoreProperties(value = {"food"})
    private List<OrderDetailet> orderDetailets;

    private String remarks; //备注

    @Override
    public String toString() {
        return "Food{" +
                "foodId=" + foodId +
                ", foodName='" + foodName + '\'' +
                ", foodExplain='" + foodExplain + '\'' +
                ", foodImg='" + foodImg + '\'' +
                ", foodPrice=" + foodPrice +
//                ", business=" + business.getBusinessName() +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}