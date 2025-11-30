package com.dairy.Repository;

import com.dairy.Entity.FarmerRegistration;
import com.dairy.Entity.DairyRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface FarmerRegistrationRepository extends JpaRepository<FarmerRegistration,Integer> {
    @Query(value = "SELECT * FROM farmer_registration WHERE dairy_id = :dairy_id", nativeQuery = true)
    List<FarmerRegistration> findByForeignKey(@Param("dairy_id") int dairy_id);
    boolean existsByCodeAndDairyRegistration(String code, DairyRegistration dairyRegistration);
    boolean existsByMobileAndDairyRegistration(String mobile, DairyRegistration dairyRegistration);
//   @Query(value="SELECT *FROM farmer_registration WHERE dairy_id= :dairy_id and id= :id",nativeQuery = true)
//    FarmerRegistration findByForeginKeyCustomer(@Param("dairy_id") int dairy_id, @Param("id") int id);
//    Optional<FarmerRegistration> findByCode(String farmerCode);
    @Query(value = "SELECT *FROM farmer_registration WHERE is_Active=TRUE;",nativeQuery = true)
     List<FarmerRegistration>findAllByStatusActive();
  //  @Query(value = "SELECT milk_type_id FROM dairy_registration WHERE d_id= :did",nativeQuery = true)
    @Query(value = "SELECT dairy_id FROM farmer_registration WHERE id= :id",nativeQuery = true)
    Optional<Integer> getdairyIdByFarmerId(@PathVariable("id")int id);
  @Query(value = "SELECT milk_type FROM farmer_registration WHERE id= :id",nativeQuery = true)
    Integer getMilkTypeByFarmerId(@Param("id")Integer id);
  @Query(value="SELECT * FROM farmer_registration WHERE is_active=1 AND dairy_id= :dairy_id ",nativeQuery = true)
  List<FarmerRegistration>getAllActive(@Param("dairy_id")int dairy_id);
  @Query(value = "SELECT * FROM farmer_registration WHERE is_delete=1 AND dairy_id= :dairy_id",nativeQuery = true)
    List<FarmerRegistration>getAllInactive(@PathVariable("dairy_id")int dairy_id);

//    @Query(value ="SELECT f.first_name," +
//            "f.last_name,f.mobile,f.farmer_code,d.dairy_name FROM  " +
//            "farmer_registration f JOIN dairy_registration d;",nativeQuery = true)
//    List<FarmerRegistration>getAllFarmerAdmin();
}
