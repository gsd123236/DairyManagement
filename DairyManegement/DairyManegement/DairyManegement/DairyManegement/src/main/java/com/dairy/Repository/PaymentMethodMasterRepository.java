package com.dairy.Repository;

import com.dairy.Entity.PaymentMethodMaster;
import com.dairy.Entity.PaymentPeriodMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentMethodMasterRepository extends JpaRepository<PaymentMethodMaster, Integer> {
    @Query(value = "SELECT * FROM payment_type WHERE payment_method= :payment_method",nativeQuery = true)
    List<PaymentMethodMaster> paymentTypeByName(@Param("payment_method") String payment_method);
    @Query(value="SELECT * FROM payment_type WHERE (is_active=1 OR is_deactive=1 )AND is_delete=0" ,nativeQuery = true)
    List<PaymentMethodMaster> getAllActiveOrDeactive();
    @Query(value = "SELECT * FROM payment_type WHERE is_active=1;",nativeQuery = true)
    List<PaymentMethodMaster>getAllActiveData();
}
