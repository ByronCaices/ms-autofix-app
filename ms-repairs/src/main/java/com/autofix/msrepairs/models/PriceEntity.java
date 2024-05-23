package com.autofix.msrepairs.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class PriceEntity {

    private Long id;
    private Integer repairType;
    private String engine;
    private Integer price;
}