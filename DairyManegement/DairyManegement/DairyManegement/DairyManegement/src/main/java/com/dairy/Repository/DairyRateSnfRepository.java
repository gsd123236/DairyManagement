package com.dairy.Repository;

import com.dairy.Entity.DairyRateSnf;
import com.dairy.Entity.DairyRateStartingAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DairyRateSnfRepository extends JpaRepository<DairyRateSnf,Integer> {

    void deleteByDairyRateStartingAmount(DairyRateStartingAmount savedStartingAmount);
}