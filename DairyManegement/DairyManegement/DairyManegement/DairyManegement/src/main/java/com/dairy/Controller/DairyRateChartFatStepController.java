//
//package com.dairy.Controller;
//
//import com.dairy.Entity.DairyRateChartFatStep;
//import com.dairy.Services.DairyRateChartFatService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/dairy-fat-step")
//public class DairyRateChartFatStepController {
//
//    @Autowired
//    private DairyRateChartFatService service;
//
//    @PostMapping("/add")
//    public DairyRateChartFatStep createDairyRateChartFatStep(@RequestBody DairyRateChartFatStep dairyRateChartFatStep ,
//                                                             @RequestParam Integer dairyId) {
//        return service.saveDairyRateChartFatStep(dairyRateChartFatStep,dairyId);
//    }
//
//
//    @GetMapping("/all")
//    public List<DairyRateChartFatStep> getAllDairyRateChartFatSteps() {
//        return service.getAllDairyRateChartFatSteps();
//    }
//
//
//    @GetMapping("/getId/{id}")
//    public Optional<DairyRateChartFatStep> getDairyRateChartFatStepById(@PathVariable int id) {
//        return service.getDairyRateChartFatStepById(id);
//    }
//
//
//
//
//
//}
