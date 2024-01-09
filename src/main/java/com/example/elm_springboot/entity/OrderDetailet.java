package com.example.elm_springboot.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderDetailet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long odId;

    @Column
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"orderDetailet"})
    private Food food;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"orderDetailets"})
    private Orders orders;
}
