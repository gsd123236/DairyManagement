package com.dairy.Services;


import com.dairy.Entity.MilkTypeMaster;
import com.dairy.Repository.MilkTypeMasterRepository;
import jakarta.persistence.PrePersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MilkTypeMasterService {
    @Autowired
    private MilkTypeMasterRepository milkTypeMasterRepository;
    public List<MilkTypeMaster> getAllMilkType(){
        return milkTypeMasterRepository.getActiveData();
    }
    public List<MilkTypeMaster>getActiveDeactive(){return milkTypeMasterRepository.getActiveDeactive();}
    public Optional<MilkTypeMaster> getById(int id){
        return  milkTypeMasterRepository.findById(id);
    }
    public MilkTypeMaster addMilkType(MilkTypeMaster milkType) {
        List<MilkTypeMaster>existingMilkType=milkTypeMasterRepository.findByName(milkType.getAnimalName());
        boolean anyNotDelted=existingMilkType.stream().anyMatch(m->!m.isDelete());
        if(anyNotDelted){
            throw new RuntimeException("PaymentType with the same name already exists and is not deleted");
        }
        milkType.setActive(true);
        milkType.setLastUpdate(LocalDateTime.now());
        milkType.setCreateDate(LocalDateTime.now());
        return  milkTypeMasterRepository.save(milkType);
    }
    public MilkTypeMaster deactivateMilkType(int id) {
        Optional<MilkTypeMaster> optionalState = milkTypeMasterRepository.findById(id);
        if (optionalState.isPresent()) {
            MilkTypeMaster  animal= optionalState.get();
            animal.setActive(false);
            animal.setDelete(false);
            animal.setDeactive(true);
            return  milkTypeMasterRepository.save(animal);
        } else {
            throw new RuntimeException("Milk with ID " + id + " not found");
        }
    }
    public MilkTypeMaster deletedMilkType(int id) {
        Optional<MilkTypeMaster> optionalState = milkTypeMasterRepository.findById(id);
        if (optionalState.isPresent()) {
            MilkTypeMaster  animal= optionalState.get();
            animal.setActive(false);
            animal.setDelete(true);
            animal.setDeactive(false);
            return  milkTypeMasterRepository.save(animal);
        } else {
            throw new RuntimeException("Milk with ID " + id + " not found");
        }
    }
    public MilkTypeMaster activateMilkType(int id){
        Optional<MilkTypeMaster> milkType= milkTypeMasterRepository.findById(id);
        if(milkType.isPresent()){
            MilkTypeMaster s=milkType.get();
            milkType.get().setActive(true);
            milkType.get().setDelete(false);
            milkType.get().setDeactive(false);
            return milkTypeMasterRepository.save(s);
        }
        else {
            throw new RuntimeException("PaymentPeriod with Id"+id+"not found");
        }
    }
    @PrePersist
    public MilkTypeMaster updateMilkType(int id, String newAnimalTypeName ){
        Optional<MilkTypeMaster> milkType= milkTypeMasterRepository.findById(id);
        if(milkType.isPresent()) {
            MilkTypeMaster s = milkType.get();
            s.setAnimalName(newAnimalTypeName);
            s.setLastUpdate(LocalDateTime.now());
            return milkTypeMasterRepository.save(s);
        }
        else{
            throw  new RuntimeException("MilkType with Id "+id+" not found");
        }
    }
}
