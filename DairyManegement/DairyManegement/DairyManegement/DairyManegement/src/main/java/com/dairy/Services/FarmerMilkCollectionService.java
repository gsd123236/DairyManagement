package com.dairy.Services;


import com.dairy.Dto.FarmerMilkCollectionTotalDto;
import com.dairy.Entity.*;

import com.dairy.Repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractAuditable_.createdDate;

@Service
public class FarmerMilkCollectionService {


    @Autowired
    private final FarmerMilkCollectionRepository farmerMilkCollectionRepository;

    @Autowired
    FarmerAccountRepository farmerAccountRepository;

    @Autowired
    private FarmerRegistrationRepository farmerRegistrationRepository;


    @Autowired
    private FarmerBalanceSheetRepository repository;
    @Autowired
    private DairyRepository dairyRepository;
    @Autowired
    private  FarmerInvoiceRepository farmerInvoiceRepository;




    public FarmerMilkCollectionService(FarmerMilkCollectionRepository farmerMilkCollectionRepository,
                                       FarmerRegistrationRepository addFarmerRepository) {
        this.farmerMilkCollectionRepository = farmerMilkCollectionRepository;
    }

    private void updateFarmerAccount(FarmerMilkCollection bill) {
        FarmerRegistration farmer = bill.getFarmerRegistration();
        FarmerAccount latestAccount = farmerAccountRepository.findLatestTransactionByFarmerId(farmer.getId())
                .orElse(new FarmerAccount());
        int fid = farmer.getId();
        double previousBalance = latestAccount.getBalance();
        double totalPrice = bill.getTotalPrice();
        double updatedBalance = previousBalance + totalPrice;
        FarmerAccount newAccountEntry = new FarmerAccount();
        newAccountEntry.setFarmerRegistration(farmer);
        newAccountEntry.setCredit((long) totalPrice);
        newAccountEntry.setBalance(updatedBalance);
        newAccountEntry.setTransactionDate(LocalDateTime.now());
        newAccountEntry.setPaymentMethod(4);
        newAccountEntry.setDate(LocalDate.now());
        newAccountEntry.setStatus("Credit");
        addBalance(fid, newAccountEntry);
        farmerAccountRepository.save(newAccountEntry);
    }

    public FarmerBalanceSheet addBalance(int farmerId, FarmerAccount farmerAccount) {
        Double balance = repository.getByFarmerId(farmerId);
        if (balance == null) {
            balance = 0.0;
        }
        if (farmerAccount.getAdvance() > 0) {
            balance -= farmerAccount.getAdvance();
        } else if (farmerAccount.getDebit() > 0) {
            balance -= farmerAccount.getDebit();
        } else if (farmerAccount.getCredit() > 0) {
            balance += farmerAccount.getCredit();
        }
        FarmerBalanceSheet balanceSheet = repository.findByFarmerId(farmerId)
                .orElse(new FarmerBalanceSheet());
        FarmerRegistration farmerRegistration = farmerRegistrationRepository.findById(farmerId)
                .orElseThrow(() -> new RuntimeException("Farmer not found with ID: " + farmerId));
        balanceSheet.setFarmerId(farmerRegistration);
        balanceSheet.setBalance(balance);
        Integer dairyId = farmerRegistrationRepository.getdairyIdByFarmerId(farmerId)
                .orElseThrow(() -> new RuntimeException("Dairy ID not found for Farmer ID: " + farmerId));
        DairyRegistration dairyRegistration = dairyRepository.findById(dairyId)
                .orElseThrow(() -> new RuntimeException("Dairy not found for ID: " + dairyId));
        balanceSheet.setDairyRegistration(dairyRegistration);
        return repository.save(balanceSheet);
    }
    public List<FarmerMilkCollection> saveFarmerMilkCollection(FarmerMilkCollection collection,Integer dId) {
        LocalDate today = LocalDate.now();

        DairyRegistration dairy = dairyRepository.findById(dId)
                .orElseThrow(() -> new RuntimeException("Dairy Registration not found"));

        FarmerRegistration farmer = farmerRegistrationRepository.findById(collection.getFarmerRegistration().getId())
                .orElseThrow(() -> new RuntimeException("Farmer not found"));

        collection.setDairyRegistration(dairy);
        collection.setFarmerRegistration(farmer);

        List<FarmerMilkCollection> savedCollections = new ArrayList<>();
        double val=0.0;
        Integer milkTypeId = collection.getMilkTypeId();

        boolean exists = farmerMilkCollectionRepository.
                existsByFarmerRegistrationAndCollectionShiftIdAndDairyRegistrationAndCreatedDateAndMilkTypeId(
                        farmer, collection.getCollectionShiftId(), dairy, today, milkTypeId
                );

        if (!exists) {
            try {
                double liter = Double.parseDouble(collection.getLiter());
                double rate = Double.parseDouble(collection.getRate());
                double totalPrice = liter * rate;
                val=totalPrice;
                collection.setTotalPrice(totalPrice);

            } catch (Exception e) {
                System.out.println("Error parsing liter or rate: " + e.getMessage());
                collection.setTotalPrice(0.0);
            }

            collection.setCreatedDate(today);
            collection.setCreatedDateTime(LocalDateTime.now());
            collection.setUpdateDate(LocalDate.now());
            addInvoice(farmer.getId(),val);
            FarmerMilkCollection saved = farmerMilkCollectionRepository.save(collection);
            savedCollections.add(saved);
        } else {

            throw new RuntimeException("Milk already exists for this farmer ");
        }

        return savedCollections;
    }

