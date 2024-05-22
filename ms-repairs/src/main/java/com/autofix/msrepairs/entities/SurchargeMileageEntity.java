
package com.autofix.msrepairs.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "surcharge_mileage")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class SurchargeMileageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String category;
    private String bodywork;
    private float surcharge;
}