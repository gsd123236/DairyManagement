package com.dairy.Controller;

import com.dairy.Dto.DairyRateChartDTO;
import com.dairy.Dto.DairyRateResponseDTO;
import com.dairy.Dto.ResponseDTO;
import com.dairy.Entity.DairyRegistration;
import com.dairy.Entity.FarmerMilkCollection;
import com.dairy.Services.DairyRateChartServices;
import org.springframework.beans.factory.annotation.  Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/RateChart")
public class DairyRateChartController {
    @Autowired
    private DairyRateChartServices dairyRateChartServices;




    @PostMapping("/save-all/{dairyRegId}")

    public ResponseDTO saveAllRates(@PathVariable Integer dairyRegId, @RequestBody DairyRateChartDTO requestDTO) {
        ResponseDTO response = new ResponseDTO();
        try {
            response.data = dairyRateChartServices.saveAllRates(requestDTO, dairyRegId);
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
            response.data = dairyRateChartServices.getRatesByDairyId(dairyId,milkType);
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
//    public ResponseEntity<DairyRateChartDTO> updateAllRates(
//            @RequestBody DairyRateChartDTO requestDTO,
//            @PathVariable Integer dairyRegId) {
//        DairyRateChartDTO updatedRates = dairyRateChartServices.updateAllRates(requestDTO, dairyRegId);
//        return ResponseEntity.ok(updatedRates);
//    }
@PutMapping("/update/{dairyRegId}")
public ResponseDTO updateAllRates(@PathVariable Integer dairyRegId,
        @RequestParam String milkType,
        @RequestBody DairyRateChartDTO requestDTO) {

//    DairyRateChartDTO updatedRates = dairyRateChartServices.updateAllRates(requestDTO, dairyRegId, milkType);
//    return ResponseEntity.ok(updatedRates);

    ResponseDTO response = new ResponseDTO();
    try {
        response.data = dairyRateChartServices.updateAllRates(requestDTO, dairyRegId, milkType);
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
//    @PutMapping("/dairy/{dairyRegId}/rate-chart/{milkTypeId}")
//    public ResponseEntity<DairyRateChartDTO> updateDairyRateChart(
//            @PathVariable Integer dairyRegId,
//            @PathVariable Integer milkTypeId,
//            @RequestBody DairyRateChartDTO requestDTO) {
//
//        DairyRateChartDTO updatedChart = dairyRateChartServices.updateAllRates(requestDTO, dairyRegId, milkTypeId);
//        return ResponseEntity.ok(updatedChart);
//    }



}
