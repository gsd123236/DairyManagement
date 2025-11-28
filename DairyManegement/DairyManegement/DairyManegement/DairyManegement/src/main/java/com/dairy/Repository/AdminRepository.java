package com.dairy.Repository;

import com.dairy.Entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    @Query(value = "SELECT COUNT(*) FROM admin WHERE username = :username AND password = :password", nativeQuery = true)
    long countAdmin(@Param("username") String username, @Param("password") String password);

}
