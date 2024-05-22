
package com.autofix.msrepairs.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "discount_bonus")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class DiscountBonusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String brand;
    private Integer bonus;
    private Integer stock;
}
