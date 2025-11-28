package com.dairy.Services;

import com.dairy.Entity.CollectionShiftMaster;
import com.dairy.Repository.CollectionShiftMasterRepository;
import jakarta.persistence.PrePersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CollectionShiftMasterService {
    @Autowired
    private CollectionShiftMasterRepository collectionShiftMasterRepository;
    public List<CollectionShiftMaster> getAllShift(){
        return collectionShiftMasterRepository.getAllData();
    }
    public Optional<CollectionShiftMaster> getById(int id){
        return  collectionShiftMasterRepository.findById(id);
    }
    public CollectionShiftMaster addShiftType(CollectionShiftMaster collectionShift) {
      List<CollectionShiftMaster>existingShiftType=collectionShiftMasterRepository.findByName(collectionShift.getShift());
        boolean anyNotDelted=existingShiftType.stream().anyMatch(c->!c.isDelete());
        if(anyNotDelted){
            throw new RuntimeException("CollectionShift with the same name already exists and is not deleted");
        }
        collectionShift.setLastUpdate(LocalDateTime.now());
        collectionShift.setActive(true);
        collectionShift.setCreateDate(LocalDateTime.now());
        return  collectionShiftMasterRepository.save(collectionShift);
    }
    public List<CollectionShiftMaster>getActiveDeactive(){
        return  collectionShiftMasterRepository.ActiveDeactive();
    }
    public CollectionShiftMaster activateCollectionShift(int id){
        Optional<CollectionShiftMaster> shift= collectionShiftMasterRepository.findById(id);
        if(shift.isPresent()){
            CollectionShiftMaster s=shift.get();
            shift.get().setActive(true);
            shift.get().setDelete(false);
            shift.get().setDeactive(false);
            return collectionShiftMasterRepository.save(s);
        }
        else {
            throw new RuntimeException("collectionShift with Id"+id+"not found");
        }
    }
    public   CollectionShiftMaster deactivateShiftType(int id) {
        Optional<CollectionShiftMaster> optionalState = collectionShiftMasterRepository.findById(id);
        if (optionalState.isPresent()) {
            CollectionShiftMaster shift= optionalState.get();
            shift.setActive(false);
            shift.setDelete(false);
            shift.setDeactive(true);
            return  collectionShiftMasterRepository.save(shift);
        } else {
            throw new RuntimeException("Shift with ID " + id + " not found");
        }
    }
    public   CollectionShiftMaster deletedShiftType(int id) {
        Optional<CollectionShiftMaster> optionalState = collectionShiftMasterRepository.findById(id);
        if (optionalState.isPresent()) {
            CollectionShiftMaster shift= optionalState.get();
            shift.setActive(false);
            shift.setDelete(true);
            shift.setDeactive(false);
            return  collectionShiftMasterRepository.save(shift);
        } else {
            throw new RuntimeException("Shift with ID " + id + " not found");
        }
    }
    @PrePersist
    public CollectionShiftMaster updateShift(int id, String newShiftName) {
        Optional<CollectionShiftMaster>  shift = collectionShiftMasterRepository.findById(id);
        if(shift.isPresent()) {
            CollectionShiftMaster s = shift.get();
            s.setShift(newShiftName);
            s.setLastUpdate(LocalDateTime.now());
            return collectionShiftMasterRepository.save(s);
        }
        else{
            throw  new RuntimeException("Shift with Id "+id+" not found");
        }
    }

}
