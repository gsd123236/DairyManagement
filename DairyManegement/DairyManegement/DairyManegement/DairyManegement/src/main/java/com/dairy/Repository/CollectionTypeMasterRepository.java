package com.dairy.Repository;

import com.dairy.Entity.CollectionTypeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionTypeMasterRepository extends JpaRepository<CollectionTypeMaster,Integer> {
    @Query(value = "SELECT * FROM collection WHERE collection_name = :collection_name", nativeQuery = true)
    List<CollectionTypeMaster> findByName(@Param("collection_name") String collection_name);
    @Query(value = "SELECT * FROM collection WHERE is_delete=0",nativeQuery = true)
    List< CollectionTypeMaster> activeDeactive();
    @Query(value = "SELECT * FROM collection WHERE is_active=1",nativeQuery = true)
    List<CollectionTypeMaster>getActiveData();
}
