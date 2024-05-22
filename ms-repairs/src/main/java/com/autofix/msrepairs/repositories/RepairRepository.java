package com.autofix.msrepairs.repositories;

import com.autofix.msrepairs.entities.RepairEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairRepository extends JpaRepository<RepairEntity,Long> {

    public List<RepairEntity> findByPlate(String plate);
    public List<RepairEntity> findByBodywork(String bodywork);
    public List<RepairEntity> findByRepairType(Integer repairType);

    @Query(value = "SELECT * FROM repairs ORDER BY checkin_date DESC", nativeQuery = true)
    public List<RepairEntity> findAllOrderByCheckinDate();

    @Query(value="SELECT count(*)\n" +
            "FROM repairs\n" +
            "WHERE\n" +
            "    checkin_date >= CURRENT_DATE - INTERVAL '12 months'\n" +
            "    AND checkin_date < CURRENT_DATE\n" +
            "    AND plate = :plate", nativeQuery = true)
    public Integer countByPlateLastYear(@Param("plate")String plate);

    @Query(value = "SELECT repair_type,\n" +
            "       SUM(total_amount) AS suma_amount_repair_type,\n" +
            "       COUNT(*) AS number_repairs,\n" +
            "       bodywork\n" +
            "FROM repairs\n" +
            "GROUP BY repair_type, bodywork\n" +
            "ORDER BY SUM(total_amount) DESC, repair_type, bodywork", nativeQuery = true)
    List<Object[]> getRepairTypeAmountsByBodywork();

    @Query(value = "SELECT repair_type,\n" +
            "       SUM(total_amount) AS suma_amount_repair_type,\n" +
            "       COUNT(*) AS number_repairs,\n" +
            "       engine\n" +
            "FROM repairs\n" +
            "GROUP BY repair_type, engine\n" +
            "ORDER BY SUM(total_amount) DESC, repair_type, engine", nativeQuery = true)
    List<Object[]> getRepairTypeAmountsByEngine();

    @Query(value = "SELECT * FROM REPAIRS WHERE repair_code = :repair_code ORDER BY total_amount ASC", nativeQuery = true)
    public List<RepairEntity> findByRepairCode(@Param("repair_code") String repair_code);

    @Query(value = "SELECT sum(total_amount) FROM REPAIRS where repair_code = :repair_code", nativeQuery = true)
    public float sumTotalAmountByRepairCode(@Param("repair_code") String repair_code);

    @Query(value = "SELECT brand, " +
            "ROUND(AVG(CAST(EXTRACT(EPOCH FROM (finish_date - checkin_date)) AS NUMERIC) / 3600), 1) AS average_repair_time_hours " +
            "FROM repairs " +
            "GROUP BY brand " +
            "ORDER BY average_repair_time_hours ASC",
            nativeQuery = true)
    public List<Object[]> getAverageRepairTimeByBrand();





}
