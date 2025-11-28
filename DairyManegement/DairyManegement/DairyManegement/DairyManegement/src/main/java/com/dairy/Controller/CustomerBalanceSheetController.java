package com.dairy.Controller;

import com.dairy.Dto.ResponseDTO;
import com.dairy.Services.CustomerBalanceSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/CustomerBalanceSheet")
public class CustomerBalanceSheetController {
    @Autowired
    private CustomerBalanceSheetService service;

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
}
