package com.dairy.Repository;

import com.dairy.Entity.CustomerInvoice;
import com.dairy.Entity.FarmerInvoice;
import com.twilio.rest.api.v2010.account.availablephonenumbercountry.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FarmerInvoiceRepository extends JpaRepository<FarmerInvoice,Integer> {
    @Query(value = "SELECT  * FROM farmer_invoice  WHERE farmer_id= :farmerId",nativeQuery = true)
    Optional<FarmerInvoice> findByFarmerId(@Param("farmerId") int farmerId);
    @Query(value = "SELECT * FROM farmer_invoice WHERE dairy_id= :dairy_id AND pending_status=false;",nativeQuery = true)
    List<FarmerInvoice> getBalanceByDairyId(@PathVariable("dairy_id")int dairy_id);
    @Query(value="UPDATE farmer_invoice SET pending_status =false WHERE farmer_id = :farmerId;",nativeQuery = true)
    FarmerInvoice updateInvoice (@Param("farmerId")int farmerId);
    @Query(value = "SELECT * FROM farmer_invoice WHERE dairy_id= :dairyId AND DATE  BETWEEN  :startDate AND  :endDate AND pending_status=false;",nativeQuery = true)
    List<FarmerInvoice>getAllListDairyId(@Param("dairyId") int dairyId,
                                         @Param("startDate")LocalDate startDate,
                                         @Param("endDate")LocalDate endDate);

}
