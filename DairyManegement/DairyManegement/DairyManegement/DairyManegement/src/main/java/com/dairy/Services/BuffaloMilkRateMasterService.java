package com.dairy.Services;

import com.dairy.Entity.BuffaloMilkRateMaster;
import com.dairy.Entity.CowMilkRateMaster;
import com.dairy.Repository.BuffaloMilkRateMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BuffaloMilkRateMasterService {
    @Autowired
    private BuffaloMilkRateMasterRepository repository;
    public BuffaloMilkRateMaster add(BuffaloMilkRateMaster buffaloMilkRateMaster){
        List<BuffaloMilkRateMaster>existingCowMilkType=repository.getByName(buffaloMilkRateMaster.getRateType());
        boolean anyNotDelted=existingCowMilkType.stream().anyMatch(m->!m.isDelete());
        if(anyNotDelted){
            throw new RuntimeException("BuffaloMilkType with the same name already exists and is not deleted");
        }
        buffaloMilkRateMaster.setActive(true);
        buffaloMilkRateMaster.setUpdateDate(LocalDateTime.now());
        buffaloMilkRateMaster.setCreateDate(LocalDateTime.now());
        return repository.save(buffaloMilkRateMaster);
    }
    public List<BuffaloMilkRateMaster>getAll(){
        return repository.getActive();
    }
    public List<BuffaloMilkRateMaster>getActiveDeactive(){
        return repository.getActiveDeactive();
    }
    public Optional<BuffaloMilkRateMaster> getById(int id){
        Optional<BuffaloMilkRateMaster> buffalo=repository.findById(id);
        if(buffalo.isPresent()) {
            return repository.findById(id);
        }
        else{
            throw  new RuntimeException("Not found");
        }
    }
    public BuffaloMilkRateMaster isDeleted(int id){
        Optional<BuffaloMilkRateMaster> buffalo=repository.findById(id);
        if(buffalo.isPresent()){
            BuffaloMilkRateMaster s=buffalo.get();
            s.setActive(false);
            s.setDelete(true);
            s.setDeactive(false);
            return repository.save(s);
        }
        else{
            throw  new RuntimeException("Not Found");
        }
    }
    public BuffaloMilkRateMaster isDeactive(int id){
        Optional<BuffaloMilkRateMaster> buffalo=repository.findById(id);
        if(buffalo.isPresent()){
            BuffaloMilkRateMaster s=buffalo.get();
            s.setActive(false);
            s.setDelete(false);
            s.setDeactive(true);
            return repository.save(s);
        }
        else{
            throw  new RuntimeException("Not Found");
        }
    }
    public  BuffaloMilkRateMaster isActive(int id){
        Optional<BuffaloMilkRateMaster> buffalo=repository.findById(id);
        if(buffalo.isPresent()){
            BuffaloMilkRateMaster s=buffalo.get();
            s.setDelete(false);
            s.setActive(true);
            return repository.save(s);
        }
        else{
            throw new RuntimeException("Not Found");
        }
    }
    public BuffaloMilkRateMaster update(int id,String rateType){
        Optional<BuffaloMilkRateMaster>buffalo=repository.findById(id);
        if(buffalo.isPresent()){
            BuffaloMilkRateMaster s=buffalo.get();
            s.setRateType(rateType);
            s.setUpdateDate(LocalDateTime.now());
            return repository.save(s);
        }
        else{
            throw new RuntimeException("Not Fount");
        }
    }
}
