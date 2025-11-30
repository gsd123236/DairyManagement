package com.dairy.Repository;

import com.dairy.Entity.DairySubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DairySubscriptionRepository extends JpaRepository<DairySubscription,Integer> {
    @Query(value = "SELECT * FROM  dairy_subscription WHERE dairy_id= :dairy_id AND status=1",nativeQuery = true)
    DairySubscription getByDairyId(@Param("dairy_id")int dairy_id);
}
