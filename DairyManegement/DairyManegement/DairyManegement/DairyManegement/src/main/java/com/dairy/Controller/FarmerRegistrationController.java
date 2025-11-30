package com.dairy.Controller;


import com.dairy.Dto.FarmerDTO;
import com.dairy.Dto.ResponseDTO;
import com.dairy.Entity.FarmerRegistration;
import com.dairy.Repository.MilkTypeMasterRepository;
import com.dairy.Services.FarmerRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/farmer")
public class FarmerRegistrationController {
    @Autowired
    private FarmerRegistrationService farmerRegistrationService;
    @GetMapping("/getAllFarmer")
    public ResponseDTO getAllFarmers() {
        ResponseDTO response=new ResponseDTO();
        try {
            response.data= farmerRegistrationService.getAllFarmer();
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

    @GetMapping("/getAllActive/{dairy_id}")
    public ResponseDTO getAllActive(@PathVariable int dairy_id){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=  farmerRegistrationService.getAllActive(dairy_id);
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
    @GetMapping("/getAllInactive/{dairy_id}")
    public ResponseDTO getAllInactive(@PathVariable int dairy_id){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=  farmerRegistrationService.getAllInactive(dairy_id);
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

    @GetMapping("/allFarmer/{id}")
    public ResponseDTO findtheFarmer(@PathVariable int id){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=  farmerRegistrationService.getAllFarmerByDairyId(id);
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

//    @GetMapping("/AdminFarmer")
//    public ResponseDTO getAdminFarmer(){
//        ResponseDTO response=new ResponseDTO();
//        try {
//            response.data=  farmerRegistrationService.getAllAdminFarmer();
//            response.message="Success";
//            response.success=true;
//            response.status=200l;
//        }catch (Exception e){
//            response.message="Not get data..";
//            response.success=false;
//            response.status=500L;
//        }
//        return response;
//    }
//    @GetMapping("/{id}")
//    public ResponseDTO getDairyRegistrations(@PathVariable int id) {
//        ResponseDTO response=new ResponseDTO();
//        try {
//            response.data= farmerRegistr  ationService.getFarmerById(id);
//            response.message="Success";
//            response.success=true;
//            response.status=200l;
//        }catch (Exception e){
//            response.message="Not get data..";
//            response.success=false;
//            response.status=500L;
//        }
//        return response;
//    }

//    @GetMapping("/dairyIdByParticularFarmer/{dairy_id}/{id}")
//    public  FarmerDTO getByIdByDairyId(@PathVariable int dairy_id,@PathVariable int id){
//        return  farmerRegistrationService.getFarmerByDairyId(dairy_id,id);
//    }

    @PostMapping("/add/{dairyId}")
    public ResponseDTO addFarmer(@PathVariable int dairyId, @RequestBody FarmerRegistration farmer) {
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=  farmerRegistrationService.saveFarmer(farmer, dairyId);
            response.message="Success";
            response.success=true;
            response.status=200l;
        }catch (Exception e){
            response.message="Farmer code already exists...";
            response.success=false;
            response.status=500L;
        }
        return response;
    }
    @PutMapping("/activate/{id}")
    public  ResponseDTO activateFarmer(@PathVariable  int id){

        ResponseDTO response=new ResponseDTO();
        try {
            response.data=  farmerRegistrationService.activateFarmer(id);
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
    public  ResponseDTO deactivateCollection(@PathVariable int id) {
        ResponseDTO response=new ResponseDTO();
        try {
            response.data= farmerRegistrationService.deactivateCollection(id);
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
    public ResponseDTO updateFarmer(
            @PathVariable int id,
            @RequestBody FarmerRegistration updatedFarmer) {
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=  farmerRegistrationService.updateFarmer(id, updatedFarmer);
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
