package com.dairy.Repository;

import com.dairy.Entity.CustomerInvoice2;
import com.dairy.Entity.FarmerInvoice2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface FarmerInvoice2Respository extends JpaRepository<FarmerInvoice2,Integer> {
    @Query(value = "SELECT * FROM farmer_invoice2 WHERE dairy_id = :dairyId AND pending_status = TRUE", nativeQuery = true)
    List<FarmerInvoice2> getByDairyId(@Param("dairyId") int dairyId);
    @Query(value = "SELECT COALESCE(MAX(invoice_number), 0) FROM farmer_invoice2", nativeQuery = true)
    int getInvoice();

    @Query(value = "SELECT * FROM farmer_invoice2 " +
            "WHERE dairy_id = :dairyId " +
            "AND date BETWEEN :startDate AND :endDate " +
            "AND pending_status = TRUE " +
            "ORDER BY date DESC",
            nativeQuery = true)
    List<FarmerInvoice2> getBalanceDate(
            @Param("dairyId") int dairyId,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate
    );
    @Query(value = "UPDATE farmer_invoice2 SET pending_status =false WHERE farmer_id = :farmerId AND id= :InvoiceId;",nativeQuery = true)
    FarmerInvoice2 updateStatus(@Param("farmerId") int FarmerId,@Param("InvoiceId") int InvoiceId);
}
