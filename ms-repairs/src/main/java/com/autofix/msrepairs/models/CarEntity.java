package com.autofix.msrepairs.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
