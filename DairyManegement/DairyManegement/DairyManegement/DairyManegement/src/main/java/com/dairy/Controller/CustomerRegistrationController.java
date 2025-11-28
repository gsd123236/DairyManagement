package com.dairy.Controller;

import com.dairy.Dto.CustomerDTO;
import com.dairy.Dto.FarmerDTO;
import com.dairy.Dto.ResponseDTO;
import com.dairy.Entity.CustomerRegistration;
import com.dairy.Services.CustomerRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerRegistrationController {
    @Autowired
    private CustomerRegistrationService service;
    @PostMapping("/add/{dairyId}")
    public ResponseDTO add(@PathVariable int dairyId, @RequestBody  CustomerRegistration customer){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=service.add(customer,dairyId);
            response.message="Success";
            response.status=200l;
            response.success=true;
        } catch (Exception e) {
            response.message="Unsuccessfully";
            response.status=500l;
            response.success=false;

        }
        return response;
    }
    //    @GetMapping("/getByDairy/{id}")
//    public ResponseDTO getAll(@PathVariable int id){
//        ResponseDTO response=new ResponseDTO();
//        try {
//            response.data = service.getCustomerByDairyid(id);
//            response.message="successfully";
//            response.status=200l;
//            response.success=true;
//        }
//        catch (Exception e) {
//            response.message="Unsuccessfully";
//            response.status=500l;
//            response.success=false;
//
//        }
//        return response;
//    }
    //    @GetMapping("/get/{id}")
//    public ResponseEntity<CustomerDTO> getDairyRegistrations(@PathVariable int id) {
//        try {
//            CustomerDTO registrations = service.getCustomerDTO(id);
//            return ResponseEntity.ok(registrations);
//        }
//        catch (RuntimeException e){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//    }
    @PutMapping("/update/{id}")
    public ResponseDTO updateCustomer(@PathVariable int id,@RequestBody CustomerRegistration customer){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data =service.updateCustomer(id,customer);
            response.message="successfully";
            response.status=200l;
            response.success=true;
        }
        catch (Exception e) {
            response.message="Unsuccessfully";
            response.status=500l;
            response.success=false;

        }
        return response;
    }
    @PutMapping("/active/{id}")
    public  ResponseDTO isActive(@PathVariable int id){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=service.isActive(id);
            response.message="successfully";
            response.status=200l;
            response.success=true;
        }
        catch (Exception e) {
            response.message="Unsuccessfully";
            response.status=500l;
            response.success=false;

        }
        return response;

    }
    @DeleteMapping("/delete/{id}")
    public  ResponseDTO isDeactive(@PathVariable int id){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=service.isDelete(id);
            response.message="successfully";
            response.status=200l;
            response.success=true;
        }
        catch (Exception e) {
            response.message="Unsuccessfully";
            response.status=500l;
            response.success=false;

        }
        return response;
    }
    @GetMapping("/allCustomer/{id}")
    public ResponseDTO findtheCustomer(@PathVariable int id){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=  service.getAllCustomerByDairyId(id);
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
            response.data=  service.getAllActive(dairy_id);
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
            response.data=  service.getAllInactive(dairy_id);
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
    public  ResponseDTO activateCustomer(@PathVariable  int id){

        ResponseDTO response=new ResponseDTO();
        try {
            response.data=  service.activateCustomer(id);
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
    @PutMapping("/Inactive/{id}")
    public  ResponseDTO InactivateCustomer(@PathVariable  int id){

        ResponseDTO response=new ResponseDTO();
        try {
            response.data=  service.InactivateCustomer(id);
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
