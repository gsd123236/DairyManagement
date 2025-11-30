package com.dairy.Repository;

import com.dairy.Entity.DairyNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DairyNoticeRepository extends JpaRepository<DairyNotice,Integer> {
@Query(value="SELECT *from dairy_notice WHERE d_id= :dId;",nativeQuery = true)
    List<DairyNotice>getNoticeByDairyId(@Param("dId")int dId);

}
