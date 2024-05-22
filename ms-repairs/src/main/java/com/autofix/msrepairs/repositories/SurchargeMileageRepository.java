package com.autofix.msrepairs.repositories;

import com.autofix.msrepairs.entities.SurchargeMileageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurchargeMileageRepository extends JpaRepository<SurchargeMileageEntity,Long> {

    public List<SurchargeMileageEntity> findSurchargeMileageEntitiesByBodywork(String bodywork);

    @Query(value = "SELECT surcharge FROM surcharge_mileage WHERE category = :category AND bodywork = :bodywork", nativeQuery = true)
    public float getSurchargeMileageByCategoryAndBodywork(@Param("category") String category, @Param("bodywork") String bodywork);
}
