package com.dairy.Controller;


import com.dairy.Dto.CustomerKhataDTO;
import com.dairy.Dto.ResponseDTO;
import com.dairy.Entity.CustomerKhata;
import com.dairy.Services.CustomerKhataService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/customerKhata")
@RestController
public class CustomerKhataController {
    @Autowired
    private CustomerKhataService service;

    @PostMapping("/add/{customerId}")
    public ResponseDTO add(@RequestBody CustomerKhata customer, @PathVariable int customerId) {
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=service.add(customer, customerId);
            response.message="Successfully";
            response.status=200l;
            response.success=true;

        } catch (Exception e) {
            response.success=false;
            response.message="Failed";
            response.status=500l;
        }
        return response;
    }

    @GetMapping("/getBalance/{customerId}")
    public ResponseDTO getCustomerBalanceById(@PathVariable int customerId) {

        ResponseDTO response=new ResponseDTO();
        try {
            response.data = service.getBalanceByCustomerId(customerId);
            response.message="Successfully";
            response.status=200l;
            response.success=true;

        } catch (Exception e) {
            response.success=false;
            response.message="Failed";
            response.status=500l;
        }
        return response;
    }

    @GetMapping("/customerStatement/{customerId}")
    public ResponseDTO getAllTransactionById(@PathVariable int customerId) {
        ResponseDTO response=new ResponseDTO();
        try {
            response.data =service.getAllTranscationByCustomerId(customerId);
            response.message="Successfully";
            response.status=200l;
            response.success=true;

        } catch (Exception e) {
            response.success=false;
            response.message="Failed";
            response.status=500l;
        }
        return response;
    }

//    @GetMapping("/customerStatementAll")
//    public ResponseDTO getAllTransaction(){
//        ResponseDTO response=new ResponseDTO();
//        try {
//            response.data = service.getAllTransaction();
//            response.message="Successfully";
//            response.status=200l;
//            response.success=true;
//
//        } catch (Exception e) {
//            response.success=false;
//            response.message="Failed";
//            response.status=500l;
//        }
//        return response;
//    }
//    @GetMapping("/GetCustomerByDateId/{customerId}")
//    public  ResponseDTO getTransactionByCustomerByDate(@PathVariable int customerId, @RequestParam String startDate,
//                                                       @RequestParam String endDate) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        LocalDate start = LocalDate.parse(startDate, formatter);
//        LocalDate end = LocalDate.parse(endDate, formatter);
//        ResponseDTO response=new ResponseDTO();
//        try{
//            response.data= service.getTransactionByDateByCustomerId(customerId, start, end);
//            response.message="Successfully";
//            response.status=200l;
//            response.success=true;
//
//        } catch (Exception e) {
//            response.success=false;
//            response.message="Failed";
//            response.status=500l;
//        }
//        return response;
//    }

    //    @GetMapping("/GetCustomerByDate")
//    public ResponseDTO getTransactionByDate(@RequestParam String startDate,@RequestParam String endDate){
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        LocalDate start = LocalDate.parse(startDate, formatter);
//        LocalDate end = LocalDate.parse(endDate, formatter);
//        ResponseDTO response=new ResponseDTO();
//        try {
//            response.data= service.getTransaction(start, end);
//            response.message="Successfully";
//            response.status=200l;
//            response.success=true;
//
//        } catch (Exception e) {
//            response.success=false;
//            response.message="Failed";
//            response.status=500l;
//        }
//        return response;
//    }
    @DeleteMapping("/delete/{id}")
    public ResponseDTO deleteByCustomerId(@PathVariable int id) {
        ResponseDTO response = new ResponseDTO();
        try {
            response.data = service.deleteByCustomerId(id);
            response.message = "Successfully";
            response.status = 200l;
            response.success = true;

        } catch (Exception e) {
            response.success = false;
            response.message = "Failed";
            response.status = 500l;
        }
        return response;
    }


}
