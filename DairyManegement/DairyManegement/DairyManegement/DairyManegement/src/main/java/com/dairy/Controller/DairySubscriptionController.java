package com.dairy.Controller;

import com.dairy.Dto.ResponseDTO;
import com.dairy.Entity.DairyRegistration;
import com.dairy.Entity.DairySubscription;
import com.dairy.Repository.DairyRepository;
import com.dairy.Repository.DairySubscriptionRepository;
import com.dairy.Repository.SubscriptionTypeRepository;
import com.dairy.Services.DairySubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dairySubscription")
public class DairySubscriptionController {
    @Autowired
    private DairySubscriptionService service;
    @PostMapping("/add/{dairyId}/{subId}")
    public ResponseDTO add(DairySubscription dairySubscription,@PathVariable int dairyId,@PathVariable int subId) {
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=service.add(dairySubscription,dairyId,subId);
            response.message="Success";
            response.success=true;
            response.status=200l;

        }catch (Exception e){
            response.message="Filed";
            response.success=false;
            response.status=500l;
        }
        return response;
    }
    @PutMapping("/updateStatus/{dairyId}")
    public ResponseDTO updateDairyStatus(@PathVariable int dairyId) {
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=service.updateSubscriptionStatus(dairyId);
            response.message="Success";
            response.success=true;
            response.status=200l;

        }catch (Exception e){
            response.message="Failed";
            response.success=false;
            response.status=500l;
        }
        return response;
    }

    @GetMapping("/getAllSubscription")
    public ResponseDTO getAllSubscription() {
        ResponseDTO response = new ResponseDTO();
        try {
            response.data = service.getAllSubscriptions();
            response.message = "Success";
            response.success = true;
            response.status = 200l;

        } catch (Exception e) {
            response.message = "Failed";
            response.success = false;
            response.status = 500l;
        }
        return response;

    }
    @PutMapping("/updateDairySubscript/{id}/{subId}")
    public ResponseDTO updateDairySubscription( DairySubscription dairySubscription,@PathVariable int id,@PathVariable int subId) {
        ResponseDTO response = new ResponseDTO();
        try {
            response.data = service.createNewDairySubscription(dairySubscription, id, subId);
            response.message = "Success";
            response.success = true;
            response.status = 200l;

        } catch (Exception e) {
            response.message = "Failed";
            response.success = false;
            response.status = 500l;
        }
        return response;

    }
    @GetMapping("/getAll")
    public ResponseDTO getAll() {
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=service.getAll();
            response.message="Success";
            response.success=true;
            response.status=200l;

        }catch (Exception e){
            response.message="Failed";
            response.success=false;
            response.status=500l;
        }
        return response;
    }

}
