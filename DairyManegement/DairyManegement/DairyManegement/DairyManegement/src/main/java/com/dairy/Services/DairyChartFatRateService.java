//package com.dairy.Services;
//
//import com.dairy.Entity.DairyChartFatRate;
//import com.dairy.Entity.DairyRateChartFatStep;
//import com.dairy.Repository.DairyChartFatRateRepository;
//import com.dairy.Repository.DairyRateChartFatStepRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//@Service
//public class DairyChartFatRateService {
//    @Autowired
//    private DairyChartFatRateRepository dairyChartFatRateRepository;
//    @Autowired
//    private  DairyRateChartFatStepRepository dairyRateChartFatStepRepository;
//
////    public DairyChartFatRate saveDataRate(DairyChartFatRate dairyChartFatRate,int id) {
////        Optional<DairyChartFatRate> fatRate = dairyChartFatRateRepository.findById(id);
////        if (fatRate.isPresent()) {
////            DairyChartFatRate rate = fatRate.get();
////            double rateFat = rate.getRate();
////            double fat=rate.getFat();
////            dairyChartFatRate.setFat(rateFat+dairyChartFatRate.getFat());
////            dairyChartFatRate.setFat(fat+dairyChartFatRate.getFat());
////            dairyChartFatRate.setRate(rateFat + dairyChartFatRate.getRate());
////            dairyChartFatRate.setCreateDate(LocalDateTime.now());
////            dairyChartFatRate.setUpdateDate(LocalDateTime.now());
////            return dairyChartFatRateRepository.save(dairyChartFatRate);
//////        } else if (fatRate==null) {
//////            dairyChartFatRate.setCreateDate(LocalDateTime.now());
//////            dairyChartFatRate.setUpdateDate(LocalDateTime.now());
//////            return dairyChartFatRateRepository.save(dairyChartFatRate);
////         }else {
////            throw new RuntimeException();
////        }
////
////
////
////
////    }
//
//public void saveDataRate(DairyChartFatRate dairyChartFatRate, int id) {
//    Optional<DairyChartFatRate> previousValue = dairyChartFatRateRepository.findById(id);
//
//    if (previousValue.isPresent()) {
//        DairyChartFatRate rate = previousValue.get();
//        double previousFat = rate.getFat();
//        double previousRate = rate.getRate();
//
//        double targetFat = dairyChartFatRate.getFat();
//        double targetRate = dairyChartFatRate.getRate();
//
//        double fatStep = 0.1;
//        double rateStep = 0.5;
//
//        for (double fat = previousFat, rateValue = previousRate;
//             fat <= targetFat && rateValue <= targetRate;
//             fat += fatStep, rateValue += rateStep) {
//
//            DairyChartFatRate newEntry = new DairyChartFatRate();
//            newEntry.setFat(fat);
//            newEntry.setRate(rateValue);
//            newEntry.setCreateDate(LocalDateTime.now());
//            newEntry.setUpdateDate(LocalDateTime.now());
//
//            dairyChartFatRateRepository.save(newEntry);
//        }
//    } else {
//        dairyChartFatRate.setCreateDate(LocalDateTime.now());
//        dairyChartFatRate.setUpdateDate(LocalDateTime.now());
//        dairyChartFatRateRepository.save(dairyChartFatRate);
//    }
//}
//
//}
