package com.autofix.msrepairs.repositories;

import com.autofix.msrepairs.entities.SurchargeCarAgeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SurchargeCarAgeRepository extends JpaRepository<SurchargeCarAgeEntity, Long> {

    @Query(value = "SELECT surcharge FROM surcharge_carage WHERE category = :category AND bodywork = :bodywork", nativeQuery = true)
    public float getSurchargeByCategoryAndBodywork(@Param("category") String category, @Param("bodywork") String bodywork);
}
