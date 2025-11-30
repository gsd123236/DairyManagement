package com.dairy.Controller;


import com.dairy.Dto.ResponseDTO;
import com.dairy.Entity.MilkTypeMaster;
import com.dairy.Services.MilkTypeMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/milk")
@RestController
public class MilkTypeMasterController {
    @Autowired
    private MilkTypeMasterService milkTypeMasterService;
    @GetMapping("/getAll/admin")
    public ResponseDTO getActiveDeactive(){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=milkTypeMasterService.getActiveDeactive();
            response.message="Success";
            response.success=true;
            response.status=200l;
        }catch (Exception e){
            response.message="Not get data..";
            response.success=false;
            response.status=500L;
        }
        return response;
    }
    @GetMapping("/getAll")
    public ResponseDTO getAll(){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=milkTypeMasterService.getAllMilkType();
            response.message="Success";
            response.success=true;
            response.status=200l;
        }catch (Exception e){
            response.message="Not get data..";
            response.success=false;
            response.status=500L;
        }
        return response;
    }
    @GetMapping("/get/{id}")
    public ResponseDTO getById(@PathVariable int id){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data= milkTypeMasterService.getById(id);
            response.message="Success";
            response.success=true;
            response.status=200l;
        }catch (Exception e){
            response.message="Not get data..";
            response.success=false;
            response.status=500L;
        }
        return response;
    }
    @PostMapping("/add")
    public ResponseDTO addMilkType(@RequestBody MilkTypeMaster milkType) {
        ResponseDTO response=new ResponseDTO();
        try {
            response.data= milkTypeMasterService.addMilkType(milkType);
            response.message="Success";
            response.success=true;
            response.status=200l;
        }catch (Exception e){
            response.message="Not get data..";
            response.success=false;
            response.status=500L;
        }
        return response;
    }
    @PutMapping("/activate/{id}")
    public ResponseDTO activateMilkType(@PathVariable  int id){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=  milkTypeMasterService.activateMilkType(id);
            response.message="Success";
            response.success=true;
            response.status=200l;
        }catch (Exception e){
            response.message="Not get data..";
            response.success=false;
            response.status=500L;
        }
        return response;
    }
    @PutMapping("/deleted/{id}")
    public ResponseDTO deletedMilkType(@PathVariable  int id){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=  milkTypeMasterService.deletedMilkType(id);
            response.message="Success";
            response.success=true;
            response.status=200l;
        }catch (Exception e){
            response.message="Not get data..";
            response.success=false;
            response.status=500L;
        }
        return response;
    }
    @PutMapping("/deactivate/{id}")
    public ResponseDTO deactivateMilkType(@PathVariable int id) {
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=  milkTypeMasterService.deactivateMilkType(id);
            response.message="Success";
            response.success=true;
            response.status=200l;
        }catch (Exception e){
            response.message="Not get data..";
            response.success=false;
            response.status=500L;
        }
        return response;
    }

    @PutMapping("/update/{id}")
    public  ResponseDTO  updateMilkType(
            @PathVariable int id,
            @RequestParam String newAnimalName) {
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=  milkTypeMasterService.updateMilkType(id,newAnimalName);
            response.message="Success";
            response.success=true;
            response.status=200l;
        }catch (Exception e){
            response.message="Not get data..";
            response.success=false;
            response.status=500L;
        }
        return response;
    }

}

