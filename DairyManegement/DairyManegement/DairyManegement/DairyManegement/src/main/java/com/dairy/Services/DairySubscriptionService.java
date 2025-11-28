package com.dairy.Services;

import com.dairy.Dto.DairySubscriptionResponseDTO;
import com.dairy.Entity.DairyRegistration;
import com.dairy.Entity.DairySubscription;
import com.dairy.Entity.FarmerRegistration;
import com.dairy.Entity.SubscriptionType;
import com.dairy.Repository.DairyRepository;
import com.dairy.Repository.DairySubscriptionRepository;
import com.dairy.Repository.SubscriptionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DairySubscriptionService {
    @Autowired
    private DairySubscriptionRepository repository;
    @Autowired
    private SubscriptionTypeRepository subscriptionTypeRepository;
    @Autowired
    private DairyRepository dairyRepository;
    public DairySubscription add(  DairySubscription dairySubscription,int dairyId,int subscriptionId){
        DairyRegistration dairy=dairyRepository.findById(dairyId)
                .orElseThrow(()->new RuntimeException("Dairy Not Found"));
        SubscriptionType subscriptionType=subscriptionTypeRepository.findById(subscriptionId)
                .orElseThrow(()->new RuntimeException("Subscription plan not found"));
        dairySubscription.setDairyRegistration(dairy);
        dairySubscription.setStatus(true);
        dairySubscription.setSubscriptionType(subscriptionType);
        dairySubscription.setStartDate(LocalDate.now());
        LocalDate endDate = dairySubscription.getStartDate().plusDays(subscriptionType.getDuration());
        dairySubscription.setEndDate(endDate);
//        if (endDate.isEqual(LocalDate.now())) {
//            dairySubscription.setStatus(false);
//        } else {
//            dairySubscription.setStatus(true);
//        }
        return repository.save(dairySubscription);
    }
    public DairySubscription updateSubscriptionStatus(int dairyId) {
        DairySubscription dairy=repository.getByDairyId(dairyId);
        if (dairy.getEndDate().isEqual(LocalDate.now())) {
            dairy.setStatus(false);
        } else {
            dairy.setStatus(true);
        }
        return repository.save(dairy);
    }
    public List<DairySubscriptionResponseDTO> getAllSubscriptions() {
        List<DairySubscription> subscriptions = repository.findAll();

        return subscriptions.stream().map(sub -> {
            DairySubscriptionResponseDTO dto = new DairySubscriptionResponseDTO();
            dto.setId(sub.getId());
            dto.setDairyName(sub.getDairyRegistration().getDairyName());// Assuming DairyRegistration has getDairyName()
            dto.setFirstName(sub.getDairyRegistration().getFirstName());
            dto.setLastName(sub.getDairyRegistration().getLastName());
            dto.setDairyNumber(sub.getDairyRegistration().getMobile());
            dto.setDairyAddress(sub.getDairyRegistration().getVillage());
            dto.setSubscriptionPlanName(sub.getSubscriptionType().getPlanName()); // Assuming SubscriptionType has getPlanName()
            dto.setAmount(sub.getSubscriptionType().getAmount());
            dto.setStartDate(sub.getStartDate());
            dto.setEndDate(sub.getEndDate());
            dto.setStatus(sub.isStatus());
            return dto;
        }).collect(Collectors.toList());
    }
    public DairySubscription createNewDairySubscription(DairySubscription dairySubscription, int id, int subscriptionId) {
        DairySubscription existingDairy = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subscription not found with ID: " + id));

        SubscriptionType subscriptionType = subscriptionTypeRepository.findById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("Subscription plan not found"));
        DairySubscription newDairySubscription = new DairySubscription();
        newDairySubscription.setSubscriptionType(subscriptionType);
        newDairySubscription.setDairyRegistration(existingDairy.getDairyRegistration());
        newDairySubscription.setStatus(true);
        newDairySubscription.setStartDate(LocalDate.now());
        LocalDate endDate = newDairySubscription.getStartDate().plusDays(subscriptionType.getDuration());
        newDairySubscription.setEndDate(endDate);

        return repository.save(newDairySubscription);
    }

    public List<DairySubscription>getAll(){

        return repository.findAll();
    }
}
