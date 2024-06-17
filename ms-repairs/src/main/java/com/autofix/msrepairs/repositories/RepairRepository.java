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

    @Query(value = """
        WITH meses AS (
            SELECT
                date_trunc('month', CAST(:month AS TIMESTAMP) - INTERVAL '2 months') AS start_month,
                date_trunc('month', CAST(:month AS TIMESTAMP) + INTERVAL '1 month') AS end_month
        ),
        aggregated_data AS (
            SELECT
                to_char(r.checkin_date, 'Month') AS report_month,
                r.repair_type,
                COUNT(r.id) AS num_repairs,
                SUM(r.total_amount) AS total_amount
            FROM
                repairs r
            WHERE
                r.checkin_date >= (SELECT start_month FROM meses)
                AND r.checkin_date < (SELECT end_month FROM meses)
            GROUP BY
                to_char(r.checkin_date, 'Month'),
                r.repair_type
        ),
        monthly_changes AS (
            SELECT
                ad.report_month,
                ad.repair_type,
                ad.num_repairs,
                LAG(ad.num_repairs, 1) OVER (PARTITION BY ad.repair_type ORDER BY to_date(ad.report_month, 'Month')) AS prev_month_repairs,
                ad.total_amount,
                LAG(ad.total_amount, 1) OVER (PARTITION BY ad.repair_type ORDER BY to_date(ad.report_month, 'Month')) AS prev_month_amount
            FROM
                aggregated_data ad
        )
        SELECT
            mc.report_month,
            mc.repair_type,
            mc.num_repairs,
            COALESCE(((mc.num_repairs - mc.prev_month_repairs) / NULLIF(mc.prev_month_repairs, 0)::float), 0) AS month_on_month_repair_change,
            mc.total_amount,
            COALESCE(((mc.total_amount - mc.prev_month_amount) / NULLIF(mc.prev_month_amount, 0)::float), 0) AS month_on_month_amount_change
        FROM
            monthly_changes mc
        ORDER BY
            to_date(mc.report_month, 'Month') ASC
        """, nativeQuery = true)
    List<Object[]> getMonthlyRepairReport2(@Param("month") String month);

    @Query(value = """
    WITH meses AS (
        SELECT
            date_trunc('month', CAST(:month AS TIMESTAMP) - INTERVAL '2 months') AS start_month,
            date_trunc('month', CAST(:month AS TIMESTAMP) + INTERVAL '1 month') AS end_month
    ),
    aggregated_data AS (
        SELECT
            to_char(r.checkin_date, 'Month') AS report_month,
            r.repair_type,
            COUNT(r.id) AS num_repairs,
            SUM(r.total_amount) AS total_amount
        FROM
            repairs r
        WHERE
            r.checkin_date >= (SELECT start_month FROM meses)
            AND r.checkin_date < (SELECT end_month FROM meses)
        GROUP BY
            to_char(r.checkin_date, 'Month'), r.repair_type
    ),
    monthly_changes AS (
        SELECT
            ad.report_month,
            ad.repair_type,
            ad.num_repairs,
            LAG(ad.num_repairs, 1) OVER (PARTITION BY ad.repair_type ORDER BY to_date(ad.report_month, 'Month')) AS prev_month_repairs,
            ad.total_amount,
            LAG(ad.total_amount, 1) OVER (PARTITION BY ad.repair_type ORDER BY to_date(ad.report_month, 'Month')) AS prev_month_amount
        FROM
            aggregated_data ad
    )
    SELECT
        mc.report_month,
        mc.repair_type,
        mc.num_repairs,
        COALESCE(CAST((mc.num_repairs - mc.prev_month_repairs) AS FLOAT) / NULLIF(CAST(mc.prev_month_repairs AS FLOAT), 0), 0) AS month_on_month_repair_change,
        mc.total_amount,
        COALESCE(CAST((mc.total_amount - mc.prev_month_amount) AS FLOAT) / NULLIF(CAST(mc.prev_month_amount AS FLOAT), 0), 0) AS month_on_month_amount_change
    FROM
        monthly_changes mc
    ORDER BY
        to_date(mc.report_month, 'Month') ASC
    """, nativeQuery = true)
    List<Object[]> getMonthlyRepairReport(@Param("month") String month);
}
