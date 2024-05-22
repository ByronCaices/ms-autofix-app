
package com.autofix.msrepairs.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "discount_reg_client")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class DiscountRegClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String category;
    private String engine;
    private float discount;
}
