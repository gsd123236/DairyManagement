package com.dairy.Repository;


import com.dairy.Entity.CollectionShiftMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionShiftMasterRepository extends JpaRepository<CollectionShiftMaster,Integer> {
    @Query(value = "SELECT * FROM collection_shift WHERE shift_name = :shift_name", nativeQuery = true)
    List<CollectionShiftMaster> findByName(@Param("shift_name") String shift_name);
    @Query(value = "SELECT * FROM collection_shift WHERE is_delete=0 ",nativeQuery = true)
    List<CollectionShiftMaster> ActiveDeactive();
    @Query(value = "SELECT * FROM collection_shift WHERE is_active=1",nativeQuery = true)
    List<CollectionShiftMaster>getAllData();

}
