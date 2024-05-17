package com.autofix.mscar.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cars")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarEntity {

    @Id
    @Column(unique = true, nullable = false)
    private String plate;

    private String engine;
    private String brand;
    private String model;
    private String bodywork;
    private Integer year;
    private Integer seats;
    private Long mileage;
}