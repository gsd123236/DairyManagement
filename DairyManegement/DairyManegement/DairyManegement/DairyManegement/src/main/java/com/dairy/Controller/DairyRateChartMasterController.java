//package com.dairy.Controller;
//
//import com.dairy.Entity.DairyRateChartMaster;
//import com.dairy.Services.DairyRateChartMasterService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/rateMaster")
//public class DairyRateChartMasterController {
//
//    @Autowired
//    private DairyRateChartMasterService dairyRateChartMasterService;
//
//    @PostMapping("/addMaster")
//    public String saveMaster(@RequestBody DairyRateChartMaster master){
//         dairyRateChartMasterService.saveRateMaster(master);
//         return "Save Master Successfully.......";
//    }
//    @PutMapping("/updateData")
//    public String updateData(@RequestBody DairyRateChartMaster dairyRateChartMaster){
//        dairyRateChartMasterService.updateData(dairyRateChartMaster);
//        return "Successfully update....";
//    }
//
//    @PutMapping("/activeRate")
//    public DairyRateChartMaster activeFarmerRate(@PathVariable int id){
//        return dairyRateChartMasterService.activeRateChart(id);
//    }
//
//    @PutMapping("/deactivateRate")
//    public DairyRateChartMaster deactivateFarmerRate(@PathVariable int id){
//        return dairyRateChartMasterService.deactiveRateChart(id);
//    }
//}
