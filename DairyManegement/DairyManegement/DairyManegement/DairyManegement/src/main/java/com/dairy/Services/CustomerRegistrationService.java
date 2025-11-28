package com.dairy.Services;

import com.dairy.Dto.CustomerDTO;
import com.dairy.Dto.FarmerDTO;
import com.dairy.Dto.MilkTypeDTO;
import com.dairy.Entity.*;
import com.dairy.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerRegistrationService {
    @Autowired
    private CustomerRegistrationRepository repository;
    @Autowired
    private MilkTypeMasterRepository milkTypeMasterRepository;
    @Autowired
    private BuffaloMilkRateMasterRepository buffaloMilkRateMasterRepository;
    @Autowired
    private CowMilkRateMasterRepository cowMilkRateMasterRepository;
    @Autowired
    private DairyRepository dairyRepository;
    public CustomerRegistration add(CustomerRegistration customer, int dairyId){
        DairyRegistration dairy = dairyRepository.findById(dairyId)
                .orElseThrow(() -> new RuntimeException("Dairy not found"));
        if (repository.existsByMobileAndDairyRegistration(customer.getMobile(), dairy)) {
            throw new RuntimeException("Mobile number already exists for this dairy");
        }
        if (repository.existsByCodeAndDairyRegistration(customer.getCode(), dairy)) {
            throw new RuntimeException("Customer code already exists for this dairy");
        }
        // List<Integer> dairyMilkTypes = dairy.getMilkTypeList();

//        if (!dairyMilkTypes.containsAll(farmerMilkTypes)) {
//            throw new IllegalArgumentException("Invalid milk type! This dairy only allows " + dairyMilkTypes);
//        }
        customer.setDairyRegistration(dairy);
        customer.setUpdateDate(LocalDateTime.now());
        customer.setCreateDate(LocalDateTime.now());
        return repository.save(customer);
    }
//    public CustomerDTO getCustomerDTO(int customerId) {
//        CustomerRegistration customer = repository.findById(customerId)
//                .orElseThrow(() -> new RuntimeException("Customer not found"));
//        List<MilkTypeDTO> milkTypeNames = Optional.ofNullable(customer.getMilkType())
//                .orElse(Collections.emptyList())
//                .stream()
//                .map(milkTypeId -> milkTypeMasterRepository.findById(milkTypeId)
//                        .map(milkType -> new MilkTypeDTO(milkType.getId(), milkType.getAnimalName()))
//                        .orElse(new MilkTypeDTO(-1, "Unknown")))
//                .collect(Collectors.toList());
//        String buffaloMilkRateMasterName = buffaloMilkRateMasterRepository.findById(Integer.valueOf(customer.getBuffaloMilkRateMasterId()))
//                .map(BuffaloMilkRateMaster::getRateType).orElse(null);
//        String cowMilkRateMasterName = cowMilkRateMasterRepository.findById(Integer.valueOf(customer.getCowMilkRateMasterId()))
//                .map(CowMilkRateMaster::getRateType).orElse(null);
//        return new CustomerDTO(customer, milkTypeNames, buffaloMilkRateMasterName, cowMilkRateMasterName);
//    }
//    public List<CustomerDTO> getCustomerByDairyid(int dairyId) {
//        List<CustomerRegistration> customers = repository.findByForeignKey(dairyId);
//
//        return customers.stream()
//                .map(customer -> getCustomerDTO(customer.getId()))
//                .collect(Collectors.toList());
//    }

    public List<CustomerRegistration> getAllCustomerByDairyId(int dairyId){
        return repository.findByForeignKey(dairyId);
    }
    public List<CustomerRegistration>getAllActive(int dariryId){
        return repository.getAllActive(dariryId);
    }
    public List<CustomerRegistration>getAllInactive(int dariryId){
        return repository.getAllInactive(dariryId);
    }
    public CustomerRegistration activateCustomer(int id){
        Optional<CustomerRegistration> customer= repository.findById(id);
        if(customer.isPresent()){
            CustomerRegistration s=customer.get();
            customer.get().setActive(true);
            customer.get().setDeactive(false);
            return repository.save(s);
        }
        else {
            throw new RuntimeException("Customer with Id"+id+"not found");
        }
    }
    public CustomerRegistration InactivateCustomer(int id){
        Optional<CustomerRegistration> customer= repository.findById(id);
        if(customer.isPresent()){
            CustomerRegistration s=customer.get();
            customer.get().setActive(false);
            customer.get().setDeactive(true);
            return repository.save(s);
        }
        else {
            throw new RuntimeException("Customer with Id"+id+"not found");
        }
    }
    public CustomerRegistration updateCustomer(int id, CustomerRegistration customer) {
        CustomerRegistration existingCustomer = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Farmer not found with ID: " + id));
        if (customer.getName()!= null) {
            existingCustomer.setName(customer.getName());
        }
        if(customer.getMobile()!=null){
            existingCustomer.setMobile(customer.getMobile());
        }

        if(customer.getCowMilkRateMasterId()!=null){
            existingCustomer.setCowMilkRateMasterId(customer.getCowMilkRateMasterId());
        }
        if(customer.getCowMilkRate()!=0){
            existingCustomer.setCowMilkRate(customer.getCowMilkRate());
        }
        if(customer.getBuffaloMilkRateMasterId()!=null){
            existingCustomer.setBuffaloMilkRateMasterId(customer.getBuffaloMilkRateMasterId());
        }
        if(customer.getBuffaloMilkRate()!=0){
            existingCustomer.setBuffaloMilkRate(customer.getBuffaloMilkRate());
        }
        if(customer.getAddress()!=null){
            existingCustomer.setAddress(customer.getAddress());
        }
        if(customer.getVillage()!=null){
            existingCustomer.setVillage(customer.getVillage());
        }
        if(customer.getTaluka()!=null){
            existingCustomer.setTaluka(customer.getTaluka());
        }
        if(customer.getDistrict()!=null){
            existingCustomer.setDistrict(customer.getDistrict());
        }
        if(customer.getDistrict()!=null){
            existingCustomer.setDistrict(customer.getDistrict());
        }
        if (customer.getMilkType() != null && !customer.getMilkType().isEmpty()) {
            existingCustomer.setMilkType(customer.getMilkType());
        }
        customer.setUpdateDate(LocalDateTime.now());
        return repository.save(existingCustomer);
    }
    public CustomerRegistration isActive(int id){
        Optional<CustomerRegistration>customer=repository.findById(id);
        if(customer.isPresent()){
            CustomerRegistration s=customer.get();
            s.setActive(true);
            s.setDeactive(false);
            return repository.save(s);
        }
        else{
            throw new RuntimeException("Not Found.");
        }
    }
    public  CustomerRegistration isDelete(int id){
        Optional<CustomerRegistration> customer=repository.findById(id);
        if(customer.isPresent()){
            CustomerRegistration s=customer.get();
            s.setDeactive(true);
            s.setActive(false);
            return  repository.save(s);
        }
        else{
            throw  new RuntimeException("Not Found");
        }

    }
}


