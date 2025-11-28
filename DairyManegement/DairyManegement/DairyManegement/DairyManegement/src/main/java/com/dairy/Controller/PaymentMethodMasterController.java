package com.dairy.Controller;

import com.dairy.Dto.ResponseDTO;
import com.dairy.Entity.PaymentMethodMaster;
import com.dairy.Services.PaymentMethodMasterService;

import com.twilio.twiml.voice.Pay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payment-method")
public class PaymentMethodMasterController {

    @Autowired
    private PaymentMethodMasterService service;
    @GetMapping("/payment/admin")
    public ResponseDTO getActiveOrDeactive() {
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=service.getActiveDeactive();
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

    @PostMapping
    public ResponseDTO addPaymentMethod(@RequestBody PaymentMethodMaster paymentMethod) {
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=service.savePaymentMethod(paymentMethod);
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

    @GetMapping
    public ResponseDTO getAllPaymentMethods() {
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=service.getAllPaymentMethods();
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

    @GetMapping("/{id}")
    public  ResponseDTO getPaymentMethodById(@PathVariable int id) {
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=service.getPaymentMethodById(id);
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
    @PutMapping("/isActive/{id}")
    public ResponseDTO isActive(@PathVariable int id){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=service.activatePaymentMethod(id);
            response.success=true;
            response.status=200l;
        }catch (Exception e){
            response.message="Not get data..";
            response.success=false;
            response.status=500L;
        }
        return response;
    }
    @PutMapping("/isDeactive/{id}")
    public ResponseDTO isDeactive(@PathVariable int id){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=service.deactivatePaymentMethod(id);
            response.success=true;
            response.status=200l;
        }catch (Exception e){
            response.message="Not get data..";
            response.success=false;
            response.status=500L;
        }
        return response;
    }
    @PutMapping("/isDelete/{id}")
    public ResponseDTO isDeleted(@PathVariable int id){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=service.DeletedPaymentMethod(id);
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
    public ResponseDTO UpdatePayMethodName(@PathVariable int id, @RequestParam String paymentMethod){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=service.update(id, paymentMethod);
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
