package com.autofix.msrepairs.repositories;

import com.autofix.msrepairs.entities.DiscountRegClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountRegClientRepository extends JpaRepository<DiscountRegClientEntity,Long> {

    public List<DiscountRegClientEntity>findDiscountRegClientEntityByCategoryAndEngine(String category, String engine);

    @Query(value = "SELECT discount FROM discount_reg_client WHERE category = :category AND engine = :engine", nativeQuery = true)
    public float findDiscountRCByCategoryAndEngine(@Param("category") String category, @Param("engine") String engine);
}
