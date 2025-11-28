package com.dairy.Controller;

import com.dairy.Dto.CustomerRateChartDTO;
import com.dairy.Dto.DairyRateChartDTO;
import com.dairy.Dto.ResponseDTO;
import com.dairy.Services.CustomerRateChartService;
import com.dairy.Services.DairyRateChartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/CustomerRateChart")
public class CustomerRateChartController {
    @Autowired
    private CustomerRateChartService customerRateChartService;




    @PostMapping("/save-all/{dairyRegId}")

    public ResponseDTO saveAllRates(@PathVariable Integer dairyRegId, @RequestBody CustomerRateChartDTO requestDTO) {
        ResponseDTO response = new ResponseDTO();
        try {
            response.data = customerRateChartService.saveAllRates(requestDTO, dairyRegId);
            response.message = "Success";
            response.success = true;
            response.status = 200L;
        } catch (Exception e) {
            response.message = e.getMessage();
            response.success = false;
            response.status = 400L;
        }
        return response;
    }


    @GetMapping("/{dairyId}/{milkType}")
    public ResponseDTO getDairyRates(@PathVariable Integer dairyId,@PathVariable String milkType) {
//        return dairyRateChartServices.getRatesByDairyId(dairyId);
        ResponseDTO response = new ResponseDTO();
        try {
            response.data = customerRateChartService.getRatesByDairyId(dairyId,milkType);
            response.message = "Success";
            response.success = true;
            response.status = 200L;
        } catch (Exception e) {
            response.message = e.getMessage();
            response.success = false;
            response.status = 400L;
        }
        return response;
    }

//    @PutMapping("/update/{dairyRegId}")
//    public ResponseDTO updateAllRates(@PathVariable Integer dairyRegId,
//                                      @RequestParam String milkType,
//                                      @RequestBody CustomerRateChartDTO requestDTO) {
//
//        ResponseDTO response = new ResponseDTO();
//        try {
//            response.data = customerRateChartService.updateAllRates(requestDTO, dairyRegId, milkType);
//            response.message = "Success";
//            response.success = true;
//            response.status = 200L;
//        } catch (Exception e) {
//            response.message = e.getMessage();
//            response.success = false;
//            response.status = 400L;
//        }
//        return response;
//    }
}
