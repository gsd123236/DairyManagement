package com.dairy.Repository;

import com.dairy.Entity.DairyRateFat;
import com.dairy.Entity.DairyRateStartingAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DairyRateFatRepository extends JpaRepository<DairyRateFat,Integer> {

    void deleteByDairyRateStartingAmount(DairyRateStartingAmount savedStartingAmount);
}