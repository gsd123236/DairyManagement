package com.dairy.Repository;

import com.dairy.Entity.DairyRegistration;
import com.dairy.Entity.FarmerBalanceSheet;
import com.fasterxml.jackson.databind.node.DoubleNode;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface FarmerBalanceSheetRepository  extends JpaRepository<FarmerBalanceSheet,Integer> {
//    @Query(value = "SELECT CASE WHEN EXISTS (SELECT 1 FROM farmer_balance_sheet WHERE farmer_id = :farmer_id) THEN 'TRUE' ELSE 'FALSE' END AS result;",nativeQuery = true)
//    boolean farmerExistOrNot(@PathVariable("farmer_id") int farmer_id);
    @Query(value="SELECT balance FROM farmer_balance_sheet WHERE farmer_id= :farmer_id",nativeQuery = true)
    Double getByFarmerId(@PathVariable("farmer_id") int farmer_id);
    @Query("SELECT fbs FROM FarmerBalanceSheet fbs WHERE fbs.farmerId.id = :farmerId")
    Optional<FarmerBalanceSheet> findByFarmerId(@Param("farmerId") int farmerId);
  @Query(value = "SELECT *FROM farmer_balance_sheet WHERE dairy_id= :dairy_id",nativeQuery = true)
  List<FarmerBalanceSheet>getBalanceByDairyId(@PathVariable("dairy_id")int dairy_id);
//  @Query(value = "SELECT balance FROM farmer_balance_sheet WHERE farmer_id= :farmer_id",nativeQuery = true)
//  Double getBalanceByFarmerId(@PathVariable("farmer_id") int farmer_id);
}
