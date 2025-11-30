package employee_management.com.Controller;

import employee_management.com.Dto.ResponseDto;
import employee_management.com.Entity.EmployeeRegistration;
import employee_management.com.Services.EmployeeRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employee")
public class EmployeeRegistrationController {
    @Autowired
    private EmployeeRegistrationService employeeRegistrationService;
    @PostMapping("addData")
    public ResponseDto addEmployee(@RequestBody EmployeeRegistration employee){
        ResponseDto response=new ResponseDto();
        try {
            response.data=employeeRegistrationService.saveData(employee);
            response.message="Employee data save successfully...";
            response.success=true;
            response.status=200L;
        }catch (Exception e){
            response.message="Filed...";
            response.success=false;
            response.status=500L;
        }
        return response;
    }

    @GetMapping("getData")
    public ResponseDto getEmployee(@RequestBody EmployeeRegistration employee){
        ResponseDto response=new ResponseDto();
        try {
            response.data=employeeRegistrationService.getData(employee);
            response.message="Get all data successFully...";
            response.success=true;
            response.status=200L;
        }catch (Exception e){
            response.message="Filed..";
            response.success=false;
            response.status=500L;
        }
        return response;
    }
}
