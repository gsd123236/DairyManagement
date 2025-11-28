package com.dairy.Controller;

import com.dairy.Dto.ResponseDTO;
import com.dairy.Entity.FarmerInvoice;
import com.dairy.Services.FarmerInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/FarmerInvoice")
public class FarmerInvoiceController {
    @Autowired
    private FarmerInvoiceService farmerInvoiceService;
    @PostMapping("/add")
    public  ResponseDTO saveInvoice(@RequestBody FarmerInvoice farmerInvoice){
        ResponseDTO response= new ResponseDTO();
        try {
            response.data= farmerInvoiceService.farmerInvoiceSave(farmerInvoice);
            response.success=true;
            response.status=200l;
            response.message="Success";
        } catch (Exception e) {
            response.message="unSuccessfully";
            response.success=false;
            response.status=500l;
        }
        return response;
    }
    @GetMapping("/get/{dairyId}")
    public ResponseDTO getAllTransactionByDairyId(@PathVariable int dairyId) {
        ResponseDTO response=new ResponseDTO();
        try {
            response.data= farmerInvoiceService.getAllTransactionByDairyId(dairyId);
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
    @GetMapping("/get1/{dairyId}")
    public ResponseDTO getAllTransactionByDairyId1(@PathVariable int dairyId,@RequestParam String startDate, @RequestParam String  endDate) {
        ResponseDTO response=new ResponseDTO();
        try {
            response.data= farmerInvoiceService.getAllTransactionByDairyId1(dairyId,startDate,endDate);
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
    @GetMapping("/getByFarmerId/{farmerId}")
    public ResponseDTO getByFarmerId(@PathVariable int farmerId){
        ResponseDTO response=new ResponseDTO();
        try{
            response.data=farmerInvoiceService.getDataByFarmerId(farmerId);
            response.message="Success";
            response.success=true;
            response.status=200l;
        } catch (Exception e) {
            response.message="UnSuccess";
            response.success=false;
            response.status=500l;
        }
        return response;
    }
    @GetMapping("/getByFarmerId1/{farmerId}")
    public ResponseDTO getTransactionsByDairyIdAndDateRange(
            @PathVariable int farmerId,
            @RequestParam String startDate,   // dd/MM/yyyy
            @RequestParam String endDate      // dd/MM/yyyy
    ) {
        ResponseDTO response = new ResponseDTO();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);

            response.data = farmerInvoiceService.getDataDateByFarmerId(farmerId, start, end);
            response.message = "Success";
            response.success = true;
            response.status = 200L;
        } catch (Exception e) {
            response.message = "UnSuccess: " + e.getMessage();
            response.success = false;
            response.status = 500L;
        }
        return response;
    }

    @GetMapping("/update/{farmerId}")
    public ResponseDTO getByFarmer(@PathVariable int farmerId){
        ResponseDTO response=new ResponseDTO();
        try{
            response.data=farmerInvoiceService.getDataByFarmer(farmerId);
            response.message="Success";
            response.success=true;
            response.status=200l;
        } catch (Exception e) {
            response.message="UnSuccess";
            response.success=false;
            response.status=500l;
        }
        return response;
    }
}
