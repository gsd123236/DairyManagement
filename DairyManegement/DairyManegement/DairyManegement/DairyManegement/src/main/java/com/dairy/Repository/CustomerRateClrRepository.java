package com.dairy.Repository;

import com.dairy.Entity.CustomerRateChart;
import com.dairy.Entity.CustomerRateClr;
import com.dairy.Entity.DairyRateStartingAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRateClrRepository extends JpaRepository<CustomerRateClr,Integer> {
//    void deleteByCustomerRateStartingAmount(CustomerRateChart savedStartingAmount);
}
