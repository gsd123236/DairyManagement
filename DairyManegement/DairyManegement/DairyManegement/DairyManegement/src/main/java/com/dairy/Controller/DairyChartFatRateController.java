//package com.dairy.Controller;
//
//import com.dairy.Entity.DairyChartFatRate;
//import com.dairy.Services.DairyChartFatRateService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/fatRate")
//public class DairyChartFatRateController {
//
//    @Autowired
//    private DairyChartFatRateService dairyChartFatRateService;
//
//    @PostMapping("addRateFat")
//    public String saveDataFAtRate(@RequestBody DairyChartFatRate dairyChartFatRate,@RequestParam int id){
//        dairyChartFatRateService.saveDataRate(dairyChartFatRate,id);
//        return "Save Successfully......";
//    }
//}
