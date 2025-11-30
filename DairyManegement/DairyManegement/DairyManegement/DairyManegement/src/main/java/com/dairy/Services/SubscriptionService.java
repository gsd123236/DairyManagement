package com.dairy.Services;

import com.dairy.Entity.SubscriptionType;
import com.dairy.Repository.SubscriptionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {
    @Autowired
    private SubscriptionTypeRepository repository;
    public SubscriptionType addAll(SubscriptionType subScriptionType){
        subScriptionType.setActive(true);
        subScriptionType.setCreateDate(LocalDateTime.now());
        subScriptionType.setUpdateDate(LocalDateTime.now());
        return repository.save(subScriptionType);
    }
    public List<SubscriptionType> getAll(){
        return  repository.findAll();
    }
    public SubscriptionType updateSubscription(int id,SubscriptionType subScriptionType){
        SubscriptionType exisitingSubscriptionType= repository.
                findById(id).orElseThrow(() -> new RuntimeException("SubscriptionType not found with ID: " + id));
        if(subScriptionType.getAmount()!=0){
            exisitingSubscriptionType.setAmount(subScriptionType.getAmount());
        }
        if(subScriptionType.getPlanName() !=null){
            exisitingSubscriptionType.setPlanName(subScriptionType.getPlanName());
        }
        if(subScriptionType.getDuration() !=0){
            exisitingSubscriptionType.setDuration(subScriptionType.getDuration());
        }
        return repository.save(exisitingSubscriptionType);
    }
    public SubscriptionType isActive(int id,SubscriptionType subScriptionType){
        SubscriptionType exisitingSubscriptionType= repository.
                findById(id).orElseThrow(() -> new RuntimeException("SubscriptionType not found with ID: " + id));
        exisitingSubscriptionType.setActive(true);
        exisitingSubscriptionType.setInactive(false);
        exisitingSubscriptionType.setIsdelete(false);
        return repository.save(exisitingSubscriptionType);
    }
    public SubscriptionType isInActive(int id,SubscriptionType subScriptionType){
        SubscriptionType exisitingSubscriptionType= repository.
                findById(id).orElseThrow(() -> new RuntimeException("SubscriptionType not found with ID: " + id));
        exisitingSubscriptionType.setActive(false);
        exisitingSubscriptionType.setInactive(true);
        exisitingSubscriptionType.setIsdelete(false);
        return repository.save(exisitingSubscriptionType);
    }
    public SubscriptionType isDeleted(int id,SubscriptionType subScriptionType){
        SubscriptionType exisitingSubscriptionType= repository.
                findById(id).orElseThrow(() -> new RuntimeException("SubscriptionType not found with ID: " + id));
        exisitingSubscriptionType.setActive(false);
        exisitingSubscriptionType.setInactive(false);
        exisitingSubscriptionType.setIsdelete(true);
        return repository.save(exisitingSubscriptionType);
}



}
