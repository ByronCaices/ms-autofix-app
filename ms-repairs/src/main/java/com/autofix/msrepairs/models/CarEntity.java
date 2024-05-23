package com.autofix.msrepairs.models;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CarEntity {

    private String plate;
    private String engine;
    private String brand;
    private String model;
    private String bodywork;
    private Integer year;
    private Integer seats;
    private Long mileage;
}
