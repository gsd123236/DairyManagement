package com.dairy.Controller;

import com.dairy.Dto.ResponseDTO;
import com.dairy.Entity.PaymentPeriodMaster;
import com.dairy.Entity.StateMaster;
import com.dairy.Services.PaymentPeriodMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payment")
public class PaymentPeriodMasterController {
    @Autowired
    private PaymentPeriodMasterService paymentPeriodMasterService;
    @GetMapping("/getAll")
    public ResponseDTO getAll(){
        ResponseDTO response=new ResponseDTO();
        try {

            response.data=paymentPeriodMasterService.getAll();
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
            response.data=paymentPeriodMasterService.getById(id);
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
    public ResponseDTO addPaymentPeriod(@RequestBody PaymentPeriodMaster paymentPeriod){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=paymentPeriodMasterService.addPaymentPeriod(paymentPeriod);
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
    public ResponseDTO activatePayment(@PathVariable  int id){
        ResponseDTO response=new ResponseDTO();
        try {
             PaymentPeriodMaster updatedState = paymentPeriodMasterService.activatePaymentPeriod(id);
            response.data=updatedState;
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
            PaymentPeriodMaster updatedState = paymentPeriodMasterService.deactivatePaymentPeriod(id);
            response.data=updatedState;
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
    @GetMapping("/payment/admin")
    public ResponseDTO getActiveOrDeactive() {
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=paymentPeriodMasterService.getActiveDeactive();
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
    @PutMapping("/deleted/{id}")
    public ResponseDTO deletedPeriod(@PathVariable int id) {
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=paymentPeriodMasterService.DeletedPaymentPeriod(id);
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
    public ResponseDTO updateMilkType(
            @PathVariable int id,
            @RequestParam String newPaymentPeriodName) {
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=paymentPeriodMasterService.paymentPeriodTime(id,newPaymentPeriodName);
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
}
