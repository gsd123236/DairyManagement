package employee_management.com.Repository;

import employee_management.com.Entity.EmployeeRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRegistrationRepository extends JpaRepository<EmployeeRegistration,Integer> {
}
