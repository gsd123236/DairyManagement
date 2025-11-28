package com.dairy.Repository;

import com.dairy.Entity.CustomerKhata;
import com.dairy.Entity.CustomerMilkSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerMilkSaleRepository extends JpaRepository<CustomerMilkSale,Integer> {
//    @Query("SELECT c FROM CustomerMilkSale c WHERE c.date = :date AND c.dairyRegistration.dId = :dId")
//    List<CustomerMilkSale> getByDateAndDairyId(@Param("date") LocalDate date, @Param("dId") Integer dId);

    @Query(value = "SELECT m.id, m.shift, m.clr, m.milk_type, m.fat, m.liter, m.snf, m.amount, m.rate, m.transaction_date, " +
            "r.id AS customer_id, r.name, r.code " +
            "FROM customer_milk_sale m " +
            "JOIN customer_registration r ON r.id = m.customer_id " +
            "WHERE m.d_id = :dId AND DATE(m.date) = :createdDate", nativeQuery = true)
    List<Object[]> getByDateAndDairyId(@Param("dId") Integer dId, @Param("createdDate") LocalDate createdDate);


    @Query(value = "SELECT m.id, m.shift, m.milk_type, m.liter, m.amount, m.rate,m.paid, m.transaction_date, " +
            "r.id AS customer_id, r.name, r.code FROM customer_milk_sale m " +
            "JOIN customer_registration r ON r.id = m.customer_id " +
            "WHERE m.d_id = :dId AND DATE(m.date) BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<Object[]> getByCustomerInvoice(@Param("dId") Integer dId,
                                        @Param("startDate") LocalDate startDate,
                                        @Param("endDate") LocalDate endDate);




    @Query(value="SELECT * FROM customer_milk_sale WHERE date= :date AND shift=  :shift ;",nativeQuery = true)
    List<CustomerMilkSale>getByDateAndShfit(@PathVariable("date")LocalDate date, @PathVariable("shift") int shift);
    @Query(value = "SELECT * FROM customer_milk_sale WHERE date= :date ;",nativeQuery = true)
    List<CustomerMilkSale>getByDate(@PathVariable("date")LocalDate date);
    @Query(value="SELECT amount FROM customer_milk_sale WHERE customer_id= :customer_id ORDER BY date ;",nativeQuery = true)
    List<Double>add(@PathVariable("customer_id")int customer_id);
    @Query(value = "SELECT paid FROM customer_milk_sale WHERE customer_id= :customer_id ORDER BY date;",nativeQuery = true)
    List<Double>getpaid(@PathVariable("customer_id")int customer_id);
    @Query(value="SELECT amount FROM customer_milk_sale WHERE customer_id= :customer_id AND date BETWEEN :startDate AND :endDate",nativeQuery = true)
    List<Double>getAmountByDate(@PathVariable ("customer_id") int customer_id, @Param("startDate") LocalDate startDate, @Param("endDate")LocalDate endDate);
    @Query(value="SELECT paid FROM customer_milk_sale WHERE customer_id= :customer_id AND date BETWEEN :startDate AND :endDate",nativeQuery = true)
    List<Double>getPaidByDate(@PathVariable("customer_id") int customer_id,@Param("startDate")LocalDate startDate,@Param("endDate")LocalDate endDate);


    @Query("SELECT COALESCE(SUM(c.amount), 0) FROM CustomerMilkSale c WHERE c.customerRegistration.id = :customerId")
    Double getTotalMilkAmount(@Param("customerId") int customerId);

    @Query("SELECT COALESCE(SUM(c.paid), 0) FROM CustomerMilkSale c WHERE c.customerRegistration.id = :customerId")
    Double getTotalPaid(@Param("customerId") int customerId);
}


