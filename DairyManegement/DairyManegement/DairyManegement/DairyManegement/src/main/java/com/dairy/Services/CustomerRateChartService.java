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
public class CustomerRateChartService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerRateChartService.class);

    @Autowired
    private CustomerRateChartRepository customerRateChartRepository;

    @Autowired
    private DairyRepository dairyRepository;

    @Autowired
    private CustomerRateFatRepository customerRateFatRepository;

    @Autowired
    private CustomerRateSnfRepository customerRateSnfRepository;

    @Autowired
    private CustomerRateClrRepository customerRateClrRepository;

    @Autowired
    private CollectionTypeMasterRepository collectionTypeMasterRepository;


    @Transactional
    public CustomerRateChartDTO saveAllRates(CustomerRateChartDTO requestDTO, Integer dairyRegId) {

        DairyRegistration dairyReg = dairyRepository.findById(dairyRegId)
                .orElseThrow(() -> new RuntimeException("Dairy Registration not found"));

        dairyReg.getCollectionTypeId();
        CustomerRateChart startingAmount = new CustomerRateChart();
        startingAmount.setStartingAmount(requestDTO.getCustomerRateChart().getStartingAmount());
        startingAmount.setClrIncrementBy(requestDTO.getCustomerRateChart().getClrIncrementBy());
        startingAmount.setMilkType(requestDTO.getCustomerRateChart().getMilkType());
        startingAmount.setCreatedDate(LocalDateTime.now());
        startingAmount.setUpdateDate(LocalDateTime.now());
        startingAmount.setDairyRegistration(dairyReg);


        CustomerRateChart customerRateChart = customerRateChartRepository.save(startingAmount);

        List<CustomerRateFat> savedFats = new ArrayList<>();
        if (requestDTO.getCustomerRateFats() != null) {
            for (CustomerRateFat fat : requestDTO.getCustomerRateFats()) {
                fat.setCustomerRateChart(customerRateChart);
                fat.setCreateDate(LocalDateTime.now());
                fat.setUpdateDate(LocalDateTime.now());
                savedFats.add(customerRateFatRepository.save(fat));
            }
        }

        List<CustomerRateSnf> savedSnfs = new ArrayList<>();
        if (requestDTO.getCustomerRateSnfs() != null) {
            for (CustomerRateSnf snf : requestDTO.getCustomerRateSnfs()) {
                snf.setCustomerRateChart(customerRateChart);
                snf.setCreateDate(LocalDateTime.now());
                snf.setUpdateDate(LocalDateTime.now());
                savedSnfs.add(customerRateSnfRepository.save(snf));
            }
        }

        List<CustomerRateClr> savedClrs = new ArrayList<>();
        if (requestDTO.getCustomerRateClrs() != null) {
            for (CustomerRateClr clr : requestDTO.getCustomerRateClrs()) {
                clr.setCustomerRateChart(customerRateChart);
                clr.setCreateDate(LocalDateTime.now());
                clr.setUpdateDate(LocalDateTime.now());
                savedClrs.add(customerRateClrRepository.save(clr));
            }
        }

        // Creating response DTO
        CustomerRateChartDTO responseDTO = new CustomerRateChartDTO();
        responseDTO.setCustomerRateChart(customerRateChart);
        responseDTO.setCustomerRateFats(savedFats);
        responseDTO.setCustomerRateSnfs(savedSnfs);
        responseDTO.setCustomerRateClrs(savedClrs);

        return responseDTO;
    }


    public CustomerRateStartingAmountDTO getRatesByDairyId(Integer dairyId, String milkType) {
        List<Object[]> results = customerRateChartRepository.findRatesByDairyId(milkType, dairyId);

        if (results.isEmpty()) {
            return null;
        }

        // Extract first row for DairyRateStartingAmountDTO
        Object[] firstRow = results.get(0);
        CustomerRateStartingAmountDTO1 startingAmount = new CustomerRateStartingAmountDTO1(
                (Double) firstRow[0],
                (Double) firstRow[1],
                (String) firstRow[2]
        );

        List<CustomerRateStepDTO> fats = results.stream()
                .map(row -> new CustomerRateStepDTO((Double) row[3], (Double) row[4]))
                .collect(Collectors.toList());

        List<CustomerRateStepDTO> snfs = results.stream()
                .map(row -> new CustomerRateStepDTO((Double) row[5], (Double) row[6]))
                .collect(Collectors.toList());

        List<CustomerRateStepDTO> clrs = results.stream()
                .map(row -> new CustomerRateStepDTO((Double) row[7], (Double) row[8]))
                .collect(Collectors.toList());

        return new CustomerRateStartingAmountDTO(startingAmount, fats, snfs, clrs);
    }

//    @Transactional
//    public CustomerRateChartDTO updateAllRates(CustomerRateChartDTO requestDTO, Integer dairyRegId, String milkType) {
//        DairyRegistration dairyReg = dairyRepository.findById(dairyRegId)
//                .orElseThrow(() -> new RuntimeException("Dairy Registration not found"));
//
//
//        Optional<CustomerRateChart> optionalStartingAmount =
//                customerRateChartRepository.findByDairyRegistrationAndMilkType(dairyReg, milkType);
//
//        if (!optionalStartingAmount.isPresent()) {
//            throw new RuntimeException("Milk Type '" + milkType + "' not found for the given Dairy Registration");
//        }
//
//
//        CustomerRateChart startingAmount = optionalStartingAmount.get();
//
//        startingAmount.setStartingAmount(requestDTO.getCustomerRateChart().getStartingAmount());
//        startingAmount.setClrIncrementBy(requestDTO.getCustomerRateChart().getClrIncrementBy());
//        startingAmount.setUpdateDate(LocalDateTime.now());
//        startingAmount.setDairyRegistration(dairyReg);
//
//        CustomerRateChart savedStartingAmount = customerRateChartRepository.save(startingAmount);
//
//
//        customerRateFatRepository.deleteByCustomerRateStartingAmount(savedStartingAmount);
//        customerRateSnfRepository.deleteByCustomerRateStartingAmount(savedStartingAmount);
//        customerRateClrRepository.deleteByCustomerRateStartingAmount(savedStartingAmount);
//
//
//        List<CustomerRateFat> fats = requestDTO.getCustomerRateFats();
//        if (fats != null) {
//            fats.forEach(fat -> {
//                fat.setCustomerRateChart(savedStartingAmount);
//                fat.setUpdateDate(LocalDateTime.now());
//            });
//            customerRateFatRepository.saveAll(fats);
//        }
//
//        List<CustomerRateSnf> snfs = requestDTO.getCustomerRateSnfs();
//        if (snfs != null) {
//            snfs.forEach(snf -> {
//                snf.setCustomerRateChart(savedStartingAmount);
//                snf.setUpdateDate(LocalDateTime.now());
//            });
//            customerRateSnfRepository.saveAll(snfs);
//        }
//
//        List<CustomerRateClr> clrs = requestDTO.getCustomerRateClrs();
//        if (clrs != null) {
//            clrs.forEach(clr -> {
//                clr.setCustomerRateChart(savedStartingAmount);
//                clr.setUpdateDate(LocalDateTime.now());
//            });
//            customerRateClrRepository.saveAll(clrs);
//        }
//
//
//        CustomerRateChartDTO responseDTO = new CustomerRateChartDTO();
//        responseDTO.setCustomerRateChart(savedStartingAmount);
//        responseDTO.setCustomerRateFats(fats);
//        responseDTO.setCustomerRateSnfs(snfs);
//        responseDTO.setCustomerRateClrs(clrs);
//
//        return responseDTO;
//    }

}
