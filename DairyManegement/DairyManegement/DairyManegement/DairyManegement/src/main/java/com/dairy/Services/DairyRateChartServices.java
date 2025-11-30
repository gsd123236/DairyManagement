package com.dairy.Services;
import com.dairy.Dto.*;
import com.dairy.Entity.*;
import com.dairy.Repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DairyRateChartServices{
    private static final Logger logger = LoggerFactory.getLogger(DairyRateChartServices.class);

    @Autowired
    private DairyRateStartingAmountRepository dairyRateStartingAmountRepository;

    @Autowired
    private DairyRepository dairyRepository;

    @Autowired
    private DairyRateFatRepository dairyRateFatRepository;

    @Autowired
    private DairyRateSnfRepository dairyRateSnfRepository;

    @Autowired
    private DairyRateClrRepository dairyRateClrRepository;

    @Autowired
    private CollectionTypeMasterRepository collectionTypeMasterRepository;


    @Transactional
    public DairyRateChartDTO saveAllRates(DairyRateChartDTO requestDTO, Integer dairyRegId) {

        DairyRegistration dairyReg = dairyRepository.findById(dairyRegId)
                .orElseThrow(() -> new RuntimeException("Dairy Registration not found"));

        dairyReg.getCollectionTypeId();
        DairyRateStartingAmount startingAmount = new DairyRateStartingAmount();
        startingAmount.setStartingAmount(requestDTO.getDairyRateStartingAmount().getStartingAmount());
        startingAmount.setClrIncrementBy(requestDTO.getDairyRateStartingAmount().getClrIncrementBy());
        startingAmount.setMilkType(requestDTO.getDairyRateStartingAmount().getMilkType());
        startingAmount.setCreatedDate(LocalDateTime.now());
        startingAmount.setUpdateDate(LocalDateTime.now());
        startingAmount.setDairyRegistration(dairyReg);


        DairyRateStartingAmount savedStartingAmount = dairyRateStartingAmountRepository.save(startingAmount);

        List<DairyRateFat> savedFats = new ArrayList<>();
        if (requestDTO.getDairyRateFats() != null) {
            for (DairyRateFat fat : requestDTO.getDairyRateFats()) {
                fat.setDairyRateStartingAmount(savedStartingAmount);
                fat.setCreateDate(LocalDateTime.now());
                fat.setUpdateDate(LocalDateTime.now());
                savedFats.add(dairyRateFatRepository.save(fat));
            }
        }

        List<DairyRateSnf> savedSnfs = new ArrayList<>();
        if (requestDTO.getDairyRateSnfs() != null) {
            for (DairyRateSnf snf : requestDTO.getDairyRateSnfs()) {
                snf.setDairyRateStartingAmount(savedStartingAmount);
                snf.setCreateDate(LocalDateTime.now());
                snf.setUpdateDate(LocalDateTime.now());
                savedSnfs.add(dairyRateSnfRepository.save(snf));
            }
        }

        List<DairyRateClr> savedClrs = new ArrayList<>();
        if (requestDTO.getDairyRateClrs() != null) {
            for (DairyRateClr clr : requestDTO.getDairyRateClrs()) {
                clr.setDairyRateStartingAmount(savedStartingAmount);
                clr.setCreateDate(LocalDateTime.now());
                clr.setUpdateDate(LocalDateTime.now());
                savedClrs.add(dairyRateClrRepository.save(clr));
            }
        }

        // Creating response DTO
        DairyRateChartDTO responseDTO = new DairyRateChartDTO();
        responseDTO.setDairyRateStartingAmount(savedStartingAmount);
        responseDTO.setDairyRateFats(savedFats);
        responseDTO.setDairyRateSnfs(savedSnfs);
        responseDTO.setDairyRateClrs(savedClrs);

        return responseDTO;
    }


//    public DairyRateResponseDTO getRatesByDairyId(Integer dairyId) {
//        List<Object[]> results = dairyRateStartingAmountRepository.findRatesByDairyId(dairyId);
//
//        if (results.isEmpty()) {
//            return null;
//        }
//
//        // Extract first row for DairyRateStartingAmountDTO
//        Object[] firstRow = results.get(0);
//        DairyRateStartingAmountDTO1 startingAmount = new DairyRateStartingAmountDTO1(
//                (Double) firstRow[0],
//                (Double) firstRow[1],
//                (String) firstRow[2]
//        );
//
//        // Extract DairyRateStepDTO for fats, snfs, and clrs
//        List<DairyRateStepDTO> fats = results.stream()
//                .map(row -> new DairyRateStepDTO((Double) row[3], (Double) row[4]))
//                .collect(Collectors.toList());
//
//        List<DairyRateStepDTO> snfs = results.stream()
//                .map(row -> new DairyRateStepDTO((Double) row[5], (Double) row[6]))
//                .collect(Collectors.toList());
//
//        List<DairyRateStepDTO> clrs = results.stream()
//                .map(row -> new DairyRateStepDTO((Double) row[7], (Double) row[8]))
//                .collect(Collectors.toList());
//
//        return new DairyRateResponseDTO(startingAmount, fats, snfs, clrs);
//    }

    public DairyRateResponseDTO getRatesByDairyId(Integer dairyId, String milkType) {
        List<Object[]> results = dairyRateStartingAmountRepository.findRatesByDairyId(milkType, dairyId);

        if (results.isEmpty()) {
            return null;
        }

        // Extract first row for DairyRateStartingAmountDTO
        Object[] firstRow = results.get(0);
        DairyRateStartingAmountDTO1 startingAmount = new DairyRateStartingAmountDTO1(
                (Double) firstRow[0],
                (Double) firstRow[1],
                (String) firstRow[2]
        );

        // Extract DairyRateStepDTO for fats, snfs, and clrs
        List<DairyRateStepDTO> fats = results.stream()
                .map(row -> new DairyRateStepDTO((Double) row[3], (Double) row[4]))
                .collect(Collectors.toList());

        List<DairyRateStepDTO> snfs = results.stream()
                .map(row -> new DairyRateStepDTO((Double) row[5], (Double) row[6]))
                .collect(Collectors.toList());

        List<DairyRateStepDTO> clrs = results.stream()
                .map(row -> new DairyRateStepDTO((Double) row[7], (Double) row[8]))
                .collect(Collectors.toList());

        return new DairyRateResponseDTO(startingAmount, fats, snfs, clrs);
    }



//    public DairyRateChartDTO getAllRates(Integer dairyRegId) {
//        DairyRateStartingAmount dairyReg = dairyRateStartingAmountRepository.findByDairyRegistration(dairyRegId);
//                .orElseThrow(() -> new RuntimeException("Dairy Registration not found"));
//
    ////        DairyRateStartingAmount startingAmount = (DairyRateStartingAmount) dairyRateStartingAmountRepository.findByDairyRegistration(dairyReg)
    ////                .orElseThrow(() -> new RuntimeException("Rate Chart not found"));
//
//        List<DairyRateFat> fats = dairyRateFatRepository.findByDairyRateStartingAmount(dairyReg);
//        List<DairyRateSnf> snfs = dairyRateSnfRepository.findByDairyRateStartingAmount(dairyReg);
//        List<DairyRateClr> clrs = dairyRateClrRepository.findByDairyRateStartingAmount(dairyReg);
//
//        // Creating response DTO
//        DairyRateChartDTO responseDTO = new DairyRateChartDTO();
//        responseDTO.setDairyRateStartingAmount(dairyReg);
//        responseDTO.setDairyRateFats(fats);
//        responseDTO.setDairyRateSnfs(snfs);
//        responseDTO.setDairyRateClrs(clrs);
//
//        return responseDTO;
//    }

//    @Transactional
//    public DairyRateChartDTO updateAllRates(DairyRateChartDTO requestDTO, Integer dairyRegId) {
//        DairyRegistration dairyReg = dairyRepository.findById(dairyRegId)
//                .orElseThrow(() -> new RuntimeException("Dairy Registration not found"));
//
//
//        DairyRateStartingAmount startingAmount = dairyRateStartingAmountRepository
//                .findByDairyRegistration(dairyReg)
//                .orElse(new DairyRateStartingAmount());
//
//        startingAmount.setStartingAmount(requestDTO.getDairyRateStartingAmount().getStartingAmount());
//        startingAmount.setClrIncrementBy(requestDTO.getDairyRateStartingAmount().getClrIncrementBy());
//        startingAmount.setMilkType(requestDTO.getDairyRateStartingAmount().getMilkType());
//        startingAmount.setUpdateDate(LocalDateTime.now());
//        startingAmount.setDairyRegistration(dairyReg);
//
//        DairyRateStartingAmount savedStartingAmount = dairyRateStartingAmountRepository.save(startingAmount);
//
//        List<DairyRateFat> fats = requestDTO.getDairyRateFats();
//        if (fats != null) {
//            fats.forEach(fat -> {
//                fat.setDairyRateStartingAmount(savedStartingAmount);
//                fat.setUpdateDate(LocalDateTime.now());
//            });
//            dairyRateFatRepository.saveAll(fats);
//        }
//
//        List<DairyRateSnf> snfs = requestDTO.getDairyRateSnfs();
//        if (snfs != null) {
//            snfs.forEach(snf -> {
//                snf.setDairyRateStartingAmount(savedStartingAmount);
//                snf.setUpdateDate(LocalDateTime.now());
//            });
//            dairyRateSnfRepository.saveAll(snfs);
//        }
//
//        List<DairyRateClr> clrs = requestDTO.getDairyRateClrs();
//        if (clrs != null) {
//            clrs.forEach(clr -> {
//                clr.setDairyRateStartingAmount(savedStartingAmount);
//                clr.setUpdateDate(LocalDateTime.now());
//            });
//            dairyRateClrRepository.saveAll(clrs);
//        }
//
//        DairyRateChartDTO responseDTO = new DairyRateChartDTO();
//        responseDTO.setDairyRateStartingAmount(savedStartingAmount);
//        responseDTO.setDairyRateFats(fats);
//        responseDTO.setDairyRateSnfs(snfs);
//        responseDTO.setDairyRateClrs(clrs);
//
//        return responseDTO;
//    }

    @Transactional
    public DairyRateChartDTO updateAllRates(DairyRateChartDTO requestDTO, Integer dairyRegId, String milkType) {
        DairyRegistration dairyReg = dairyRepository.findById(dairyRegId)
                .orElseThrow(() -> new RuntimeException("Dairy Registration not found"));


        Optional<DairyRateStartingAmount> optionalStartingAmount =
                dairyRateStartingAmountRepository.findByDairyRegistrationAndMilkType(dairyReg, milkType);

        if (!optionalStartingAmount.isPresent()) {
            throw new RuntimeException("Milk Type '" + milkType + "' not found for the given Dairy Registration");
        }


        DairyRateStartingAmount startingAmount = optionalStartingAmount.get();

        startingAmount.setStartingAmount(requestDTO.getDairyRateStartingAmount().getStartingAmount());
        startingAmount.setClrIncrementBy(requestDTO.getDairyRateStartingAmount().getClrIncrementBy());
        startingAmount.setUpdateDate(LocalDateTime.now());
        startingAmount.setDairyRegistration(dairyReg);

        DairyRateStartingAmount savedStartingAmount = dairyRateStartingAmountRepository.save(startingAmount);


        dairyRateFatRepository.deleteByDairyRateStartingAmount(savedStartingAmount);
        dairyRateSnfRepository.deleteByDairyRateStartingAmount(savedStartingAmount);
        dairyRateClrRepository.deleteByDairyRateStartingAmount(savedStartingAmount);


        List<DairyRateFat> fats = requestDTO.getDairyRateFats();
        if (fats != null) {
            fats.forEach(fat -> {
                fat.setDairyRateStartingAmount(savedStartingAmount);
                fat.setUpdateDate(LocalDateTime.now());
            });
            dairyRateFatRepository.saveAll(fats);
        }

        List<DairyRateSnf> snfs = requestDTO.getDairyRateSnfs();
        if (snfs != null) {
            snfs.forEach(snf -> {
                snf.setDairyRateStartingAmount(savedStartingAmount);
                snf.setUpdateDate(LocalDateTime.now());
            });
            dairyRateSnfRepository.saveAll(snfs);
        }

        List<DairyRateClr> clrs = requestDTO.getDairyRateClrs();
        if (clrs != null) {
            clrs.forEach(clr -> {
                clr.setDairyRateStartingAmount(savedStartingAmount);
                clr.setUpdateDate(LocalDateTime.now());
            });
            dairyRateClrRepository.saveAll(clrs);
        }


        DairyRateChartDTO responseDTO = new DairyRateChartDTO();
        responseDTO.setDairyRateStartingAmount(savedStartingAmount);
        responseDTO.setDairyRateFats(fats);
        responseDTO.setDairyRateSnfs(snfs);
        responseDTO.setDairyRateClrs(clrs);

        return responseDTO;
    }


//    @Transactional
//    public DairyRateChartDTO updateAllRates(DairyRateChartDTO requestDTO, Integer dairyRegId, Integer milkTypeId) {
//        DairyRegistration dairyReg = dairyRepository.findById(dairyRegId)
//                .orElseThrow(() -> new RuntimeException("Dairy Registration not found"));
//
//        // Fetch milkType entity if needed
//        MilkType milkType = dairyRateStartingAmountRepository.findById(milkTypeId)
//                .orElseThrow(() -> new RuntimeException("Milk Type not found"));
//
//        DairyRateStartingAmount startingAmount = dairyRateStartingAmountRepository
//                .getReferenceById(dairyReg, milkType)
//                .orElse(new DairyRateStartingAmount());
//
//        startingAmount.setStartingAmount(requestDTO.getDairyRateStartingAmount().getStartingAmount());
//        startingAmount.setClrIncrementBy(requestDTO.getDairyRateStartingAmount().getClrIncrementBy());
//        startingAmount.setMilkType(milkType);
//        startingAmount.setUpdateDate(LocalDateTime.now());
//        startingAmount.setDairyRegistration(dairyReg);
//
//        DairyRateStartingAmount savedStartingAmount = dairyRateStartingAmountRepository.save(startingAmount);
//
//        // Set and save fats, snfs, clrs...
//        // (same as before)
//
//        DairyRateChartDTO responseDTO = new DairyRateChartDTO();
//        responseDTO.setDairyRateStartingAmount(savedStartingAmount);
//        responseDTO.setDairyRateFats(requestDTO.getDairyRateFats());
//        responseDTO.setDairyRateSnfs(requestDTO.getDairyRateSnfs());
//        responseDTO.setDairyRateClrs(requestDTO.getDairyRateClrs());
//
//        return responseDTO;
//    }
//





}
