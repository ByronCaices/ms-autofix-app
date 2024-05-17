package com.autofix.msprices.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "prices")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private Integer repairType;

    @Column(nullable = false)
    private String engine;

    @Column(nullable = false)
    private Integer price;
}