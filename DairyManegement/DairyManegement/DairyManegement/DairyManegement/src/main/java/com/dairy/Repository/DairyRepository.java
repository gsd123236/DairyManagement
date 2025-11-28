package com.dairy.Repository;

import com.dairy.Entity.DairyRegistration;
import com.dairy.Entity.StateMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DairyRepository extends JpaRepository<DairyRegistration,Integer> {

    Optional<DairyRegistration> findByMobile(String mobile);


    @Query(value = "SELECT  first_name,last_name,mobile,village,taluka,district,s.state_name" +
            " FROM dairy_registration f JOIN state s ON f.state_id=s.id WHERE f.d_id= :dId", nativeQuery = true)
    List<Object[]> findDairyDetailsByDId(@Param("dId") Integer dId);


    @Query(value = "SELECT collection_shift_id, milk_type_id FROM dairy_registration WHERE d_id = :dId", nativeQuery = true)
    List<Object[]> getMilkCollection(@Param("dId") int dId);

    @Query(value="SELECT * FROM dairy_registration WHERE (is_active=1 OR is_deactive=1 )AND is_deleted=0" ,nativeQuery = true)
    List<DairyRegistration> getAllActiveOrDeactive();



}
