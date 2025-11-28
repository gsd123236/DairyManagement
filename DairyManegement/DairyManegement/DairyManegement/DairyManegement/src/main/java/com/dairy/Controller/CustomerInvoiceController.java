package com.dairy.Controller;

import com.dairy.Dto.ResponseDTO;
import com.dairy.Entity.CustomerInvoice;
import com.dairy.Services.CustomerInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/Invoice")
public class CustomerInvoiceController {
    @Autowired
    private CustomerInvoiceService customerInvoiceService;
    @GetMapping("/getById/{customer_id}")
    public ResponseDTO add(@PathVariable("customer_id") int customer_id){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data= customerInvoiceService.add(customer_id);
            response.message="Successfully";
            response.success=true;
            response.status=200l;
        } catch (Exception e) {
            response.message="Failed";
            response.status=500l;
            response.success=false;
        }
        return response;

    }
    @GetMapping("/getById1/{customer_id}")
    public ResponseDTO add1(@PathVariable("customer_id") int customer_id){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data= customerInvoiceService.generatedInvoice(customer_id);
            response.message="Successfully";
            response.success=true;
            response.status=200l;
        } catch (Exception e) {
            response.message="Failed";
            response.status=500l;
            response.success=false;
        }
        return response;

    }
    @GetMapping("/get5/{dairyId}")
    public ResponseDTO getTransactionsByDairyIdAndDateRange(
            @PathVariable int dairyId,
            @RequestParam String startDate,   // in dd/MM/yyyy format
            @RequestParam String endDate      // in dd/MM/yyyy format
    ) {
        ResponseDTO response = new ResponseDTO();
        try {
            // Convert dd/MM/yyyy → yyyy-MM-dd
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter dbFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            LocalDate start = LocalDate.parse(startDate, inputFormatter);
            LocalDate end = LocalDate.parse(endDate, inputFormatter);

            // Convert back to string for repository (since your repo uses String)
            String formattedStart = start.format(dbFormatter);
            String formattedEnd = end.format(dbFormatter);

            response.data = customerInvoiceService.getAllTransactionByDate(dairyId, formattedStart, formattedEnd);
            response.message = "Success";
            response.success = true;
            response.status = 200L;
        } catch (Exception e) {
            response.message = "Unsuccessful: " + e.getMessage();
            response.success = false;
            response.status = 500L;
        }
        return response;
    }

    @GetMapping("/getByDate/{customer_id}")
    public ResponseDTO addByDate(@PathVariable("customer_id")int customer_id, @RequestParam String startDate,@RequestParam String endDate,
                                 @RequestBody CustomerInvoice customerInvoice){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        ResponseDTO response=new ResponseDTO();
        try{
            response.data=customerInvoiceService.getByDate(customer_id,start,end, customerInvoice);
            response.success=true;
            response.status=200l;
            response.message="Successfull";
        } catch (Exception e) {
            response.status=500l;
            response.success=false;
            response.message="Failed";
        }
        return response;
    }
    @GetMapping("/get/{dairyId}")
    public ResponseDTO getAllTransactionByDairyId(@PathVariable int dairyId) {
        ResponseDTO response=new ResponseDTO();
        try {
            response.data= customerInvoiceService.getAllTransactionByDairyId(dairyId);
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
    public ResponseDTO getAllTransactionByDairyId1(@PathVariable int dairyId) {
        ResponseDTO response=new ResponseDTO();
        try {
            response.data= customerInvoiceService.getAllTransactionByDairyId1(dairyId);
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
