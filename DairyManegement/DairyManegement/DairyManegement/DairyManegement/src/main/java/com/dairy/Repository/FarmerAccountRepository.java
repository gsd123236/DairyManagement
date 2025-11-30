package com.dairy.Repository;

import com.dairy.Entity.FarmerAccount;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FarmerAccountRepository extends JpaRepository<FarmerAccount,Integer> {
    @Query(value = "SELECT * FROM farmer_account WHERE farmer_id = :farmer_id", nativeQuery = true)
    List<FarmerAccount> findByForeignKey(@Param("farmer_id") int farmer_id);

    @Query(value = "SELECT * FROM farmer_account WHERE farmer_id = :farmer_id ORDER BY transaction_date DESC LIMIT 1", nativeQuery = true)
    Optional<FarmerAccount> findLatestTransactionByFarmerId(@Param("farmer_id") int farmerId);

    @Query(value = "SELECT * FROM farmer_account WHERE farmer_id = :farmer_id ORDER BY transaction_date ", nativeQuery = true)
    List<FarmerAccount> findTransactionByFarmer(@Param("farmer_id") int farmerId);

    @Query(value="SELECT * FROM farmer_account f WHERE f.farmer_id = :farmerId AND f.date BETWEEN :startDate AND :endDate ORDER BY transaction_date DESC",nativeQuery = true)
    List<FarmerAccount> findTransactionsByDateRange(@Param("farmerId") int farmerId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    @Query(value="SELECT  * From farmer_account f WHERE f.farmer_id= :farmer_id ORDER BY transaction_date DESC LIMIT 1",nativeQuery = true )
    Optional<FarmerAccount>AccountBalance(@Param("farmer_id") int farmerId);


    @Query(value = "SELECT * " +
            "FROM farmer_account " +
            "WHERE farmer_id = :farmer_id " +
            "AND `date` BETWEEN :startDate AND :endDate",
            nativeQuery = true)
    List<FarmerAccount> getAccountListByDate(
            @Param("farmer_id") int farmerId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );



    @Modifying
    @Transactional
    @Query(value=" DELETE  FROM farmer_account WHERE farmer_id= :farmer_id" ,nativeQuery = true)
    int deleteByFarmerId(@Param("farmer_id") int farmerId );
    @Query(value = "SELECT balance FROM farmer_account c WHERE c.farmer_id = :farmer_id ORDER BY transaction_date DESC LIMIT 1", nativeQuery = true)
    Double getByBalance(@Param("farmer_id") int farmer_id);


}

