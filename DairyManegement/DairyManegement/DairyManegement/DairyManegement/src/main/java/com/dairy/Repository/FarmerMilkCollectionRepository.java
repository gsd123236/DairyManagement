package com.dairy.Repository;

import com.dairy.Dto.FarmerMilkCollectionDto;
import com.dairy.Dto.FarmerMilkCollectionTotalDto;
import com.dairy.Entity.DairyRegistration;
import com.dairy.Entity.FarmerMilkCollection;
import com.dairy.Entity.FarmerRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FarmerMilkCollectionRepository extends JpaRepository<FarmerMilkCollection, Integer> {

    @Query(value = """
            SELECT 
                m.id, m.collection_shift_id, m.clr, m.milk_type_id,m.fat, m.liter, m.snf, m.total_price, m.rate,m.created_date_time, 
               m.farmer_id,  r.farmer_code, r.first_name, r.last_name FROM farmer_milk_collection m 
            JOIN farmer_registration r ON r.id = m.farmer_id
            WHERE 
                m.d_id = :dId
                AND DATE(m.created_date) = :createdDate
            """, nativeQuery = true)
    List<Object[]> findByDIdAndCreatedDate(@Param("dId") Integer dId, @Param("createdDate") LocalDate createdDate);

    @Query(value = "SELECT f.farmer_id,r.dairy_id, r.first_name, r.last_name, NULL AS milkType, NULL AS totalLiters," +
            " NULL AS totalPrice " +
            "FROM farmer_milk_collection f " +
            "LEFT JOIN farmer_registration r ON f.farmer_id = r.id " +
            "WHERE f.farmer_id = :farmerId", nativeQuery = true)
    List<Object[]> findByAndGetFarmerMileInfo(@Param("farmerId") Integer farmerId);


    @Query(value = """
            WITH milk_data AS (
                SELECT f.farmer_id, f.liter, f.total_price, f.created_date, r.first_name, r.last_name, m.animal_name AS milktype
                FROM farmer_milk_collection f LEFT JOIN farmer_registration r ON f.farmer_id = r.id
                LEFT JOIN milktype m ON f.milk_type_id = m.id WHERE f.farmer_id = :farmerId
                  AND f.milk_type_id = :milkTypeId AND f.d_id=:dId AND f.created_date BETWEEN :startDate AND :endDate
            )
            SELECT farmer_id, liter, milktype, total_price, created_date, first_name, last_name FROM milk_data
            UNION ALL
            SELECT NULL, SUM(liter), 'Total', SUM(total_price), NULL, NULL, NULL FROM milk_data;
            """, nativeQuery = true)
    List<Object[]> getMilkCollectionSummaryWithTotal(
            @Param("farmerId") Integer farmerId, @Param("milkTypeId") Integer milkTypeId,
            @Param("dId") Integer dId,
            @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate
    );

    @Query(value = """
                SELECT f.farmer_id, r.first_name, r.last_name, m.animal_name AS milktype,
                CAST(SUM(f.liter) AS DECIMAL(10,2)) AS total_liters, 
                CAST(SUM(f.total_price) AS DECIMAL(10,2)) AS total_price
                FROM farmer_milk_collection f 
                JOIN farmer_registration r ON f.farmer_id = r.id 
                JOIN milktype m ON f.milk_type_id = m.id 
                WHERE f.farmer_id = :farmerId 
                AND f.milk_type_id = :milkTypeId 
                AND f.d_id = :dId  
                AND f.created_date BETWEEN :startDate AND :endDate
                GROUP BY f.farmer_id, r.first_name, r.last_name, m.animal_name
            """, nativeQuery = true)
    List<Object[]> getMilkCollectionSummary(
            @Param("farmerId") Integer farmerId,
            @Param("milkTypeId") Integer milkTypeId,
            @Param("dId") Integer dId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    boolean existsByFarmerRegistrationAndCollectionShiftIdAndDairyRegistrationAndCreatedDateAndMilkTypeId(
            FarmerRegistration farmerRegistration,
            int collectionShiftId,
            DairyRegistration dairyRegistration,
            LocalDate createdDate,
            int milkTypeId
    );



    @Query(value = "SELECT m.id, m.collection_shift_id, m.clr, m.milk_type_id, m.fat, m.liter, m.snf, " +
            "m.total_price, m.rate, m.created_date, r.id AS farmer_id, r.first_name, r.last_name, r.farmer_code " +
            "FROM farmer_milk_collection m " +
            "JOIN farmer_registration r ON r.id = m.farmer_id " +
            "WHERE m.d_id = :dId " +
            "AND DATE(m.created_date) BETWEEN :startDate AND :endDate",
            nativeQuery = true)
    List<Object[]> getFarmerMilkCollectionByDate(@Param("dId") int dId,
                                                 @Param("startDate") LocalDate startDate,
                                                 @Param("endDate") LocalDate endDate);
    @Query(value="SELECT COALESCE(SUM(f.total_price), 0) FROM farmer_milk_collection f WHERE farmer_id = :farmerId",nativeQuery = true)
    Double getTotalMilkAmountByFarmerId(@Param("farmerId") int farmerId);
    @Query(value = "SELECT COALESCE(SUM(f.total_price), 0) FROM farmer_milk_collection f WHERE f.farmer_id = :farmerId AND f.created_date BETWEEN :startDate AND :endDate",
            nativeQuery = true)
    Double getTotalMilkAmountByFarmerId1(@Param("farmerId") int farmerId,
                                         @Param("startDate") LocalDate startDate,
                                         @Param("endDate") LocalDate endDate);



    @Query(value = "SELECT m.fat, m.snf, m.clr, m.rate, m.liter, m.collection_shift_id, m.milk_type_id, m.total_price,m.created_date " +
                "FROM farmer_milk_collection m " +
                "WHERE m.d_id = :dId " +
                "AND m.created_date BETWEEN :startDate AND :endDate",
                nativeQuery = true)
    List<Object[]> getMilkCollectionData(@Param("dId") int dId,
                                             @Param("startDate") LocalDate startDate,
                                             @Param("endDate") LocalDate endDate);


    @Query(value = "SELECT m.fat, m.snf, m.clr, m.rate, m.liter, m.collection_shift_id, " +
            "m.milk_type_id, m.total_price, f.farmer_code, f.first_name, f.last_name " +
            "FROM farmer_milk_collection m " +
            "JOIN farmer_registration f ON m.farmer_id = f.id " +
            "WHERE m.d_id = :dId " +
            "AND m.created_date = :createdDate",
            nativeQuery = true)
    List<Object[]> getMilkReportData(@Param("dId") int dId,
                                     @Param("createdDate") LocalDate createdDate);


}