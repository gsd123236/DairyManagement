package com.dairy.Repository;

import com.dairy.Entity.BuffaloMilkRateMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuffaloMilkRateMasterRepository extends JpaRepository<BuffaloMilkRateMaster,Integer> {
    @Query(value = "SELECT * FROM buffalo_milk_rate_master WHERE is_delete=0",nativeQuery = true)
    List<BuffaloMilkRateMaster> getActiveDeactive();
    @Query(value = "SELECT * FROM buffalo_milk_rate_master WHERE is_active=1",nativeQuery = true)
    List<BuffaloMilkRateMaster>getActive();
    @Query(value = "SELECT * FROM buffalo_milk_rate_master WHERE rate_type = :rate_type",nativeQuery = true)
    List<BuffaloMilkRateMaster>getByName(@Param("rate_type") String rate_type);
}

