package com.dairy.Repository;

import com.dairy.Entity.DairyExpensive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DairyExpensiveRepository extends JpaRepository<DairyExpensive,Integer> {
    @Query(value = "SELECT * FROM dairy_expensive  WHERE  dairy_id= :dairy_id AND date BETWEEN :startDate AND :endDate ORDER BY date ", nativeQuery = true)
    List<DairyExpensive> findTransactionsByDateRange( @PathVariable ("dairy_id")int dairy_id, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    @Query(value = "SELECT * FROM dairy_expensive WHERE dairy_id= :dairy_id",nativeQuery = true)
    List<DairyExpensive>findByIdExpensive(@PathVariable ("dairy_id")int dairy_id);

}