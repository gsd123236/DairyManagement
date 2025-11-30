package employee_management.com.Services;

import employee_management.com.Entity.EmployeeRegistration;
import employee_management.com.Repository.EmployeeRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeRegistrationService {
    @Autowired
    private EmployeeRegistrationRepository employeeRegistrationRepository;
    public EmployeeRegistration saveData(EmployeeRegistration employee){
        return employeeRegistrationRepository.save(employee);
    }

    public List<EmployeeRegistration> getData(EmployeeRegistration employee){
        return employeeRegistrationRepository.findAll();
    }

}
