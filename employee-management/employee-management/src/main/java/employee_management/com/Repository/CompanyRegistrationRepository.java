package employee_management.com.Repository;

import employee_management.com.Entity.CompanyRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.JpqlQueryBuilder;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRegistrationRepository extends JpaRepository<CompanyRegistration,Integer> {

//@Query(value = "select * from companyRegistration where id:c_id",nativeQuery = true)
//    List<CompanyRegistration>getAllCompanyData(@Param("c_id") Integer c_id);
    @Query(value = "select * from company_Registration where c_id = :c_id", nativeQuery = true)
    List<CompanyRegistration> getAllCompanyData(@Param("c_id") Integer c_id);

//    @Query(value="select c_id,company_Name,owner_name,phone_Number from " +
//            "company_Registration where c_id=:c_id",nativeQuery = true)
//    List<CompanyRegistration> getCompanyData(@Param("c_id") Integer c_id);
@Query(
        value = "select c_id, company_Name, owner_name, phone_Number " +
                "from company_registration where c_id = :c_id",
        nativeQuery = true
)
List<Object[]> getCompanyData(@Param("c_id") Integer c_id);


//    Optional<CompanyRegistration> findByPhoneNumber(String phone_Number);
    @Query("SELECT c FROM CompanyRegistration c WHERE c.phone_Number = :phone")
    Optional<CompanyRegistration> findByPhoneNumber(@Param("phone") String phone_Number);


}
