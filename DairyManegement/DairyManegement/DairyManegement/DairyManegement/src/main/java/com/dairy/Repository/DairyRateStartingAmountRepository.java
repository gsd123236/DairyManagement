package com.dairy.Repository;

import com.dairy.Entity.DairyRateStartingAmount;
import com.dairy.Entity.DairyRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DairyRateStartingAmountRepository extends JpaRepository<DairyRateStartingAmount,Integer> {
    Optional<DairyRateStartingAmount> findByDairyRegistration(DairyRegistration dairyRegistration);
//@Query(value = """
//        SELECT
//            a.starting_amount, a.clr_increment_by, a.milk_type,
//            f.step AS fat_step, f.step_amount AS fat_step_amount,
//            s.step AS snf_step, s.step_amount AS snf_step_amount,
//            c.step AS clr_step, c.step_amount AS clr_step_amount
//        FROM dairy_rate_starting_amount a
//        JOIN dairy_rate_fat f ON a.id = f.s_id
//        JOIN dairy_rate_snf s ON a.id = s.sid
//        JOIN dairy_rate_clr c ON a.id = c.s_id
//        WHERE a.milk_type = :milkType AND a.dairy_id = :dairyId
//    """, nativeQuery = true)
//List<Object[]> findRatesByDairyId(@Param("dairyId") Integer dairyId);

    @Query(value = """
        SELECT 
            a.starting_amount, a.clr_increment_by, a.milk_type, 
            f.step AS fat_step, f.step_amount AS fat_step_amount, 
            s.step AS snf_step, s.step_amount AS snf_step_amount, 
            c.step AS clr_step, c.step_amount AS clr_step_amount
        FROM dairy_rate_starting_amount a
        JOIN dairy_rate_fat f ON a.id = f.s_id
        JOIN dairy_rate_snf s ON a.id = s.sid
        JOIN dairy_rate_clr c ON a.id = c.s_id
        WHERE a.milk_type = :milkType AND a.dairy_id = :dairyId
    """, nativeQuery = true)
    List<Object[]> findRatesByDairyId(@Param("milkType") String milkType, @Param("dairyId") Integer dairyId);

    Optional<DairyRateStartingAmount> findByDairyRegistrationAndMilkType(DairyRegistration dairyReg, String milkType);


//    <DairyRateStartingAmount> findByDairyRegistration(DairyRegistration dairyReg);
}
