package com.dairy.Repository;

import com.dairy.Entity.CustomerRegistration;
import com.dairy.Entity.DairyRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRegistrationRepository  extends JpaRepository<CustomerRegistration,Integer> {
    @Query(value = "SELECT * FROM customer_registration WHERE dairy_id = :dairy_id", nativeQuery = true)
    List<CustomerRegistration> findByForeignKey(@Param("dairy_id") int dairy_id);
    boolean existsByCodeAndDairyRegistration(String  code, DairyRegistration dairyRegistration);
    boolean existsByMobileAndDairyRegistration(String mobile, DairyRegistration dairyRegistration);
    //    @Query(value="SELECT *FROM customer_registration WHERE dairy_id= :dairy_id and id= :id",nativeQuery = true)
//    CustomerRegistration findByForeginKeyCustomer(@Param("dairy_id") int dairy_id, @Param("id") int id);
    @Query(value="SELECT * FROM customer_registration WHERE is_active=1 AND dairy_id= :dairy_id ",nativeQuery = true)
    List<CustomerRegistration>getAllActive(@Param("dairy_id")int dairy_id);
    @Query(value = "SELECT * FROM customer_registration WHERE is_deactive=1 AND dairy_id= :dairy_id",nativeQuery = true)
    List<CustomerRegistration>getAllInactive(@PathVariable("dairy_id")int dairy_id);
    @Query(value = "SELECT dairy_id FROM customer_registration WHERE id= :id",nativeQuery = true)
    Optional<Integer> getdairyIdByCustomerId(@PathVariable("id")int id);
}
