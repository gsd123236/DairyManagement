package com.dairy.Services;

import com.dairy.Entity.*;
import com.dairy.Repository.*;
import com.twilio.rest.api.v2010.account.availablephonenumbercountry.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FarmerInvoiceService {
    @Autowired
    private FarmerInvoiceRepository repository;
    @Autowired
    private FarmerAccountRepository farmerAccountRepository;
    @Autowired
    private  FarmerInvoice2Respository farmerInvoice2Respository;
    @Autowired
    private FarmerRegistrationRepository farmerRegistrationRepository;
    @Autowired
    private DairyRepository dairyRepository;
    @Autowired
    private  FarmerMilkCollectionRepository farmerMilkCollectionRepository;


    public FarmerInvoice farmerInvoiceSave(  FarmerInvoice farmerInvoice){
        return repository.save(farmerInvoice);
    }

    public List<Map<String, Object>> getAllTransactionByDairyId(int dairyId) {
        List<FarmerInvoice> transactions = repository.getBalanceByDairyId(dairyId);
        return transactions.stream().map(transaction -> {
            Map<String, Object> map = new HashMap<>();
            map.put("farmerId",transaction.getFarmerRegistration().getId());
            map.put("farmerCode", transaction.getFarmerRegistration().getCode());
            map.put("farmerName", transaction.getFarmerRegistration().getFirstName()+" "+transaction.getFarmerRegistration().getLastName());
            map.put("milkAmount", String.valueOf(transaction.getMilkAmount()));
            return map;
        }).collect(Collectors.toList());
    }
    public List<Map<String, Object>> getAllTransactionByDairyId1(int dairyId, String startDateStr, String endDateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Convert incoming dd/MM/yyyy to LocalDate
        LocalDate startDate = LocalDate.parse(startDateStr, formatter);
        LocalDate endDate = LocalDate.parse(endDateStr, formatter);

        List<FarmerInvoice> transactions = repository.getAllListDairyId(dairyId, startDate, endDate);

        return transactions.stream().map(transaction -> {
            Map<String, Object> map = new HashMap<>();
            map.put("farmerId", transaction.getFarmerRegistration().getId());
            map.put("farmerCode", transaction.getFarmerRegistration().getCode());
            map.put("farmerName", transaction.getFarmerRegistration().getFirstName() + " " + transaction.getFarmerRegistration().getLastName());
            map.put("milkAmount", String.valueOf(transaction.getMilkAmount()));
            return map;
        }).collect(Collectors.toList());
    }

    public Optional<FarmerInvoice> getDataByFarmerId(int farmerId) {
        FarmerInvoice farmerInvoice = repository.findByFarmerId(farmerId)
                .orElseGet(() -> {
                    FarmerRegistration farmer = farmerRegistrationRepository.findById(farmerId)
                            .orElseThrow(() -> new RuntimeException("Farmer not found"));
                    FarmerInvoice fi = new FarmerInvoice();
                    fi.setFarmerRegistration(farmer);
                    return fi;
                });

        // ✅ Get total milk amount (sum) for this farmer
        Double totalMilk = farmerMilkCollectionRepository.getTotalMilkAmountByFarmerId(farmerId);
        farmerInvoice.setMilkAmount(totalMilk != null ? totalMilk : 0.0);

        // ✅ Balance calculation
        Double balanceObj = farmerAccountRepository.getByBalance(farmerId);
        double balance = balanceObj != null ? balanceObj : 0.0;

        if (balance > 0) {
            farmerInvoice.setAmountDeposite(balance);
            farmerInvoice.setAmountdeducation(0.0);
        } else {
            farmerInvoice.setAmountdeducation(Math.abs(balance));
            farmerInvoice.setAmountDeposite(0.0);
        }

        double val = farmerInvoice.getMilkAmount() + balance;
        if (val > 0) {
            farmerInvoice.setBalance(val);
            farmerInvoice.setAmountdue(0.0);
        } else {
            farmerInvoice.setBalance(0.0);
            farmerInvoice.setAmountdue(Math.abs(val));
        }

        repository.save(farmerInvoice);
        return repository.findByFarmerId(farmerId);
    }

    //    public Optional<FarmerInvoice> getDataByFarmer(int farmerId) {
//        FarmerInvoice farmerInvoice = repository.findByFarmerId(farmerId)
//                .orElse(new FarmerInvoice());
//
//        // ✅ get total milk amount sum
//        double totalMilk = farmerMilkCollectionRepository.getTotalMilkAmountByFarmerId(farmerId);
//        farmerInvoice.setMilkAmount(totalMilk);
//
//        double balance = farmerAccountRepository.getByBalance(farmerId);
//        if (balance > 0) {
//            farmerInvoice.setAmountDeposite(balance);
//            farmerInvoice.setAmountdeducation(0.0);
//        } else {
//            farmerInvoice.setAmountdeducation(Math.abs(balance));
//            farmerInvoice.setAmountDeposite(0.0);
//        }
//
//        double val = totalMilk + balance; // ✅ use sum instead of single invoice value
//        if (val > 0) {
//            farmerInvoice.setBalance(val);
//            farmerInvoice.setAmountdue(0.0);
//        } else {
//            farmerInvoice.setBalance(0.0);
//            farmerInvoice.setAmountdue(Math.abs(val));
//        }
//
//        farmerInvoice.setPendingStatus(true);
//
//        generatedInvoice2(farmerId, totalMilk);
//        repository.save(farmerInvoice);
//
//        return repository.findByFarmerId(farmerId);
//    }
    public Optional<FarmerInvoice> getDataByFarmer(int farmerId) {
        FarmerInvoice farmerInvoice = repository.findByFarmerId(farmerId)
                .orElse(new FarmerInvoice());
        farmerInvoice.setMilkAmount(farmerInvoice.getMilkAmount());
        double balance = farmerAccountRepository.getByBalance(farmerId);
        farmerInvoice.setAmountDeposite(farmerInvoice.getAmountDeposite());
        farmerInvoice.setAmountdeducation(farmerInvoice.getAmountdeducation());
        farmerInvoice.setBalance(farmerInvoice.getBalance());
        farmerInvoice.setAmountdue(farmerInvoice.getAmountdue());
        farmerInvoice.setPendingStatus(true);
        generatedInvoice2(farmerId, farmerInvoice.getMilkAmount());
        repository.save(farmerInvoice);
        return repository.findByFarmerId(farmerId);
    }


    public void generatedInvoice2(int farmerId ,double milk) {
//        CustomerInvoice customerInvoice=new CustomerInvoice();

        FarmerInvoice2 farmerInvoice = new FarmerInvoice2();
        FarmerInvoice farmerInvoice2 = repository.findByFarmerId(farmerId)
                .orElse(new FarmerInvoice());


        FarmerRegistration f = farmerRegistrationRepository.findById(farmerId)
                .orElseThrow(() -> new RuntimeException("Not found the farmer"));
        Integer dairyId = farmerRegistrationRepository.getdairyIdByFarmerId(farmerId)
                .orElseThrow(() -> new RuntimeException("Dairy ID not found for FarmerId ID: " + farmerId));

        DairyRegistration dairy = dairyRepository.findById(dairyId)
                .orElseThrow(() -> new RuntimeException("Dairy not found for ID: " + dairyId));
        farmerInvoice.setDairyRegistration(dairy);
        farmerInvoice.setFarmerRegistration(f);

        double balance = farmerAccountRepository.getByBalance(farmerId);
        if (balance > 0) {
            farmerInvoice.setAmountDeposite(balance);
            farmerInvoice.setAmountdeducation(0.0);
        } else {
            farmerInvoice.setAmountdeducation(balance);
            farmerInvoice.setAmountDeposite(0.0);
        }
        double val = farmerInvoice2.getMilkAmount() + balance;
        if (val > 0) {
            farmerInvoice.setBalance(val);
            farmerInvoice.setAmountdue(0.0);
        } else {
            farmerInvoice.setBalance(0.0);
            farmerInvoice.setAmountdue(val);
        }
        farmerInvoice.setDate(LocalDate.now());
        farmerInvoice.setMilkAmount(milk);
        Integer lastInvoice = farmerInvoice2Respository.getInvoice(); // can be null
        int newInvoice = (lastInvoice == null ? 1 : lastInvoice + 1);
        farmerInvoice.setInvoiceNumber(newInvoice);

        farmerInvoice.setPendingStatus(true);
        farmerInvoice2Respository.save(farmerInvoice);
    }


    public Optional<FarmerInvoice> getDataDateByFarmerId(int farmerId,LocalDate startDate,LocalDate  endDate) {
        FarmerInvoice farmerInvoice = repository.findByFarmerId(farmerId)
                .orElseGet(() -> {
                    FarmerRegistration farmer = farmerRegistrationRepository.findById(farmerId)
                            .orElseThrow(() -> new RuntimeException("Farmer not found"));
                    FarmerInvoice fi = new FarmerInvoice();
                    fi.setFarmerRegistration(farmer);
                    return fi;
                });

        // ✅ Get total milk amount (sum) for this farmer
        Double totalMilk = farmerMilkCollectionRepository.getTotalMilkAmountByFarmerId1(farmerId,startDate,endDate);
        farmerInvoice.setMilkAmount(totalMilk != null ? totalMilk : 0.0);

        // ✅ Balance calculation
        Double balanceObj = farmerAccountRepository.getByBalance(farmerId);
        double balance = balanceObj != null ? balanceObj : 0.0;

        if (balance > 0) {
            farmerInvoice.setAmountDeposite(balance);
            farmerInvoice.setAmountdeducation(0.0);
        } else {
            farmerInvoice.setAmountdeducation(Math.abs(balance));
            farmerInvoice.setAmountDeposite(0.0);
        }

        double val = farmerInvoice.getMilkAmount() + balance;
        if (val > 0) {
            farmerInvoice.setBalance(val);
            farmerInvoice.setAmountdue(0.0);
        } else {
            farmerInvoice.setBalance(0.0);
            farmerInvoice.setAmountdue(Math.abs(val));
        }

        repository.save(farmerInvoice);
        return repository.findByFarmerId(farmerId);
    }
}
