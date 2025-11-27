package employee_management.com.Controller;

import employee_management.com.Dto.ResponseDto;
import employee_management.com.Entity.CompanyRegistration;
import employee_management.com.Services.CompanyRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/Company")
public class CompanyRegistrationController {
    @Autowired
    private CompanyRegistrationService companyRegistrationService;

    @PostMapping("/saveData")
    public ResponseDto companyRegistration(@RequestBody CompanyRegistration company){
        ResponseDto response=new ResponseDto();
        try{
          response.data=companyRegistrationService.saveData(company);
          response.message="Company registration success fully....";
          response.status=200L;
          response.success=true;
        }catch (Exception e){
            response.message="Company registration filed..";
            response.status=500L;
            response.success=true;
        }
        return response;
    }

    @GetMapping("/getData")
    public ResponseDto getRegistration(@RequestBody CompanyRegistration company){
        ResponseDto response=new ResponseDto();
        try{
            response.data=companyRegistrationService.getData();
            response.message="Get company data success fully....";
            response.status=200L;
            response.success=true;
        }catch (Exception e){
            response.message="filed..";
            response.status=500L;
            response.success=true;
        }
        return response;
    }

    @PutMapping ("/updateData/{id}")
    public ResponseDto updateData(@PathVariable Integer id,@RequestBody CompanyRegistration registration){
        ResponseDto response=new ResponseDto();
        try{
            response.data=companyRegistrationService.updateData(id,registration);
            response.message=" Update company data success fully....";
            response.status=200L;
            response.success=true;
        }catch (Exception e){
            response.message="filed..";
            response.status=500L;
            response.success=true;
        }
        return response;
    }

    @DeleteMapping ("/deleteData/{id}")
    public ResponseDto deleteData(@PathVariable Integer id){
        ResponseDto response=new ResponseDto();
        try{
            companyRegistrationService.deleteData(id);
            response.message=" Delete data success fully....";
            response.status=200L;
            response.success=true;
        }catch (Exception e){
            response.message="filed..";
            response.status=500L;
            response.success=true;
        }
        return response;
    }

    @GetMapping("/getDataQuery/{c_id}")
    public ResponseDto getRegistrationData(@PathVariable Integer c_id){
        ResponseDto response=new ResponseDto();
        try{
            response.data=companyRegistrationService.getDataQuery(c_id);
            System.out.println("THis dta"+c_id);
            System.out.println("THis dta"+response);
            response.message="Get company data success fully....";
            response.status=200L;
            response.success=true;
        }catch (Exception e){
            response.message="filed..";
            response.status=500L;
            response.success=false;
        }
        return response;

    }
    @GetMapping("/getDataUser/{c_id}")
    public ResponseDto getUserData(@PathVariable Integer c_id){
        ResponseDto response=new ResponseDto();
        try{
            response.data=companyRegistrationService.getUserInfo(c_id);
            System.out.println("THis dta"+c_id);
            System.out.println("THis dta"+response);
            response.message="Get company data success fully....";
            response.status=200L;
            response.success=true;
        }catch (Exception e){
            response.message="filed..";
            response.status=500L;
            response.success=false;
        }
        return response;

    }

    @PostMapping("/getLoginUser")
    public ResponseDto getLoginData(@RequestParam String phoneNumber,String password){
        ResponseDto response=new ResponseDto();
        try{
            Optional<CompanyRegistration> loginUser =
                    companyRegistrationService.loginUser(phoneNumber, password);

            if (loginUser.isPresent()) {
                response.data = loginUser.get();
                response.message = "Company Login Successfully";
                response.status = 200L;
                response.success = true;
            } else {
                response.data = null;
                response.message = "Invalid Username or Password";
                response.status = 401L;
                response.success = false;
            }

        }catch (Exception e){
            response.message="filed.....";
            response.status=500L;
            response.success=false;
        }
        return response;

    }

}
