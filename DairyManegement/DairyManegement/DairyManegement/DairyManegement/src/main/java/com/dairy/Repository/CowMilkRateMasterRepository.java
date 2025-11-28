package com.dairy.Repository;

import com.dairy.Entity.CowMilkRateMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface CowMilkRateMasterRepository extends JpaRepository<CowMilkRateMaster,Integer> {
    @Query(value = "SELECT *FROM cow_milk_rate_master WHERE rate_type= :rate_type",nativeQuery = true)
    List<CowMilkRateMaster>getByName(@Param("rate_type") String rate_type);
    @Query(value = "SELECT *FROM cow_milk_rate_master WHERE is_delete=0",nativeQuery = true)
    List<CowMilkRateMaster>getAllActiveDeactive();
    @Query(value="SELECT *FROM  cow_milk_rate_master WHERE is_active=1",nativeQuery = true)
    List<CowMilkRateMaster>getActiveData();
}
