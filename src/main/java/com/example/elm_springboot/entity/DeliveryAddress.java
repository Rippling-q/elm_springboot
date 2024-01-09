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
public class DeliveryAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long daId;

    @Column(nullable = false)
    private String contactName;

    @Column(nullable = false)
    private Integer contactSex;

    @Column(nullable = false)
    private String contactTel;

    @Column(nullable = false)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"deliveryAddresses"})
    private User user;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "deliveryAddress")
    @JsonIgnoreProperties(value = {"deliveryAddress"})
    private List<Orders> orders;
}