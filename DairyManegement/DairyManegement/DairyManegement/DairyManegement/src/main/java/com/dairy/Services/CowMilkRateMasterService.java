package com.dairy.Services;

import com.dairy.Entity.CowMilkRateMaster;
import com.dairy.Entity.MilkTypeMaster;
import com.dairy.Repository.CowMilkRateMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CowMilkRateMasterService {
    @Autowired
    private CowMilkRateMasterRepository repository;
    public CowMilkRateMaster add(CowMilkRateMaster cowMilkRateMaster){
        List<CowMilkRateMaster>existingCowMilkType=repository.getByName(cowMilkRateMaster.getRateType());
        boolean anyNotDelted=existingCowMilkType.stream().anyMatch(m->!m.isDelete());
        if(anyNotDelted){
            throw new RuntimeException("CowMilkType with the same name already exists and is not deleted");
        }
        cowMilkRateMaster.setActive(true);
        cowMilkRateMaster.setUpdateDate(LocalDateTime.now());
        cowMilkRateMaster.setCreateDate(LocalDateTime.now());
        return repository.save(cowMilkRateMaster);
    }
    public List<CowMilkRateMaster>getActiveDeactive(){return  repository.getAllActiveDeactive();}
    public List<CowMilkRateMaster>getAll(){
        return repository.getActiveData();
    }
    public Optional<CowMilkRateMaster> getById(int id){
        Optional<CowMilkRateMaster>cow=repository.findById(id);
        if(cow.isPresent()){
            return repository.findById(id);
        }
        else{
            throw  new RuntimeException("Not Found");
        }
    }
    public CowMilkRateMaster isDeactive(int id){
        Optional<CowMilkRateMaster>cow=repository.findById(id);
        if(cow.isPresent()){
            CowMilkRateMaster s=cow.get();
            s.setActive(false);
            s.setDelete(false);
            s.isDeactive(true);
            return repository.save(s);
        }
        else{
            throw new RuntimeException("Not Found");
        }
    }
    public CowMilkRateMaster isDeleted(int id){
        Optional<CowMilkRateMaster>cow=repository.findById(id);
        if(cow.isPresent()){
            CowMilkRateMaster s=cow.get();
            s.setDelete(true);
            s.setActive(false);
            s.isDeactive(false);
            return repository.save(s);
        }
        else{
            throw  new RuntimeException("Not Found");
        }
    }
    public CowMilkRateMaster isActive(int id){
        Optional<CowMilkRateMaster>cow=repository.findById(id);
        if(cow.isPresent()){
            CowMilkRateMaster s=cow.get();
            s.setDelete(false);
            s.setActive(true);
            s.isDeactive(false);
            return repository.save(s);
        }
        else{
            throw  new RuntimeException("Not Found");
        }
    }
    public CowMilkRateMaster updateCowRate(int id,String rateType){
        Optional<CowMilkRateMaster>cow=repository.findById(id);
        if(cow.isPresent()){
            CowMilkRateMaster s=cow.get();
            s.setRateType(rateType);
            s.setUpdateDate(LocalDateTime.now());
            return repository.save(s);
        }
        else{
            throw  new RuntimeException("Not Found");
        }
    }
}
