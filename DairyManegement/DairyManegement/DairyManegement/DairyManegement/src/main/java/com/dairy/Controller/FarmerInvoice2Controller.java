package com.dairy.Controller;

import com.dairy.Dto.ResponseDTO;
import com.dairy.Repository.FarmerInvoice2Respository;
import com.dairy.Services.FarmerInvoice2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/FarmerInvoice2")
public class FarmerInvoice2Controller {
    @Autowired
    private FarmerInvoice2Service service;
    @GetMapping("/get/{dairyId}")
    public ResponseDTO getAllTransactionByDairyId(@PathVariable int dairyId) {
        ResponseDTO response=new ResponseDTO();
        try {
            response.data= service.getAllTransactionByDairyId(dairyId);
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
    @GetMapping("/get2/{dairyId}")
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

            response.data = service.getAllTransactionByDate(dairyId, formattedStart, formattedEnd);
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
    @GetMapping("/getById/{farmer_id}/{id}")
    public ResponseDTO add(@PathVariable("farmer_id") int farmer_id,@PathVariable ("id")int id){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data= service.add2(farmer_id,id);
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

}
