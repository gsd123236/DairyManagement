package com.dairy.Repository;

import com.dairy.Entity.CustomerKhata;
import com.dairy.Entity.FarmerAccount;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerKhataRepository extends JpaRepository<CustomerKhata,Integer> {
    @Query(value = "SELECT * FROM customer_khata WHERE customer_id = :customer_id ORDER BY transaction_date DESC LIMIT 1", nativeQuery = true)
    Optional<CustomerKhata> findLatestTransactionByCustomerId(@Param("customer_id") int customerId);

    @Query(value = "SELECT * FROM customer_khata WHERE customer_id= :customer_id ORDER BY transaction_date DESC LIMIT 1", nativeQuery = true)
    Optional<CustomerKhata> findBalanceByCustomerId(@Param("customer_id") int customerId);

    @Query(value = "SELECT * FROM customer_khata WHERE customer_id= :customer_id ORDER BY transaction_date ASC", nativeQuery = true)
    List<CustomerKhata> getTransactionById(@Param("customer_id") int customerId);

//    @Query(value = "SELECT * FROM customer_khata WHERE customer_id = :customer_id ORDER BY transaction_date DESC LIMIT 1", nativeQuery = true)
//    Optional<CustomerKhata> findLatestTransactionByCustomerId(@Param("customer_id") int customerId);

    @Query(value = "SELECT * FROM customer_khata as c WHERE c.customer_id= :customer_id AND  c.date BETWEEN  :start_date AND :end_date", nativeQuery = true)
    List<CustomerKhata> getTransactionByDatesByFarmer(@Param("customer_id") int customerId, @Param("start_date") LocalDate startDate, @Param("end_date") LocalDate endDate);

    @Query(value = "SELECT * FROM customer_khata as c WHERE c.date BETWEEN :start_date AND :end_date", nativeQuery = true)
    List<CustomerKhata> getTransactionByDates(@Param("start_date") LocalDate startDate, @Param("end_date") LocalDate endDate);

    @Modifying
    @Transactional
    @Query(value=" DELETE  FROM customer_khata WHERE customer_id= :customer_id" ,nativeQuery = true)
    int deleteByCustomerId(@Param("customer_id") int customerId );
    @Query(value = "SELECT balance FROM Customer_khata c WHERE c.customer_id = :customer_id ORDER BY transaction_date DESC LIMIT 1", nativeQuery = true)
    Double getByBalance(@Param("customer_id") int customer_id);
    @Query(value="select balance FROM customer_khata  WHERE customer_id= :customer_id AND date BETWEEN :startDate AND :endDate  ORDER BY transaction_date DESC LIMIT 1",nativeQuery = true)
    Double getByBalanceByDate(@Param("customer_id") int customer_id,@Param("startDate") LocalDate startDate,@Param("endDate") LocalDate endDate);

//    @Query(value = "SELECT * FROM customer_khata WHERE customer_id = :customer_id ORDER BY transaction_date DESC LIMIT 1", nativeQuery = true)
//    Optional<CustomerKhata> findLatestTransactionByCustomerId(@Param("customer_id") int customer_id);

}