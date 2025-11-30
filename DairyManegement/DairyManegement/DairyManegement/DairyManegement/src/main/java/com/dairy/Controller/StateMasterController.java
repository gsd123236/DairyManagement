package com.dairy.Controller;


import com.dairy.Dto.ResponseDTO;
import com.dairy.Entity.StateMaster;
import com.dairy.Services.StateMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/state")
public class StateMasterController {
    @Autowired
    private StateMasterService stateMasterService;

    @GetMapping("/state/admin")
    public ResponseDTO getActiveOrDeactive() {
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=stateMasterService.getAllActiveOrDeactive();
            response.message="Success";
            response.success=true;
            response.status=200l;

        }catch (Exception e){
            response.message="Filed Data get...";
            response.success=false;
            response.status=500l;
        }
        return response;
    }
    @GetMapping("/state")
    public ResponseDTO getAllStates() {
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=stateMasterService.getAllstates();
            response.message="Success";
            response.success=true;
            response.status=200l;

        }catch (Exception e){
            response.message="Filed Data get...";
            response.success=false;
            response.status=500l;
        }
        return response;
    }
    @GetMapping("/state/{id}")
    public ResponseDTO getById(@PathVariable int id){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=stateMasterService.getById(id);
            response.message="Success";
            response.success=true;
            response.status=200l;

        }catch (Exception e){
            response.message="Filed Data get...";
            response.success=false;
            response.status=500l;
        }
        return response;
    }

    @PostMapping("/addState")
    public ResponseDTO addState(@RequestBody StateMaster state) {
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=stateMasterService.addState(state); response.message="Success";
            response.success=true;
            response.status=200l;

        }catch (Exception e){
            response.message="Filed Data get...";
            response.success=false;
            response.status=500l;
        }
        return response;
    }

    @PutMapping("/deactivate/{id}")
    public ResponseDTO deactivateState(@PathVariable int id) {
        ResponseDTO response = new ResponseDTO();
        try {
            StateMaster updatedState = stateMasterService.deactivateState(id);
            response.data = updatedState;
            response.success = true;
            response.status = 200L;
        } catch (Exception e) {
            response.message = "Failed to update data...";
            response.success = false;
            response.status = 500L;
        }
        return response;
    }
    @PutMapping("/deleted/{id}")
    public ResponseDTO deletd(@PathVariable int id) {
        ResponseDTO response=new ResponseDTO();
        try {
            response.data= stateMasterService.deletedState(id);
            response.success=true;
            response.status=200l;

        }catch (Exception e){
            response.message="Filed Data get...";
            response.success=false;
            response.status=500l;
        }
        return response;
    }

    @PutMapping("/activate/{id}")
    public ResponseDTO activeState(@PathVariable int id) {
        ResponseDTO response = new ResponseDTO();
        try {
            StateMaster updatedState = stateMasterService.activateState(id);
            response.data = updatedState;
            response.success = true;
            response.status = 200L;
        } catch (Exception e) {
            response.message = "Failed to update data...";
            response.success = false;
            response.status = 500L;
        }
        return response;
    }
    @PutMapping("/update/{id}")
    public ResponseDTO updateState(
            @PathVariable int id,
            @RequestParam String newStateName) {
        ResponseDTO response=new ResponseDTO();
        try {
            response.data= stateMasterService.updateState(id, newStateName);

            response.success=true;
            response.status=200l;

        }catch (Exception e){
            response.message="Filed Data get...";
            response.success=false;
            response.status=500l;
        }
        return response;
    }


}

