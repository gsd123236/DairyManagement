package com.dairy.Controller;

import com.dairy.Dto.ResponseDTO;
import com.dairy.Entity.BuffaloMilkRateMaster;
import com.dairy.Services.BuffaloMilkRateMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/Buffalo")
public class BuffaloMilkRateMasterController {
    @Autowired
    private BuffaloMilkRateMasterService service;
    @PostMapping("/add")
    public ResponseDTO add(@RequestBody BuffaloMilkRateMaster buffalo){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=service.add(buffalo);
            response.message="Success";
            response.success=true;
            response.status=200l;
        } catch (Exception e) {
            response.message="unSuccessfully";
            response.success=false;
            response.status=500l;
        }
        return response;
    }
    @GetMapping("/getAll/admin")
    public ResponseDTO getActiveDeactive(){
        ResponseDTO response=new ResponseDTO();
        try{
            response.data= service.getActiveDeactive();
            response.message="Success";
            response.success=true;
            response.status=200l;
        } catch (Exception e) {
            response.message="unSuccessfully";
            response.success=false;
            response.status=500l;
        }
        return response;
    }

    @GetMapping("/getAll")
    public ResponseDTO getAll(){
        ResponseDTO response=new ResponseDTO();
        try{
            response.data= service.getAll();
            response.message="Success";
            response.success=true;
            response.status=200l;
        } catch (Exception e) {
            response.message="unSuccessfully";
            response.success=false;
            response.status=500l;
        }
        return response;
    }
    @GetMapping("/get/{id}")
    public ResponseDTO getById(@PathVariable int id){
        ResponseDTO response=new ResponseDTO();
        try{
            response.data =service.getById(id);
            response.message="Success";
            response.success=true;
            response.status=200l;
        } catch (Exception e) {
            response.message="unSuccessfully";
            response.success=false;
            response.status=500l;
        }
        return response;
    }
    @PutMapping("/deactive/{id}")
    public ResponseDTO deactivate(@PathVariable int id){
        ResponseDTO response=new ResponseDTO();
        try{
            response.data =service.isDeactive(id);
            response.message="Success";
            response.success=true;
            response.status=200l;
        } catch (Exception e) {
            response.message="unSuccessfully";
            response.success=false;
            response.status=500l;
        }
        return response;
    }
    @PutMapping("/delete/{id}")
    public ResponseDTO deleted(@PathVariable int id){
        ResponseDTO response=new ResponseDTO();
        try{
            response.data =service.isDeleted(id);
            response.message="Success";
            response.success=true;
            response.status=200l;
        } catch (Exception e) {
            response.message="unSuccessfully";
            response.success=false;
            response.status=500l;
        }
        return response;
    }

    @PutMapping("/active/{id}")
    public ResponseDTO isActive(@PathVariable int id){
        ResponseDTO response=new ResponseDTO();
        try{
            response.data =service.isActive(id);
            response.message="Success";
            response.success=true;
            response.status=200l;
        } catch (Exception e) {
            response.message="unSuccessfully";
            response.success=false;
            response.status=500l;
        }
        return response;
    }
    @PutMapping("/update/{id}")
    public  ResponseDTO updateRate(@PathVariable int id,@RequestParam String rateType){
        ResponseDTO response=new ResponseDTO();
        try{
            response.data =service.update(id,rateType);
            response.message="Success";
            response.success=true;
            response.status=200l;
        } catch (Exception e) {
            response.message="unSuccessfully";
            response.success=false;
            response.status=500l;
        }
        return response;
    }



}
