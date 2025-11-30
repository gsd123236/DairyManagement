package com.dairy.Repository;

import com.dairy.Entity.DairyRegistration;
import com.dairy.Entity.PartyRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface PartyRegistrationRepository extends JpaRepository<PartyRegistration,Integer> {
    boolean existsByMobileAndDairyRegistration(String mobile, DairyRegistration dairyRegistration);

    @Query(value = "SELECT * FROM party_registration WHERE dairy_id = :dairy_id AND inactive = 1", nativeQuery = true)
    List<PartyRegistration> getAllInactive(@Param("dairy_id") int dairy_id);

    @Query(value = "SELECT * FROM party_registration WHERE dairy_id = :dairy_id AND active = 1", nativeQuery = true)
    List<PartyRegistration> getAllActive(@Param("dairy_id") int dairy_id);

    @Query(value = "SELECT * FROM party_registration WHERE dairy_id = :dairy_id", nativeQuery = true)
    List<PartyRegistration> getAllByDairyId(@Param("dairy_id") int dairy_id);
}
