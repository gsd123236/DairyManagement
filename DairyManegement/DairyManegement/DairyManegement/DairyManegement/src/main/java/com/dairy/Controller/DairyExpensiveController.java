package com.dairy.Controller;

import com.dairy.Dto.DairyExpensiveDTO;
import com.dairy.Dto.ResponseDTO;
import com.dairy.Entity.DairyExpensive;
import com.dairy.Entity.FarmerRegistration;
import com.dairy.Services.DairyExpensiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RestController
@RequestMapping("/api/expensiveData")
public class DairyExpensiveController {
    @Autowired
    private DairyExpensiveService service;
    @PostMapping("/add/{dairy_id}")
    public ResponseDTO add(@PathVariable int dairy_id, @RequestBody DairyExpensive expensive){
        ResponseDTO response=new ResponseDTO();
        try{
            response.data =service.add(dairy_id,expensive);
            response.message="Success";
            response.success=true;
            response.status=200l;
        }
        catch (Exception e){
            response.message="Failed ";
            response.success=false;
            response.status=500l;
        }
        return response;
    }
    @GetMapping("/getAll/{dairy_id}")
    public ResponseDTO getAllDairyExpenses(@PathVariable int dairy_id) {
        ResponseDTO response=new ResponseDTO();
        try{
            response.data=service.getAllDairyExpenses(dairy_id);
            response.message="Success";
            response.success=true;
            response.status=200l;
        }
        catch (Exception e){
            response.message="Failed ";
            response.success=false;
            response.status=500l;
        }
        return response;
    }
    @DeleteMapping("/delete/{id}")
    public  ResponseDTO deleteExpensive(@PathVariable int id){
        ResponseDTO response=new ResponseDTO();
        try{
            response.data=service.deleteDairyExpenseById(id);
            response.message="Delete Successfully....";
            response.success=true;
            response.status=200l;
        }
        catch (Exception e){
            response.message="Failed ";
            response.success=false;
            response.status=500l;
        }
        return response;
    }
    @GetMapping("/transactions/{dairy_id}")
    public ResponseDTO getTransactionsByDateRange(
            @PathVariable("dairy_id") int dairy_id,
            @RequestParam String startDate,
            @RequestParam String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        ResponseDTO response = new ResponseDTO();
        try {
            response.data = service.getTransactionsByDateRange(dairy_id, start, end);
            response.message = "Success";
            response.success = true;
            response.status = 200l;
        } catch (Exception e) {
            response.message = "Failed ";
            response.success = false;
            response.status = 500l;
        }
        return response;

    }
    @PutMapping("/update/{id}")
    public ResponseDTO updateExpensive(
            @PathVariable int id,
            @RequestBody DairyExpensive expense) {
//        try {
//            DairyExpensive updated = service.updateExpensive(id, expense);
//            return ResponseEntity.status(HttpStatus.OK).body("update Successfully");
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error Found!!");
//        }
        ResponseDTO response = new ResponseDTO();
        try {
            response.data = service.updateExpensive(id, expense);
            response.message = "Success";
            response.success = true;
            response.status = 200l;
        } catch (Exception e) {
            response.message = "Failed ";
            response.success = false;
            response.status = 500l;
        }
        return response;
    }
}

