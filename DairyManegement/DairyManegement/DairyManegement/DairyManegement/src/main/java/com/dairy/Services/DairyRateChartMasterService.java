//package com.dairy.Services;
//
//import com.dairy.Entity.DairyRateChartFatStep;
//import com.dairy.Entity.DairyRateChartMaster;
//import com.dairy.Repository.DairyRateChartMasterRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//@Service
//public class DairyRateChartMasterService {
//
//    @Autowired
//    private DairyRateChartMasterRepository dairyRateChartMasterRepository;
//
//    public DairyRateChartMaster saveRateMaster(DairyRateChartMaster dairyRateChartMaster){
//        dairyRateChartMaster.setCreatedDate(LocalDateTime.now());
//        dairyRateChartMaster.setUpdateDate(LocalDateTime.now());
//       return dairyRateChartMasterRepository.save(dairyRateChartMaster);
//    }
//
//    public String updateData(DairyRateChartMaster dairyRateChartMaster){
//        if (dairyRateChartMaster.getId()==0){
//            return "Invalid Id: Update failed";
//        }
//
//        Optional<DairyRateChartMaster>updateData=dairyRateChartMasterRepository.findById(dairyRateChartMaster.getId());
//        if (updateData.isPresent()){
//            DairyRateChartMaster updateRecord=updateData.get();
//
//            if (dairyRateChartMaster.getDairyId()!=0){
//                updateRecord.setDairyId(dairyRateChartMaster.getDairyId());
//            }
//            if (dairyRateChartMaster.getRateChartId()!=0){
//                updateRecord.setRateChartId(dairyRateChartMaster.getRateChartId());
//            }
//            if (dairyRateChartMaster.getStartingAmount()!=0){
//                updateRecord.setStartingAmount(dairyRateChartMaster.getStartingAmount());
//            }
//            updateRecord.setUpdateDate(LocalDateTime.now());
//            dairyRateChartMasterRepository.save(updateRecord);
//            return "Your Data Update Successfully....";
//        }else {
//            return "Record Not Found For Update.....";
//        }
//
//
//    }
//
//    public DairyRateChartMaster activeRateChart(int id){
//        Optional<DairyRateChartMaster> dairyRate=dairyRateChartMasterRepository.findById(id);
//        if (dairyRate.isPresent()){
//            DairyRateChartMaster dairyRateChartMaster=dairyRate.get();
//            dairyRate.get().setActive(true);
//            dairyRate.get().setDelete(false);
//            return dairyRateChartMasterRepository.save(dairyRateChartMaster);
//        }else {
//            throw  new RuntimeException("Milk Collection Id "+id+"Not Found");
//        }
//    }
//
//    public DairyRateChartMaster deactiveRateChart(int id){
//        Optional<DairyRateChartMaster> dairyRate=dairyRateChartMasterRepository.findById(id);
//        if (dairyRate.isPresent()){
//            DairyRateChartMaster dairyRateChar=dairyRate.get();
//            dairyRate.get().setActive(true);
//            dairyRate.get().setDelete(false);
//            return dairyRateChartMasterRepository.save(dairyRateChar);
//        }else {
//            throw  new RuntimeException("Milk Collection Id "+id+"Not Found");
//        }
//    }
//
//
//}
