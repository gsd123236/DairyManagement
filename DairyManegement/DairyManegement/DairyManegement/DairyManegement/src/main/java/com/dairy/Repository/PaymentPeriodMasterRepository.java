package com.dairy.Repository;


import com.dairy.Entity.PaymentPeriodMaster;
import com.dairy.Entity.StateMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentPeriodMasterRepository extends JpaRepository<PaymentPeriodMaster,Integer> {
    @Query(value = "SELECT *from payment_period where payment_period= :payment_period",nativeQuery = true)
    List<PaymentPeriodMaster> paymentPeriodByName(@Param("payment_period") String payment_period);
    @Query(value="SELECT * FROM payment_period WHERE (is_active=1 OR is_deactive=1 )AND is_delete=0" ,nativeQuery = true)
    List<PaymentPeriodMaster> getAllActiveOrDeactive();
    @Query(value = "SELECT * FROM payment_period WHERE is_active=1" ,nativeQuery = true)
    List<PaymentPeriodMaster>getActiveData();

}
