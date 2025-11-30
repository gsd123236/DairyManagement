package com.dairy.Controller;

import com.dairy.Dto.ResponseDTO;
import com.dairy.Entity.SubscriptionType;
import com.dairy.Services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {
    @Autowired
    private SubscriptionService service;
    @PostMapping("/add")
    public ResponseDTO addSubSubscription(@RequestBody SubscriptionType subScriptionType){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=service.addAll(subScriptionType);
            response.status=200l;
            response.success=true;
            response.message="Success";
        } catch (Exception e) {
            response.status=500l;
            response.message="Failed";
            response.success=false;
        }
        return response;
    }
    @GetMapping("/getAll")
    public ResponseDTO getAllSubscription(){
        ResponseDTO response=new ResponseDTO();
        try{
            response.data=service.getAll();
            response.status=200l;
            response.success=true;
            response.message="Success";
        } catch (Exception e) {
            response.status=500l;
            response.message="Failed";
            response.success=false;
        }
        return response;
    }
    @PutMapping("/update/{id}")
    public ResponseDTO updateSubScription(@PathVariable int id,@RequestBody SubscriptionType subScriptionType){
        ResponseDTO response=new ResponseDTO();
        try{
            response.data=service.updateSubscription(id,subScriptionType);
            response.status=200l;
            response.success=true;
            response.message="Success";
        } catch (Exception e) {
            response.status=500l;
            response.message="Failed";
            response.success=false;
        }
        return response;
    }
}
