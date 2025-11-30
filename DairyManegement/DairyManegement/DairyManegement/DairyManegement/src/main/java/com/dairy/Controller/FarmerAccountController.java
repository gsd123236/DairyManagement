package com.dairy.Controller;

import com.dairy.Dto.FarmerAccountDTO;
import com.dairy.Dto.ResponseDTO;
import com.dairy.Entity.FarmerAccount;
import com.dairy.Services.FarmerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/account")
public class FarmerAccountController {
    @Autowired
    private FarmerAccountService farmerAccountService;
    @PostMapping("/add/{farmerId}")
    public ResponseDTO addFarmer(@PathVariable int farmerId, @RequestBody FarmerAccount farmerAccount){
        ResponseDTO response=new ResponseDTO();

        try {
            response.data= farmerAccountService.addFarmer(farmerAccount,farmerId);
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
    @GetMapping("/transactions/{farmerId}")
    public ResponseDTO getTransactions(
            @PathVariable int farmerId,
            @RequestParam("startDate")String startDate,
            @RequestParam("endDate")String endDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        ResponseDTO response=new ResponseDTO();
        try {
            response.data= farmerAccountService.getFormattedTransactionsByDateRange(farmerId, start, end);
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

    @GetMapping("/balance/{farmer_id}")
    public ResponseDTO getBalance(@PathVariable ("farmer_id") int farmer_id){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data= farmerAccountService.getBalance(farmer_id);
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
    @DeleteMapping("/delete/{farmer_id}")
    public  ResponseDTO deleteByFarmerId(@PathVariable("farmer_id")  int farmerId){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data= farmerAccountService.deleteByFarmerId(farmerId);
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


    @GetMapping("/farmer/{farmer_id}")
    public ResponseDTO getFarmerAccounts(@PathVariable ("farmer_id") int farmer_id) {
        ResponseDTO response=new ResponseDTO();
        try {
            response.data= farmerAccountService.getFarmer(farmer_id);
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
    @GetMapping("/FarmerInvoice/{farmerId}")
    public ResponseDTO getFarmerAccountByDate(
            @PathVariable int farmerId,
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate startDate,
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate endDate) {

        ResponseDTO response = new ResponseDTO();
        try {
            response.data = farmerAccountService.getByDateInvoice(farmerId, startDate, endDate);
            response.message = "Success";
            response.success = true;
            response.status = 200L;
        } catch (Exception e) {
            response.message = "Unsuccessful";
            response.success = false;
            response.status = 500L;
        }
        return response;
    }


    }

