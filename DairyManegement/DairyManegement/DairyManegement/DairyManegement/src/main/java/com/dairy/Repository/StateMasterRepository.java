package com.dairy.Repository;


import com.dairy.Entity.StateMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateMasterRepository extends JpaRepository<StateMaster,Integer>{

    @Query(value = "SELECT * FROM state WHERE state_name = :state_name", nativeQuery = true)
    List<StateMaster> findByName(@Param("state_name") String stateName);

    @Query(value="SELECT * FROM state WHERE (is_active=1 OR is_deactive=1 )AND is_delete=0" ,nativeQuery = true)
    List<StateMaster> getAllActiveOrDeactive();
    @Query(value = "SELECT * FROM state WHERE is_active=1",nativeQuery = true)
    List<StateMaster>getAllActive();

}

