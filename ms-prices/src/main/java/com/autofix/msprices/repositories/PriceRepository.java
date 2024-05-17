package com.autofix.msprices.repositories;

import com.autofix.msprices.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, Long> {

    public List<PriceEntity> findByRepairType(Integer repairType);

    public List<PriceEntity> findByEngine(String engine);

    @Query(value = "SELECT price FROM prices WHERE engine = :engine AND repair_type = :repair_type", nativeQuery = true)
    Integer findPriceEntityByEngineAndRepairType(@Param("engine") String engine, @Param("repair_type") Integer repair_type);


}