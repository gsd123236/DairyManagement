package com.dairy.Services;

import com.dairy.Entity.DairyRegistration;
import com.dairy.Entity.FarmerRegistration;
import com.dairy.Entity.PartyRegistration;
import com.dairy.Repository.DairyRepository;
import com.dairy.Repository.PartyRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PartyRegistrationService {
    @Autowired
    private PartyRegistrationRepository repository;
    @Autowired
    private DairyRepository dairyRepository ;
    public PartyRegistration addParty(PartyRegistration partyRegistration,int dairyId){
        DairyRegistration dairy = dairyRepository.findById(dairyId)
                .orElseThrow(() -> new RuntimeException("Dairy not found"));
        if (repository.existsByMobileAndDairyRegistration(partyRegistration.getMobile(), dairy)) {
            throw new RuntimeException("Mobile number already exists for this dairy");
        }
        partyRegistration.setDairyRegistration(dairy);
        partyRegistration.setCreateDate(LocalDateTime.now());
        partyRegistration.setUpdateDate(LocalDateTime.now());
        partyRegistration.setActive(true);
        return repository.save(partyRegistration);
    }
    public List<PartyRegistration> getAll(){
        return repository.findAll();
    }
    public List<PartyRegistration>getAllActive(int dairy_id){
        return  repository.getAllActive(dairy_id);
    }
    public  List<PartyRegistration>getAllInactive(int dairy_id){
        return repository.getAllInactive(dairy_id);
    }
    public List<PartyRegistration>getAllByDairyId(int dairy_id){
        return repository.getAllByDairyId(dairy_id);
    }
    public PartyRegistration updateParty(int id, PartyRegistration updatedParty) {
        PartyRegistration existingParty = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Party not found with ID: " + id));
        if (updatedParty.getFirstName() != null) {
            existingParty.setFirstName(updatedParty.getFirstName());
        }
        if (updatedParty.getLastName() != null) {
            existingParty.setLastName(updatedParty.getLastName());
        }
        if (updatedParty.getMobile() != null) {
            existingParty.setMobile(updatedParty.getMobile());
        }
        if(updatedParty.getVillage()!=null){
            existingParty.setVillage(updatedParty.getVillage());
        }
        if(updatedParty.getDistrict()!=null){
            existingParty.setDistrict(updatedParty.getDistrict());
        }
        return repository.save(existingParty);
    }
    public PartyRegistration activateParty(int id){
        Optional<PartyRegistration> party= repository.findById(id);
        if(party.isPresent()){
            PartyRegistration s=party.get();
            party.get().setActive(true);
            party.get().setInactive(false);
            return repository.save(s);
        }
        else {
            throw new RuntimeException("Party with Id"+id+"not found");
        }
    }
    public PartyRegistration deactivateParty(int id) {
        Optional<PartyRegistration> party= repository.findById(id);
        if(party.isPresent()){
            PartyRegistration s=party.get();
            party.get().setActive(false);
            party.get().setInactive(true);
            return repository.save(s);
        }
        else {
            throw new RuntimeException("Party with Id"+id+"not found");
        }
    }

}
