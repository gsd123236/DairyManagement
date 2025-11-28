package com.dairy.Controller;

import com.dairy.Dto.MilkSaleDTO;
import com.dairy.Dto.ResponseDTO;
import com.dairy.Entity.CustomerMilkSale;
import com.dairy.Services.CustomerMilkSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customerMilkSale")
public class CustomerMilkSaleController {

    @Autowired
    private CustomerMilkSaleService service;
    @PostMapping("/add/{dId}")
    public ResponseDTO saveMilk(@RequestBody CustomerMilkSale customerMilkSale, @PathVariable int dId) {
        ResponseDTO response = new ResponseDTO();
        try {
            response.data = service.add(customerMilkSale, dId);
            response.message = "Success";
            response.success = true;
            response.status = 200L;
        } catch (Exception e) {
            response.message = "Failed: " + e.getMessage();
            response.success = false;
            response.status = 500L;
        }
        return response;
    }

    @GetMapping("/getById/{id}")
    public ResponseDTO getById(@PathVariable int id){
        ResponseDTO response=new ResponseDTO();
        try{
            response.data=service.getById(id);
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
    @GetMapping("/getByDateAndShift")
    public ResponseDTO getByDateShift(@RequestParam String date, @RequestParam int id){ //This id is shift id. i.e morning shift Or Evening shift
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate d = LocalDate.parse(date, formatter);

        ResponseDTO response=new ResponseDTO();
        try{
            response.data=service.getByDate(d,id);
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
    @GetMapping("/getAll")
    public ResponseDTO getAll(){
        ResponseDTO response=new ResponseDTO();
        try{
            response.data=service.getAll();
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
    @GetMapping("/getByDate/{dId}")
    public ResponseDTO getByDate(@PathVariable Integer dId, @RequestParam String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate parsedDate = LocalDate.parse(date, formatter);

        ResponseDTO response = new ResponseDTO();
        try {
            List<Map<String, Object>> data= service.getByDate(parsedDate, dId);
            response.data = data;
            response.message = "Success";
            response.success = true;
            response.status = 200L;
        } catch (Exception e) {
            e.printStackTrace();
            response.message = "Failed";
            response.success = false;
            response.status = 500L;
        }
        return response;
    }

    @GetMapping("/getByInvoice/{dId}")
    public ResponseDTO getByCustomerInvoice(@PathVariable Integer dId, @RequestParam String startDate,@RequestParam String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate parsedDate = LocalDate.parse(startDate,formatter);
        LocalDate parsedDate1 = LocalDate.parse(endDate,formatter);

        ResponseDTO response = new ResponseDTO();
        try {
            List<Map<String, Object>> data= service.getByCustomerMilkInvoice(parsedDate, parsedDate1,dId);
            response.data = data;
            response.message = "Success";
            response.success = true;
            response.status = 200L;
        } catch (Exception e) {
            e.printStackTrace();
            response.message = "Failed";
            response.success = false;
            response.status = 500L;
        }
        return response;
    }

}


