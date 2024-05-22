package com.autofix.msrepairs.repositories;

import com.autofix.msrepairs.entities.DiscountBonusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountBonusRepository extends JpaRepository<DiscountBonusEntity,Long> {

    public DiscountBonusEntity findByBrand(String brand);

    @Query(value = "SELECT COALESCE((SELECT stock FROM discount_bonus WHERE brand = :brand), 0)", nativeQuery = true)
    public int findStockByBrand(@Param("brand") String brand);

    @Query(value = "UPDATE discount_bonus\n" +
            "SET stock = stock - 1\n" +
            "WHERE brand = :brand", nativeQuery = true)
    public DiscountBonusEntity decreaseStockByBrand(@Param("brand") String brand);

    @Query(value = "SELECT bonus FROM discount_bonus WHERE brand = :brand", nativeQuery = true)
    public Integer findBonusByBrand(@Param("brand") String brand);

}
