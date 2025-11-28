package com.dairy.Repository;


import com.dairy.Entity.MilkTypeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface MilkTypeMasterRepository extends JpaRepository<MilkTypeMaster,Integer> {
    @Query(value = "SELECT * FROM milktype WHERE animal_name = :animal_name", nativeQuery = true)
    List<MilkTypeMaster>findByName(@Param("animal_name") String animal_name);
    @Query(value="SELECT id,animal_name FROM milktype WHERE id= :id;" ,nativeQuery = true)
    MilkTypeMaster findByIdAndName(@PathVariable("id")int id);
    @Query(value="SELECT * FROM milktype WHERE is_delete=0",nativeQuery = true)
    List<MilkTypeMaster> getActiveDeactive();
    @Query(value = "SELECT * FROM milktype WHERE is_active=1",nativeQuery = true)
    List<MilkTypeMaster>getActiveData();
}