    public void addInvoice(int farmerId, double milkAmount) {
        FarmerInvoice farmerInvoice = farmerInvoiceRepository.findByFarmerId(farmerId)
                .orElse(new FarmerInvoice());

        FarmerRegistration farmer = farmerRegistrationRepository.findById(farmerId)
                .orElseThrow(() -> new RuntimeException("Farmer not found with ID: " + farmerId));

        Integer dairyId = farmerRegistrationRepository.getdairyIdByFarmerId(farmerId)
                .orElseThrow(() -> new RuntimeException("Dairy ID not found for Farmer ID: " + farmerId));

        DairyRegistration dairy = dairyRepository.findById(dairyId)
                .orElseThrow(() -> new RuntimeException("Dairy not found for ID: " + dairyId));

        farmerInvoice.setFarmerRegistration(farmer);
        farmerInvoice.setDairyRegistration(dairy);
        farmerInvoice.setPendingStatus(false);
        double existingMilk = farmerInvoice.getMilkAmount() != 0.0 ? farmerInvoice.getMilkAmount() : 0.0;
        double val1=existingMilk + milkAmount;
        farmerInvoice.setMilkAmount(val1);

        Double deduction = farmerAccountRepository.getByBalance(farmerId);
        if (deduction == null) deduction = 0.0;

        double val = val1+deduction;

        if (val < 0.0) {
            farmerInvoice.setAmountdue(Math.abs(val)); // store positive due
            farmerInvoice.setBalance(0.0);
        } else {
            farmerInvoice.setBalance(val);
            farmerInvoice.setAmountdue(0.0);
        }

        farmerInvoice.setDate(LocalDate.now()); // ✅ set missing fields if required

        farmerInvoiceRepository.save(farmerInvoice);
    }
    public List<Map<String, Object>> getMilkCollectionByDateAndDId(Integer dId, LocalDate date) {

        List<Object[]> results = farmerMilkCollectionRepository.findByDIdAndCreatedDate(dId, date);

        if (results.isEmpty()) {
            throw new NoSuchElementException("No milk collection records found for the given Dairy ID and Date.");
        }

        List<Map<String, Object>> responseList = new ArrayList<>();
        for (Object[] row : results) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", row[0]);
            map.put("collectionShiftId", row[1].toString());
            map.put("clr", row[2]);
            map.put("milkTypeId", row[3]);
            map.put("fat", row[4]);
            map.put("liter", row[5]);
            map.put("snf", row[6]);
            map.put("totalPrice", row[7]);
            map.put("rate", row[8]);
            map.put("createdDateTime", row[9]);
            map.put("farmerId", row[10]);
            map.put("farmerCode", row[11]);
            map.put("firstName", row[12]);
            map.put("lastName", row[13]);

            responseList.add(map);
        }
        return responseList;
    }


    // Get data Date To Date throw method




    public List<FarmerMilkCollectionTotalDto> getInformationFarmerMilkID(String farmerID) {
        List<Object[]> results = farmerMilkCollectionRepository.findByAndGetFarmerMileInfo(Integer.valueOf(farmerID));

        return results.stream().map(obj -> new FarmerMilkCollectionTotalDto(
                ((Number) obj[0]).intValue(),
                String.valueOf(((Number) obj[1]).intValue()),
                (String) obj[2],
                (String) obj[3],
                null,
                null,
                null
        )).collect(Collectors.toList());

    }

    //get data to startDte to endDate All Summary Generate 10-20
    public List<FarmerMilkCollectionTotalDto> getMilkDataWithTotal(LocalDateTime startDate, LocalDateTime endDate, Integer farmerId,Integer dId, Integer milkTypeId) {
        List<Object[]> results = farmerMilkCollectionRepository.getMilkCollectionSummaryWithTotal(
                farmerId, milkTypeId,dId, startDate, endDate
        );

        return results.stream().map(result -> {
            if (result[2] != null && "Total".equals(result[2].toString())) {
                // Total row
                return new FarmerMilkCollectionTotalDto(
                        null,
                        null,
                        null,
                        result[2].toString(),
                        null,
                        result[1] != null ? new BigDecimal(result[1].toString()) : null,
                        result[3] != null ? new BigDecimal(result[3].toString()) : null
                );
            } else {
                // Data rows
                return new FarmerMilkCollectionTotalDto(
                        result[0] != null ? ((Number) result[0]).intValue() : null,
                        result[5] != null ? result[5].toString() : null,
                        result[6] != null ? result[6].toString() : null,
                        result[2] != null ? result[2].toString() : null,
                        null,
                        result[1] != null ? new BigDecimal(result[1].toString()) : null,
                        result[3] != null ? new BigDecimal(result[3].toString()) : null
                );
            }
        }).toList();
    }


    //get data to startDte to endDate All Bill Generate

    public List<FarmerMilkCollectionTotalDto> getMilkCollectionSummary(LocalDateTime startDate, LocalDateTime endDate, Integer farmerId,Integer dId, Integer milkTypeId) {
        List<Object[]> results = farmerMilkCollectionRepository.getMilkCollectionSummary(farmerId, milkTypeId,dId, startDate, endDate);
        System.out.println("Data...................."+results);
        return results.stream().map(result -> new FarmerMilkCollectionTotalDto(
                (Integer) result[0],
                result[1].toString(),
                result[2].toString(),
                result[3].toString(),
                null,
                (BigDecimal) result[4],
                (BigDecimal) result[5]
        )).toList();
    }







    //Update Data Method
    public FarmerMilkCollection milkUpdate(FarmerMilkCollection farmerMilkCollection) {
        if (farmerMilkCollection.getId() == 0) {
            throw new IllegalArgumentException("Invalid Id: Update failed");
        }

        Optional<FarmerMilkCollection> updatedata = farmerMilkCollectionRepository.findById(farmerMilkCollection.getId());
        if (updatedata.isPresent()) {
            FarmerMilkCollection updateRecord = updatedata.get();

            // Update only provided fields
            if (farmerMilkCollection.getMilkTypeId() != 0) {
                updateRecord.setMilkTypeId(farmerMilkCollection.getMilkTypeId());
            }
            if (farmerMilkCollection.getCollectionShiftId() != 0) {
                updateRecord.setCollectionShiftId(farmerMilkCollection.getCollectionShiftId());
            }
            if (farmerMilkCollection.getClr() != null) {
                updateRecord.setClr(farmerMilkCollection.getClr());
            }
            if (farmerMilkCollection.getFat() != null) {
                updateRecord.setFat(farmerMilkCollection.getFat());
            }
            if (farmerMilkCollection.getSnf() != null) {
                updateRecord.setSnf(farmerMilkCollection.getSnf());
            }
            if (farmerMilkCollection.getRate() != null) {
                updateRecord.setRate(farmerMilkCollection.getRate());
            }
            if (farmerMilkCollection.getLiter() != null) {
                updateRecord.setLiter(farmerMilkCollection.getLiter());
            }

            // Ensure FarmerRegistration is returned
            updateRecord.setUpdateDate(LocalDate.now());

            FarmerMilkCollection savedRecord = farmerMilkCollectionRepository.save(updateRecord);

            // Force loading of FarmerRegistration if lazy
            savedRecord.getFarmerRegistration().getId(); // This will ensure it's loaded

            return savedRecord;
        } else {
            throw new NoSuchElementException("Record Not Found For Update");
        }
    }


    //Delete Data

    public FarmerMilkCollection deactivateMilkCollection(int id){
        Optional<FarmerMilkCollection> optional=farmerMilkCollectionRepository.findById(id);
        if(optional.isPresent()){
            FarmerMilkCollection farmerMilk=optional.get();
            farmerMilk.setActive(false);
            farmerMilk.setDelete(true);
            return farmerMilkCollectionRepository.save(farmerMilk);
        }else {
            throw new RuntimeException("Farmer Milk Collection "+id+"Not found");
        }
    }

    public FarmerMilkCollection activeMilkCollection(int id){
        Optional<FarmerMilkCollection> farmerMilk=farmerMilkCollectionRepository.findById(id);
        if (farmerMilk.isPresent()){
            FarmerMilkCollection collection=farmerMilk.get();
            farmerMilk.get().setActive(true);
            farmerMilk.get().setDelete(false);
            return farmerMilkCollectionRepository.save(collection);
        }else {
            throw  new RuntimeException("Milk Collection Id "+id+"Not Found");
        }

    }

    //******** Farmer Invoice ********
    public List<Map<String, Object>> getByDate(LocalDate startDate, LocalDate endDate, Integer dId) {
        List<Object[]> results = farmerMilkCollectionRepository.getFarmerMilkCollectionByDate(dId, startDate, endDate);

        if (results.isEmpty()) {
            throw new NoSuchElementException("No milk collection records found for the given Dairy ID and Date range.");
        }

        List<Map<String, Object>> responseList = new ArrayList<>();
        for (Object[] row : results) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", row[0]);
            map.put("collection_shift_id", row[1]);
            map.put("clr", row[2]);
            map.put("milk_type_id", row[3]);
            map.put("fat", row[4]);
            map.put("liter", row[5]);
            map.put("snf", row[6]);
            map.put("total_price", row[7]);
            map.put("rate", row[8]);
            map.put("created_date", row[9]);
            map.put("farmer_id", row[10]);
            map.put("firstName", row[11]);
            map.put("lastName", row[12]);
            map.put("farmer_code", row[13]);

            responseList.add(map);
        }
        return responseList;
    }


    public List<Map<String, Object>> getByReportsDate(LocalDate startDate, LocalDate endDate, Integer dId) {
        List<Object[]> results = farmerMilkCollectionRepository.getMilkCollectionData(dId, startDate, endDate);

        if (results.isEmpty()) {
            throw new NoSuchElementException("No milk collection records found for the given Dairy ID and Date range.");
        }

        List<Map<String, Object>> responseList = new ArrayList<>();
        for (Object[] row : results) {
            Map<String, Object> map = new HashMap<>();
            map.put("fat", row[0]);
            map.put("snf", row[1]);
            map.put("clr", row[2]);
            map.put("rate", row[3]);
            map.put("liter", row[4]);
            map.put("collection_shift_id", row[5]);
            map.put("milk_type_id", row[6]);
            map.put("total_price", row[7]);
            map.put("created_date", row[8]);
            responseList.add(map);
        }
        return responseList;
    }

    public List<Map<String, Object>> getReportData(LocalDate startDate, Integer dId) {
        List<Object[]> results = farmerMilkCollectionRepository.getMilkReportData(dId, startDate);

        if (results.isEmpty()) {
            throw new NoSuchElementException("No milk collection records found for the given Dairy ID and Date range.");
        }

        List<Map<String, Object>> responseList = new ArrayList<>();
        for (Object[] row : results) {
            Map<String, Object> map = new HashMap<>();
            map.put("fat", row[0]);
            map.put("snf", row[1]);
            map.put("clr", row[2]);
            map.put("rate", row[3]);
            map.put("liter", row[4]);
            map.put("collection_shift_id", row[5]);
            map.put("milk_type_id", row[6]);
            map.put("total_price", row[7]);
            map.put("farmer_code", row[8]);
            map.put("first_name", row[9]);
            map.put("last_name", row[10]);
            responseList.add(map);
        }
        return responseList;
    }


}