package com.dairy.Repository;

import com.dairy.Entity.ExpenseCategoriesMaster;
import com.dairy.Entity.PaymentPeriodMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface ExpenseCategoriesMasterRepository extends JpaRepository<ExpenseCategoriesMaster,Integer> {
    @Query(value="SELECT * FROM expense_categories_master WHERE (is_active=1 OR is_deactive=1 )AND is_deleted=0" ,nativeQuery = true)
    List<ExpenseCategoriesMaster> getAllActiveOrDeactive();
    @Query(value="SELECT * FROM expense_categories_master WHERE is_active=1",nativeQuery = true)
    List<ExpenseCategoriesMaster>getActiveData();
    @Query(value = "SELECT *FROM expense_categories_master WHERE expense= :expense",nativeQuery = true)
    List<ExpenseCategoriesMaster>getByName(@Param("expense") String expense);
}
