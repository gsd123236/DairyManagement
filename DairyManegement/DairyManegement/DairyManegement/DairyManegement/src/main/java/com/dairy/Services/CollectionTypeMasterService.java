package com.dairy.Services;


import com.dairy.Entity.CollectionTypeMaster;
import com.dairy.Repository.CollectionTypeMasterRepository;
import jakarta.persistence.PrePersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CollectionTypeMasterService {
    @Autowired
    CollectionTypeMasterRepository collectionTypeMasterRepository;
    public List<CollectionTypeMaster> getActiveDeactive(){return collectionTypeMasterRepository.activeDeactive();}
    public List<CollectionTypeMaster> getAllCollection(){
        return collectionTypeMasterRepository.getActiveData();
    }
    public Optional<CollectionTypeMaster> getById(int id){
        return  collectionTypeMasterRepository.findById(id);
    }
    public CollectionTypeMaster addCollection(CollectionTypeMaster collectionTypeMaster) {
       List<CollectionTypeMaster> existingCollection = collectionTypeMasterRepository.findByName(collectionTypeMaster.getCollectionName());
        boolean anyNotDelted=existingCollection.stream().anyMatch(c->!c.isDelete());
        if(anyNotDelted){
            throw new RuntimeException("CollectionType with the same name already exists and is not deleted");
        }
        collectionTypeMaster.setActive(true);
        collectionTypeMaster.setLastUpdate(LocalDateTime.now());
        collectionTypeMaster.setCreateDate(LocalDateTime.now());
        return collectionTypeMasterRepository.save(collectionTypeMaster);
    }
    public CollectionTypeMaster activateCollection(int id){
        Optional<CollectionTypeMaster> collectionType= collectionTypeMasterRepository.findById(id);
        if(collectionType.isPresent()){
            CollectionTypeMaster s=collectionType.get();
            collectionType.get().setActive(true);
            collectionType.get().setDelete(false);
            collectionType.get().setDeactive(false);
            return collectionTypeMasterRepository.save(s);
        }
        else {
            throw new RuntimeException("collectionType with Id"+id+"not found");
        }
    }
    public CollectionTypeMaster deactivateCollection(int id) {
        Optional<CollectionTypeMaster> optionalState = collectionTypeMasterRepository.findById(id);
        if (optionalState.isPresent()) {
            CollectionTypeMaster collection= optionalState.get();
            collection.setActive(false);
            collection.setDelete(false);
            collection.setDeactive(true);
            return collectionTypeMasterRepository.save(collection);
        } else {
            throw new RuntimeException("Collection with ID " + id + " not found");
        }
    }
    public CollectionTypeMaster deletedCollection(int id) {
        Optional<CollectionTypeMaster> optionalState = collectionTypeMasterRepository.findById(id);
        if (optionalState.isPresent()) {
            CollectionTypeMaster collection= optionalState.get();
            collection.setActive(false);
            collection.setDelete(true);
            collection.setDeactive(false);
            return collectionTypeMasterRepository.save(collection);
        } else {
            throw new RuntimeException("Collection with ID " + id + " not found");
        }
    }
    @PrePersist
    public CollectionTypeMaster updateCollection(int id, String newCollectionName) {
        Optional<CollectionTypeMaster> collectionType = collectionTypeMasterRepository.findById(id);
        if(collectionType.isPresent()) {
            CollectionTypeMaster s = collectionType.get();
            s.setCollectionName(newCollectionName);
            s.setLastUpdate(LocalDateTime.now());
            return collectionTypeMasterRepository.save(s);
        }
        else{
            throw  new RuntimeException("Collection with Id "+id+" not found");
        }
    }
}
