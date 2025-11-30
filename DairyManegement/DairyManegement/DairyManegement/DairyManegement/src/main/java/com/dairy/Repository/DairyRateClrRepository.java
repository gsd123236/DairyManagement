package com.dairy.Repository;

import com.dairy.Entity.DairyRateClr;
import com.dairy.Entity.DairyRateStartingAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DairyRateClrRepository extends JpaRepository<DairyRateClr,Integer>{

    void deleteByDairyRateStartingAmount(DairyRateStartingAmount savedStartingAmount);
}