package com.dairy.Repository;

import com.dairy.Entity.CustomerRateChart;
import com.dairy.Entity.CustomerRateSnf;
import com.dairy.Entity.DairyRateStartingAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRateSnfRepository extends JpaRepository<CustomerRateSnf,Integer> {
//    void deleteByCustomerRateStartingAmount(CustomerRateChart savedStartingAmount);
}
