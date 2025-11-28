package com.dairy.Repository;

import com.dairy.Entity.CustomerInvoice;
import com.dairy.Entity.CustomerInvoice2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerInvoiceRepository extends JpaRepository<CustomerInvoice,Integer> {
    @Query("SELECT ci FROM CustomerInvoice ci WHERE ci.customerRegistration.id = :customerId AND ci.date = :date")
    Optional<CustomerInvoice> findByCustomerIdAndDate(@Param("customerId") int customerId, @Param("date") LocalDate date);

    @Query(value = "SELECT * FROM customer_invoice WHERE customer_id= :customer_id AND date BETWEEN :startDate AND  :endDate" ,nativeQuery = true)
    List<CustomerInvoice>getByDate(@Param("customer_id")int customer_id, @Param("startDate")LocalDate startDate ,@Param("endDate") LocalDate endDate);

    @Query(value = "SELECT  * FROM customer_invoice  WHERE customer_id= :customerId",nativeQuery = true)
    Optional<CustomerInvoice> findByCustomerId(@Param("customerId") int customerId);
    @Query(value = "SELECT * FROM customer_invoice WHERE dairy_id= :dairy_id AND pending_status=false;",nativeQuery = true)
    List<CustomerInvoice> getBalanceByDairyId(@PathVariable("dairy_id")int dairy_id);
    @Query(value = "SELECT * FROM customer_invoice WHERE dairy_id= :dairy_id AND pending_status=true;",nativeQuery = true)
    List<CustomerInvoice> getBalanceByDairyId1(@PathVariable("dairy_id")int dairy_id);
    @Query(value = "SELECT * FROM customer_invoice " +
            "WHERE dairy_id = :dairyId " +
            "AND date BETWEEN :startDate AND :endDate " +
            "AND pending_status = FALSE " +
            "ORDER BY date DESC",
            nativeQuery = true)
    List<CustomerInvoice> getBalanceDate(
            @Param("dairyId") int dairyId,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate
    );

}
