package com.autofix.msrepairs.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrderEntity {

    @Id
    @Column(unique = true, nullable = false)
    private String repairCode;

    private String plate;
    private String engine;
    private String bodywork;
    private float discBonus;
    private float totalAmount;
}

