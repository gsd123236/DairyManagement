package employee_management.com.Services;

import employee_management.com.Dto.CompanyDto;
import employee_management.com.Entity.CompanyRegistration;
import employee_management.com.Repository.CompanyRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyRegistrationService {
    @Autowired
    private CompanyRegistrationRepository companyRegistrationRepository;

    public CompanyRegistration  saveData(CompanyRegistration registration){
        return companyRegistrationRepository.save(registration);
    }

    public List<CompanyRegistration> getData(){
        return companyRegistrationRepository.findAll();
    }


    public  CompanyRegistration updateData (Integer id,CompanyRegistration registration){
        CompanyRegistration oldData=companyRegistrationRepository.findById(id).
                orElseThrow(()->new RuntimeException("id not found"));
        oldData.setCompany_Name(registration.getCompany_Name());
        oldData.setCompany_Email(registration.getCompany_Email());
        oldData.setCompany_Address(registration.getCompany_Address());
        oldData.setOwner_name(registration.getOwner_name());
        oldData.setPhone_Number(registration.getPhone_Number());
        oldData.setPassword(registration.getPassword());
        return  companyRegistrationRepository.save(oldData);
    }
    public void deleteData(Integer id){
        companyRegistrationRepository.deleteById(id);
    }


    public List<CompanyRegistration> getDataQuery(Integer c_id){
       // System.out.println("this id...."+c_id);
        List<CompanyRegistration>list=companyRegistrationRepository.getAllCompanyData(c_id);
        if(list==null||list.isEmpty()){
            System.out.println("this id...."+c_id);
            return null;
        }
        return list;

    }
public List<CompanyDto> getUserInfo(Integer c_id) {
    List<Object[]> rows = companyRegistrationRepository.getCompanyData(c_id);
    List<CompanyDto> result = new ArrayList<>();

    for (Object[] r : rows) {
        CompanyDto dto = new CompanyDto(
                ((Number) r[0]).intValue(),
                (String) r[1],
                (String) r[2],
                (String) r[3]
        );
        result.add(dto);
    }

    return result;
}

// public Optional<CompanyRegistration> loginUser(String phone_Number, String password){
//        Optional<CompanyRegistration> listLogin=companyRegistrationRepository.findByPhoneNumber(phone_Number);
//        if(listLogin.isPresent()){
//            CompanyRegistration company=listLogin.get();
//            if(company.getPassword().equals(password)){
//                return listLogin;
//            }
//        }
//        return Optional.empty();
// }
public Optional<CompanyRegistration> loginUser(String phoneNumber, String password) {
    Optional<CompanyRegistration> user = companyRegistrationRepository.findByPhoneNumber(phoneNumber);

    if (user.isPresent()) {
        CompanyRegistration company = user.get();

        if (company.getPassword().trim().equals(password.trim())) {
            return user; // VALID LOGIN
        }
    }

    return Optional.empty(); // INVALID LOGIN
}


}
