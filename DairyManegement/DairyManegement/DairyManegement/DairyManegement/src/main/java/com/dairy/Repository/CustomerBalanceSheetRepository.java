package com.dairy.Repository;

import com.dairy.Entity.CustomerBalanceSheet;
import com.dairy.Entity.FarmerBalanceSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerBalanceSheetRepository extends JpaRepository<CustomerBalanceSheet,Integer> {
    @Query(value="SELECT balance FROM customer_balance_sheet WHERE customer_id= :customer_id",nativeQuery = true)
    Double getByCustomerId(@PathVariable("customer_id") int customer_id);
    @Query("SELECT fbs FROM CustomerBalanceSheet fbs WHERE fbs.customerId.id = :customerId")
    Optional<CustomerBalanceSheet> findByCustomerId(@Param("customerId") int customerId);
    @Query(value = "SELECT *FROM customer_balance_sheet WHERE dairy_id= :dairy_id",nativeQuery = true)
    List<CustomerBalanceSheet> getBalanceByDairyId(@PathVariable("dairy_id")int dairy_id);

}
