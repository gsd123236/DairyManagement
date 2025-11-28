package com.dairy.Controller;

import com.dairy.Dto.ResponseDTO;
import com.dairy.Entity.ExpenseCategoriesMaster;
import com.dairy.Services.ExpensiveCategoriesMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ExpensiveMaster")
public class ExpensiveCategoriesMasterController {
    @Autowired
    private ExpensiveCategoriesMasterService service;
    @GetMapping("/expense/admin")
    public ResponseDTO getActiveOrDeactive() {
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=service.activeDeactive();
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
    @PostMapping("/add")
    public ResponseDTO add(@RequestBody ExpenseCategoriesMaster expensive){
        ResponseDTO response=new ResponseDTO();
        try{
            response.data=service.add(expensive);
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
    public  ResponseDTO updateExpensive(@PathVariable int id, @RequestParam String newexpensive){
        ResponseDTO response=new ResponseDTO();
        try{
            response.data=service.updateExpensive(id,newexpensive);
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
    public ResponseDTO DeactiveExpensive(@PathVariable int id){
        ResponseDTO response=new ResponseDTO();
        try{
            response.data=service.DeActive(id);
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
    @PutMapping("/deleted/{id}")
    public ResponseDTO Deleted(@PathVariable int id){
        ResponseDTO response=new ResponseDTO();
        try{
            response.data=service.isDeleted(id);
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
    public ResponseDTO ActiveExpensive(@PathVariable int id){
        ResponseDTO response=new ResponseDTO();
        try{
            response.data=service.Active(id);
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
    @GetMapping("/getById/{id}")
    public ResponseDTO getById(@PathVariable int id){
        ResponseDTO response=new ResponseDTO();
        try{
            response.data= service.getById(id);
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
            response.data=service.getAll();
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
