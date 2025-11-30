package com.dairy.Services;

import com.dairy.Dto.FarmerAccountDTO;
import com.dairy.Entity.*;
import com.dairy.Repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FarmerAccountService {
    @Autowired
    private  FarmerAccountRepository farmerAccountRepository;
    @Autowired
    private FarmerRegistrationRepository farmerRegistrationRepository;
    @Autowired
    private PaymentMethodMasterRepository paymentMethodMasterRepository;
    @Autowired
    private FarmerBalanceSheetRepository repository;
    @Autowired
    private DairyRepository dairyRepository;
    @Autowired
    private  FarmerInvoiceRepository farmerInvoiceRepository;
    @Transactional
    public FarmerAccount addFarmer(FarmerAccount farmerAccount, int farmerId) {
        FarmerRegistration farmer = farmerRegistrationRepository.findById(farmerId)
                .orElseThrow(() -> new RuntimeException("Farmer not found"));

        double previousBalance = farmerAccountRepository.findLatestTransactionByFarmerId(farmerId)
                .map(FarmerAccount::getBalance)
                .orElse(0.0);

        double currentBalance = previousBalance;

        if (farmerAccount.getAdvance() > 0) {
            farmerAccount.setStatus("Advance");
            currentBalance -= farmerAccount.getAdvance();
        } else if (farmerAccount.getDebit() > 0) {
//            if (currentBalance < farmerAccount.getDebit()) {
//                throw new RuntimeException("Your Debited Amount is Greater than current Amount.");
//            } else {
                farmerAccount.setStatus("Debit");
                currentBalance -= farmerAccount.getDebit();

        } else if (farmerAccount.getCredit() > 0) {
            farmerAccount.setStatus("Credit");
            currentBalance += farmerAccount.getCredit();
        }

        farmerAccount.setBalance(currentBalance);
        farmerAccount.setTransactionDate(LocalDateTime.now());
        farmerAccount.setDate(LocalDate.now());
        farmerAccount.setFarmerRegistration(farmer);
        addBalance(farmerId, farmerAccount);
        addInvoice(farmerId,currentBalance);
        return farmerAccountRepository.save(farmerAccount);
    }
    public void addInvoice(int farmerId, double currentBalance) {
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

        if(currentBalance>0.0){
            farmerInvoice.setAmountDeposite(currentBalance);
            farmerInvoice.setAmountdeducation(0.0);
        }
        else{
            farmerInvoice.setAmountdeducation(currentBalance);
            farmerInvoice.setAmountDeposite(0.0);
        }
        double val=farmerInvoice.getMilkAmount()+currentBalance;
        if(val>0){
            farmerInvoice.setBalance(val);
            farmerInvoice.setAmountdue(0.0);
        }
        else{
            farmerInvoice.setBalance(0.0);
            farmerInvoice.setAmountdue(val);
        }
        farmerInvoice.setPendingStatus(false);
        farmerInvoice.setDate(LocalDate.now());

        farmerInvoiceRepository.save(farmerInvoice);
    }


    public Optional<FarmerAccount> getBalance(int farmer_id){
        return farmerAccountRepository.AccountBalance(farmer_id);
    }
    public List<FarmerAccountDTO> getFarmer(int farmerId) {
        List<FarmerAccount> farmers = farmerAccountRepository.findTransactionByFarmer(farmerId);

        return farmers.stream()
                .map(farmer -> new FarmerAccountDTO(farmer, getPaymentMethodName(farmer.getPaymentMethod())))
                .collect(Collectors.toList());
    }

//    public List<FarmerAccountDTO> getDataTransactionsByDateRange(int farmerId, LocalDate startDate, LocalDate endDate) {
//        List<FarmerAccount> farmers = farmerAccountRepository.findTransactionsByDateRange(farmerId, startDate.withDayOfYear(), endDate);
//        return  farmers.stream()
//                .map(farmer->new FarmerAccountDTO(farmer,getPaymentMethodName(farmer.getPaymentMethod())))
//                .collect(Collectors.toList());
//    }
 public List<Map<String, Object>> getFormattedTransactionsByDateRange(int farmerId, LocalDate startDate, LocalDate endDate) {
    List<FarmerAccount> farmers = farmerAccountRepository.findTransactionsByDateRange(farmerId, startDate, endDate);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    return farmers.stream().map(farmer -> {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("paymentMethod", getPaymentMethodName(farmer.getPaymentMethod()));
        map.put("id", farmer.getId());
        map.put("credit", farmer.getCredit());
        map.put("debit", farmer.getDebit());
        map.put("advance", farmer.getAdvance());
        map.put("number", farmer.getNumber());
        map.put("date", farmer.getDate() != null ? farmer.getDate().format(formatter) : null);
        map.put("balance", farmer.getBalance());
        return map;
    }).collect(Collectors.toList());
}

    private String getPaymentMethodName(int paymentMethodId) {
        return paymentMethodMasterRepository.findById(paymentMethodId)
                .map(PaymentMethodMaster::getPaymentMethod)
                .orElse("Unknown");
    }
    public  int deleteByFarmerId(int farmerId){
        return farmerAccountRepository.deleteByFarmerId(farmerId);
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
    public List<FarmerAccount>getByDateInvoice(int farmerId,LocalDate startDate,LocalDate endDate){
        return farmerAccountRepository.getAccountListByDate(farmerId,startDate,endDate);
    }


}

