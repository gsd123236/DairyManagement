package com.dairy.Controller;


import com.dairy.Dto.RegistrationDTO;
import com.dairy.Dto.ResponseDTO;
import com.dairy.Entity.DairyRegistration;
import com.dairy.Repository.DairyRepository;
import com.dairy.Repository.StateMasterRepository;
import com.dairy.Services.DairyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dairy")
public class DairyController {
    @Autowired
    private DairyService dairyService;

    @Autowired
    DairyRepository dairyRepository;

    @Autowired
    StateMasterRepository stateMasterRepository;

    @PostMapping("/dairy_registration")
    public ResponseDTO registerDairy1(@RequestBody DairyRegistration dairyRegistration) {
        ResponseDTO response = new ResponseDTO();

        try {
            //response.data = apiVersionService.getActiveVersion();
            System.out.println("ID DAiry:"+dairyRegistration.getdId());
            response.data = dairyService.registerDairy1(dairyRegistration);
            response.message = "Dairy Registration Success....";
            response.success = true;
            response.status = 200L;
        } catch (Exception e) {
            response.message =" Mobile Number Already Exist ....";
            response.success = false;
            response.status = 500L;
        }
        return response;
    }


    @PostMapping("/dairy_registration_otp")
    public ResponseDTO registerDairyOtp(@RequestBody DairyRegistration dairyRegistration) {
        ResponseDTO response = new ResponseDTO();

        try {
            response.data = dairyService.registerDairyOtp(dairyRegistration);
            response.message = "Success";
            response.success = true;
            response.status = 200L;
        } catch (Exception e) {
            response.message = "Mobile Number Already Exist";
            response.success = false;
            response.status = 500L;
        }
        return response;
    }


    @PostMapping("/login")
    public ResponseDTO loginDairy(@RequestParam String mobile, @RequestParam String password) {
        ResponseDTO response = new ResponseDTO();
        try {
            Optional<DairyRegistration> dairyRegistration = dairyService.loginDairy(mobile, password);

            if (dairyRegistration.isPresent()) {
                response.data = dairyRegistration.get();
                response.message = "Success";
                response.success = true;
                response.status = 200L;
            } else {
                response.message = "Invalid Username or Password";
                response.success = false;
                response.status = 500L;
            }

        } catch (Exception e) {
            response.message = "Filed....";
            response.success = false;
            response.status = 500L;
        }
        return response;
    }



    @PutMapping("/DairyUpdate/{dId}")
    public ResponseDTO dairyUpdate(@RequestBody DairyRegistration dairyRegistration,@PathVariable int dId) {
        ResponseDTO response = new ResponseDTO();
        try {
            response.data=dairyService.dairyUpdate(dairyRegistration,dId);
            response.message="Dairy Update Successfully....";
            response.success=true;
            response.status=200L;
        } catch (Exception e) {
            response.message="Filed....";
            response.success=false;
            response.status=500L;
        }

        return response;
    }


    @GetMapping("/get-profile/{dId}")
    public ResponseDTO getProfileData(@PathVariable("dId") int dId){
        ResponseDTO response=new ResponseDTO();
        try{
            response.data=dairyService.getProfileData(dId);
            response.message="Success..";
            response.success=true;
            response.status=200L;
        }catch (Exception e){
            response.message="Filed....";
            response.success=false;
            response.status=500L;
        }

        return response;
    }

    @GetMapping("/get-profile-collection/{dId}")
    public ResponseDTO getProfileCollection(@PathVariable("dId") int dId) {
//        return dairyService.getCollectionProfile(dId);
        ResponseDTO response=new ResponseDTO();
        try{
            response.data=dairyService.getCollectionProfile(dId);
            response.message="Success..";
            response.success=true;
            response.status=200L;
        }catch (Exception e){
            response.message="Filed....";
            response.success=false;
            response.status=500L;
        }

        return response;

    }


    @GetMapping("/get-dairy-All")
    public ResponseDTO getAllData(){
//    List<DairyRegistration> dairy=dairyService.getAllData();
        ResponseDTO response=new ResponseDTO();
        try{
            response.data=dairyService.getAllData();
            response.message="Success..";
            response.success=true;
            response.status=200L;
        }catch (Exception e){
            response.message="Filed....";
            response.success=false;
            response.status=500L;
        }

        return response;
    }

    @GetMapping("dairyId/{id}")
    public ResponseDTO getDairyById(@PathVariable int id) {
        ResponseDTO response=new ResponseDTO();
        try{
            response.data=dairyService.findId(id);
            response.message="Success..";
            response.success=true;
            response.status=200L;
        }catch (Exception e){
            response.message="Filed....";
            response.success=false;
            response.status=500L;
        }

        return response;
    }

    // Get Master Data Code
    @GetMapping("/allMasterData")
    public ResponseDTO getAllCategories() {
//        List<RegistrationDTO> dtoRegistrationList = dairyService.getAllCategories();
        ResponseDTO response=new ResponseDTO();
        try{
            response.data=dairyService.getAllCategories();
            response.message="Success..";
            response.success=true;
            response.status=200L;
        }catch (Exception e){
            response.message="Filed....";
            response.success=false;
            response.status=500L;
        }

        return response;
    }

    //GET master data in Id



    @GetMapping("/dairyIdMaster/{id}")
    public ResponseDTO getByAllId(@PathVariable int id) {
        ResponseDTO response=new ResponseDTO();
        try {
            //response.data = apiVersionService.getActiveVersion();
            response.data = dairyService.findByAllId(id);
            response.message = "Success";
            response.success = true;
            response.status = 200L;
        } catch (Exception e) {
            response.message = "Dairy Id Invalid...";
            response.success = false;
            response.status = 500L;
        }
        return response;

    }

    @GetMapping("/dairyActiveData")
    public ResponseDTO getActiveDairy() {
        ResponseDTO response=new ResponseDTO();
        try {
            response.data = dairyService.getActivedata();
            response.message = "Success";
            response.success = true;
            response.status = 200L;
        } catch (Exception e) {
            response.message = "Dairy Id Invalid...";
            response.success = false;
            response.status = 500L;
        }
        return response;

    }




}

