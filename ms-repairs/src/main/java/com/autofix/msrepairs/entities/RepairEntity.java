package com.autofix.msrepairs.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "repairs")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class RepairEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String repairCode;
    private String plate;
    private String engine;
    private String bodywork;
    private String brand;
    private Integer repairType;
    private Long mileage;
    private LocalDateTime checkinDate;
    private LocalDateTime finishDate;
    private LocalDateTime checkoutDate;
    //private Long surcharges;
    private float repairPrice;
    private float totalAmount;
    private float discRegClient;
    private float discBonus;
    private float discMonThu;
    private float surchCarage;
    private float surchMileage;
    private float surchDelay;
    private float iva;
}
