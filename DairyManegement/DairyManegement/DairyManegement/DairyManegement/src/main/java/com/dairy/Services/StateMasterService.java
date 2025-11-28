package com.dairy.Services;

import com.dairy.Entity.StateMaster;
import com.dairy.Repository.StateMasterRepository;
import jakarta.persistence.PrePersist;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StateMasterService {
    @Autowired
    private StateMasterRepository staterepository;
    public List<StateMaster>getAllstates(){
        return staterepository.getAllActive();
    }
    public List<StateMaster>getAllActiveOrDeactive(){
        return staterepository.getAllActiveOrDeactive();
    }
    public Optional<StateMaster> getById(int id){
        return  staterepository.findById(id);
    }

    public StateMaster addState(StateMaster state) {
        List<StateMaster> existingStates = staterepository.findByName(state.getStateName());
        boolean anyNotDeleted = existingStates.stream().anyMatch(s -> !s.isDelete());
        if (anyNotDeleted) {
            throw new RuntimeException("State with the same name already exists and is not deleted.");
        }
        state.setLastUpdate(LocalDateTime.now());
        state.setCreateDate(LocalDateTime.now());
        state.setActive(true);
        return staterepository.save(state);
    }


    public StateMaster deactivateState(int id) {
        Optional<StateMaster> stateOpt = staterepository.findById(id);
        if (stateOpt.isPresent()) {
            StateMaster state = stateOpt.get();
            state.setActive(false);
            state.setDeactive(true);
            return staterepository.save(state);
        }
        return null;
    }
    public StateMaster deletedState(int id) {
        Optional<StateMaster> optionalState = staterepository.findById(id);
        if (optionalState.isPresent()) {
            StateMaster state = optionalState.get();
            state.setActive(false);
            state.setDeactive(false);
            state.setDelete(true);
            return staterepository.save(state);
        } else {
            throw new RuntimeException("State with ID " + id + " not found");
        }
    }

    public StateMaster activateState(int id) {
        Optional<StateMaster> stateOpt = staterepository.findById(id);
        if (stateOpt.isPresent()) {
            StateMaster state = stateOpt.get();
            state.setActive(true);
            state.setDeactive(false);
            return staterepository.save(state);
        }
        return null;
    }
    @PrePersist
    public StateMaster updateState(int id, String newStateName) {
        Optional<StateMaster> state = staterepository.findById(id);
        if(state.isPresent()) {
            StateMaster s = state.get();
            s.setStateName(newStateName);
            s.setLastUpdate(LocalDateTime.now());
            return staterepository.save(s);
        }
        else{
            throw  new RuntimeException("State with Id "+id+" not found");
        }
    }

}
