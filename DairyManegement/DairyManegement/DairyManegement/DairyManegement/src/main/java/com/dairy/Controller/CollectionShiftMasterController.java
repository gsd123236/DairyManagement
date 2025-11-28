package com.dairy.Controller;

import com.dairy.Dto.ResponseDTO;
import com.dairy.Entity.CollectionShiftMaster;
import com.dairy.Services.CollectionShiftMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

;

@RestController
@RequestMapping("/api/shift")
public class CollectionShiftMasterController {
    @Autowired
    private CollectionShiftMasterService collectionShiftMasterService;
    @GetMapping("/getAll/admin")
    public ResponseDTO getActiveDeactive(){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=collectionShiftMasterService.getActiveDeactive();
            response.message="Success";
            response.success=true;
            response.status=200l;
        }catch (Exception e){
            response.message="Not get Data...";
            response.success=false;
            response.status=500l;
        }
        return response;
    }

    @GetMapping("/getAll")
    public ResponseDTO getAll(){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=collectionShiftMasterService.getAllShift();
            response.message="Success";
            response.success=true;
            response.status=200l;
        }catch (Exception e){
            response.message="Not get Data...";
            response.success=false;
            response.status=500l;
        }
        return response;
    }
    @GetMapping("/get/{id}")
    public ResponseDTO getById(@PathVariable int id){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=collectionShiftMasterService.getById(id);
            response.message="Success";
            response.success=true;
            response.status=200l;
        }catch (Exception e){
            response.message="Not get Data...";
            response.success=false;
            response.status=500l;
        }
        return response;
    }
    @PostMapping("/add")
    public ResponseDTO addShift(@RequestBody CollectionShiftMaster collectionShift) {
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=collectionShiftMasterService.addShiftType(collectionShift);
            response.message="Success";
            response.success=true;
            response.status=200l;
        }catch (Exception e){
            response.message="Not get Data...";
            response.success=false;
            response.status=500l;
        }
        return response;
    }
    @PutMapping("/activate/{id}")
    public ResponseDTO activateMilkType(@PathVariable  int id){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data= collectionShiftMasterService.activateCollectionShift(id);
            response.message="Success";
            response.success=true;
            response.status=200l;
        }catch (Exception e){
            response.message="Not get Data...";
            response.success=false;
            response.status=500l;
        }
        return response;
    }
    @PutMapping("/deactivate/{id}")
    public ResponseDTO deactivateShiftType(@PathVariable int id) {
        ResponseDTO response=new ResponseDTO();
        try {
            response.data= collectionShiftMasterService.deactivateShiftType(id);
            response.message="Success";
            response.success=true;
            response.status=200l;
        }catch (Exception e){
            response.message="Not get Data...";
            response.success=false;
            response.status=500l;
        }
        return response;
    }
    @PutMapping("/deleted/{id}")
    public ResponseDTO deletedShiftType(@PathVariable int id) {
        ResponseDTO response=new ResponseDTO();
        try {
            response.data= collectionShiftMasterService.deletedShiftType(id);
            response.message="Success";
            response.success=true;
            response.status=200l;
        }catch (Exception e){
            response.message="Not get Data...";
            response.success=false;
            response.status=500l;
        }
        return response;
    }
    @PutMapping("/update/{id}")
    public ResponseDTO updateShift(
            @PathVariable int id,
            @RequestParam String newShiftName) {
        ResponseDTO response = new ResponseDTO();
        try {
            response.data = collectionShiftMasterService.updateShift(id, newShiftName);
            response.message = "Success";
            response.success = true;
            response.status = 200l;
        } catch (Exception e) {
            response.message = "Not get Data...";
            response.success = false;
            response.status = 500l;
        }
        return response;
    }

}
