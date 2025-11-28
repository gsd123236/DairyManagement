package com.dairy.Services;

import com.dairy.Entity.CustomerInvoice2;
import com.dairy.Entity.FarmerInvoice;
import com.dairy.Entity.FarmerInvoice2;
import com.dairy.Repository.FarmerInvoice2Respository;
import com.dairy.Repository.FarmerInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FarmerInvoice2Service {
    @Autowired
    private FarmerInvoice2Respository repository;
    @Autowired
    private FarmerInvoiceRepository farmerInvoiceRepository;
    public List<Map<String, Object>> getAllTransactionByDairyId(int dairyId) {
        List<FarmerInvoice2> transactions = repository.getByDairyId(dairyId);
        return transactions.stream().map(transaction -> {
            Map<String, Object> map = new HashMap<>();
            map.put("farmerId",transaction.getFarmerRegistration().getId());
            map.put("farmerCode", transaction.getFarmerRegistration().getCode());
            map.put("farmerName", transaction.getFarmerRegistration().getFirstName()+" "+transaction.getFarmerRegistration().getLastName());
            map.put("milkAmount", String.valueOf(transaction.getMilkAmount()));
            map.put("farmerInvoice",transaction.getInvoiceNumber());
            map.put("id",transaction.getId());
            return map;
        }).collect(Collectors.toList());
    }
    public List<Map<String, Object>> getAllTransactionByDate(int dairyId,String startDate,String endDate) {
        List<FarmerInvoice2> transactions = repository.getBalanceDate(dairyId,startDate,endDate);
        return transactions.stream().map(transaction -> {
            Map<String, Object> map = new HashMap<>();
            map.put("farmerId",transaction.getFarmerRegistration().getId());
            map.put("farmerCode", transaction.getFarmerRegistration().getCode());
            map.put("farmerName", transaction.getFarmerRegistration().getFirstName()+" "+transaction.getFarmerRegistration().getLastName());
            map.put("milkAmount", String.valueOf(transaction.getMilkAmount()));
            map.put("farmerInvoice",transaction.getInvoiceNumber());
            map.put("id",transaction.getId());
            return map;
        }).collect(Collectors.toList());
    }
    public FarmerInvoice2 add2(int farmer_id, int invoice_id){
        update(farmer_id);
        return repository.updateStatus(farmer_id,invoice_id);
    }
    public void update (int farmer_id){
         farmerInvoiceRepository.updateInvoice(farmer_id);
    }
}
