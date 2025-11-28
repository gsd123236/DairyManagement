package com.dairy.Controller;

import com.dairy.Dto.ResponseDTO;
import com.dairy.Entity.CowMilkRateMaster;
import com.dairy.Services.CowMilkRateMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cow")
public class CowMilkRateMasterController {
    @Autowired
    private CowMilkRateMasterService service;
    @PostMapping("/add")
    public ResponseDTO add(@RequestBody CowMilkRateMaster cowMilkRateMaster) {
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=service.add(cowMilkRateMaster);
            response.success=true;
            response.message="Successfully";
            response.status=200l;
        } catch (Exception e) {
            response.message="UnSuccessfully";
            response.success=false;
            response.status=500l;
        }
        return  response;
    }
    @GetMapping("/get/{id}")
    public ResponseDTO getById(@PathVariable int id){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data= service.getById(id);
            response.success=true;
            response.message="Successfully";
            response.status=200l;
        } catch (Exception e) {
            response.message="UnSuccessfully";
            response.success=false;
            response.status=500l;
        }
        return  response;
    }
    @GetMapping("/getAll")
    public ResponseDTO getAll(){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=service.getAll();
            response.success=true;
            response.message="Success";
            response.status=200l;
        } catch (Exception e) {
            response.message="UnSuccessfully";
            response.success=false;
            response.status=500l;
        }
        return  response;
    }
    @GetMapping("/getAll/admin")
    public ResponseDTO getActiveDeactive(){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=service.getActiveDeactive();
            response.success=true;
            response.message="Successfully";
            response.status=200l;
        } catch (Exception e) {
            response.message="UnSuccessfully";
            response.success=false;
            response.status=500l;
        }
        return  response;
    }
    @PutMapping("/isActive/{id}")
    public ResponseDTO  isActive(@PathVariable int id){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=service.isActive(id);
            response.success=true;
            response.message="Successfully";
            response.status=200l;
        } catch (Exception e) {
            response.message="UnSuccessfully";
            response.success=false;
            response.status=500l;
        }
        return  response;

    }
    @PutMapping("/isDeactive/{id}")
    public ResponseDTO isDeactive(@PathVariable int id) {
        ResponseDTO response = new ResponseDTO();
        try {
            response.data = service.isDeactive(id);
            response.success = true;
            response.message = "Successfully";
            response.status = 200l;
        } catch (Exception e) {
            response.message = "UnSuccessfully";
            response.success = false;
            response.status = 500l;
        }
        return  response;
    }
    @PutMapping("/isDeleted/{id}")
    public ResponseDTO isDeleted(@PathVariable int id){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=service.isDeleted(id);
            response.success=true;
            response.message="Successfully";
            response.status=200l;
        } catch (Exception e) {
            response.message="UnSuccessfully";
            response.success=false;
            response.status=500l;
        }
        return  response;
    }
    @PutMapping("/update/{id}")
    public ResponseDTO updateMilkRate(@PathVariable int id, @RequestParam String rateType){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=service.updateCowRate(id,rateType);
            response.success=true;
            response.message="Successfully";
            response.status=200l;
        } catch (Exception e) {
            response.message="UnSuccessfully";
            response.success=false;
            response.status=500l;
        }
        return  response;
    }
}
