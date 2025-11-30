package com.dairy.Repository;

import com.dairy.Entity.CustomerRateChart;
import com.dairy.Entity.CustomerRateFat;
import com.dairy.Entity.DairyRateStartingAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRateFatRepository extends JpaRepository<CustomerRateFat,Integer> {
//    void deleteByCustomerRateStartingAmount(CustomerRateChart savedStartingAmount);
}
