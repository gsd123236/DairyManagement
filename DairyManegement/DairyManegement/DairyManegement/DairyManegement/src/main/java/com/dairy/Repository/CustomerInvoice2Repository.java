package com.dairy.Repository;

import com.dairy.Entity.CustomerInvoice;
import com.dairy.Entity.CustomerInvoice2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface CustomerInvoice2Repository extends JpaRepository<CustomerInvoice2 ,Integer> {
    @Query(value = "SELECT * FROM customer_invoice2 WHERE dairy_id= :dairy_id AND pending_status=true;", nativeQuery = true)
    List<CustomerInvoice2> getBalanceByDairyId1(@PathVariable("dairy_id") int dairy_id);

    Optional<CustomerInvoice2> findByCustomerRegistrationIdAndId(Integer customerId, Integer invoiceId);

    @Query(value = "SELECT * FROM customer_invoice2 " +
            "WHERE dairy_id = :dairyId " +
            "AND date BETWEEN :startDate AND :endDate " +
            "AND pending_status = TRUE " +
            "ORDER BY date DESC",
            nativeQuery = true)
    List<CustomerInvoice2> getBalanceDate(
            @Param("dairyId") int dairyId,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate
    );
    @Query(value = "SELECT invoice_number FROM customer_invoice2 ORDER BY invoice_number DESC LIMIT 1" ,nativeQuery = true)
      int getInvoiceNumber();
}
