//package com.dairy.Services;
//import com.dairy.Entity.DairyRateChartFatStep;
//import com.dairy.Entity.DairyRateChartMaster;
//import com.dairy.Repository.DairyRateChartFatStepRepository;
//import com.dairy.Repository.DairyRateChartMasterRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class DairyRateChartFatService {
//
//    @Autowired
//    private DairyRateChartFatStepRepository repository;
//
//    @Autowired
//    private DairyRateChartMasterRepository dairyRateChart;
//
////    public DairyRateChartFatStep saveDairyRateChartFatStep(DairyRateChartFatStep dairyRateChartFatStep,
////                                                           Integer dairyId) {
////        Optional<DairyRateChartMaster> rate=dairyRateChart.findByID(dairyId);
////        if (rate.isPresent()){
////                DairyRateChartMaster dairyRateChartMaster=rate.get();
////
////                dairyRateChartFatStep.setStepAmount(dairyRateChartMaster.getStartingAmount());
////                dairyRateChartFatStep.setCreateDate(LocalDateTime.now());
////                dairyRateChartFatStep.setUpdateDate(LocalDateTime.now());
////                return repository.save(dairyRateChartFatStep);
////        }else {
////            throw new RuntimeException("RateChartMaster with ID " + dairyId + " not found.");
////        }
////    }
//
//    public DairyRateChartFatStep saveDairyRateChartFatStep(DairyRateChartFatStep dairyRateChartFatStep,
//                                                           Integer dairyId) {
//        Optional<DairyRateChartMaster> rate = dairyRateChart.findByID(dairyId);
//
//        if (rate.isPresent()) {
//            DairyRateChartMaster dairyRateChartMaster = rate.get();
//
//            double startingAmount = dairyRateChartMaster.getStartingAmount();
//            if (dairyRateChartFatStep.getStepAmount() < startingAmount) {
//                throw new RuntimeException("Step amount must be greater " + startingAmount + "than or equal to ");
//            }
//            dairyRateChartFatStep.setCreateDate(LocalDateTime.now());
//            dairyRateChartFatStep.setUpdateDate(LocalDateTime.now());
//
//            return repository.save(dairyRateChartFatStep);
//        } else {
//            throw new RuntimeException("RateChartMaster with ID " + dairyId + " not found.");
//        }
//    }
//
//
//    public List<DairyRateChartFatStep> getAllDairyRateChartFatSteps() {
//        return repository.findAll();
//    }
//
//
//    public Optional<DairyRateChartFatStep> getDairyRateChartFatStepById(int id) {
//        return repository.findById(id);
//    }
//
//
//}
//
