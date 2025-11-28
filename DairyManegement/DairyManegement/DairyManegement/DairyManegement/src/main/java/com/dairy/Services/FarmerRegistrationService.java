package com.dairy.Services;
import com.dairy.Entity.*;
import com.dairy.Repository.FarmerRegistrationRepository;
import com.dairy.Repository.DairyRepository;
import com.dairy.Repository.MilkTypeMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FarmerRegistrationService {
    @Autowired
    private FarmerRegistrationRepository farmerRegistrationRepository;
    @Autowired
    private MilkTypeMasterRepository milkTypeMasterRepository;
    @Autowired
    private DairyRepository dairyRepository;
    public FarmerRegistration saveFarmer(FarmerRegistration farmer, int dairyId) {
        DairyRegistration dairy = dairyRepository.findById(dairyId)
                .orElseThrow(() -> new RuntimeException("Dairy not found"));
        if (farmerRegistrationRepository.existsByMobileAndDairyRegistration(farmer.getMobile(), dairy)) {
            throw new RuntimeException("Mobile number already exists for this dairy");
        }
        if (farmerRegistrationRepository.existsByCodeAndDairyRegistration(String.valueOf(Integer.parseInt(farmer.getCode())), dairy)) {
            throw new RuntimeException("Farmer code already exists for this dairy");
        }
        farmer.setDairyRegistration(dairy);
        farmer.setCreateDate(LocalDateTime.now());
        farmer.setLastUpdate(LocalDateTime.now());
        return farmerRegistrationRepository.save(farmer);
    }
    public List<FarmerRegistration> getAllFarmerByDairyId(int dairyId){
        return farmerRegistrationRepository.findByForeignKey(dairyId);
    }
    public List<FarmerRegistration>getAllActive(int dariryId){
        return farmerRegistrationRepository.getAllActive(dariryId);
    }
    public List<FarmerRegistration>getAllInactive(int dariryId){
        return farmerRegistrationRepository.getAllInactive(dariryId);
    }

//    public List<FarmerRegistration>getAllAdminFarmer(){
//        return farmerRegistrationRepository.getAllFarmerAdmin();
//    }


    public FarmerRegistration activateFarmer(int id){
        Optional<FarmerRegistration> farmer= farmerRegistrationRepository.findById(id);
        if(farmer.isPresent()){
            FarmerRegistration s=farmer.get();
            farmer.get().setActive(true);
            farmer.get().setDelete(false);
            return farmerRegistrationRepository.save(s);
        }
        else {
            throw new RuntimeException("Farmer with Id"+id+"not found");
        }
    }
    public FarmerRegistration deactivateCollection(int id) {
        Optional<FarmerRegistration> farmer= farmerRegistrationRepository.findById(id);
        if(farmer.isPresent()){
            FarmerRegistration s=farmer.get();
            farmer.get().setActive(false);
            farmer.get().setDelete(true);
            return farmerRegistrationRepository.save(s);
        }
        else {
            throw new RuntimeException("Farmer with Id"+id+"not found");
        }
    }
    public FarmerRegistration updateFarmer(int id, FarmerRegistration updatedFarmer) {
        FarmerRegistration existingFarmer = farmerRegistrationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Farmer not found with ID: " + id));
        if (updatedFarmer.getFirstName() != null) {
            existingFarmer.setFirstName(updatedFarmer.getFirstName());
        }
        if (updatedFarmer.getLastName() != null) {
            existingFarmer.setLastName(updatedFarmer.getLastName());
        }
        if (updatedFarmer.getMobile() != null) {
            existingFarmer.setMobile(updatedFarmer.getMobile());
        }
        if (updatedFarmer.getMilkType() != null && !updatedFarmer.getMilkType().isEmpty()) {
            existingFarmer.setMilkType(updatedFarmer.getMilkType());
            // return farmerRegistrationRepository.save(existingFarmer);
        }
        return farmerRegistrationRepository.save(existingFarmer);
    }

    public List<FarmerRegistration> getAllFarmer(){
        return farmerRegistrationRepository.findAll();
    }
}
