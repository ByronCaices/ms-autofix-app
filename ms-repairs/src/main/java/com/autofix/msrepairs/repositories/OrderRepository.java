package com.autofix.msrepairs.repositories;

import com.autofix.msrepairs.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String> {

    @Query(value = "SELECT disc_bonus FROM orders where repair_code = :repair_code", nativeQuery = true)
    public float getBonusByRepairCode(String repair_code);

}
