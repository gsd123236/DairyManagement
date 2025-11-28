package com.dairy.Services;

import com.dairy.Entity.FarmerBalanceSheet;
import com.dairy.Repository.FarmerBalanceSheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FarmerBalanceSheetService {
    @Autowired
    private FarmerBalanceSheetRepository repository;
    public List<Map<String, Object>> getAllTransactionByDairyId(int dairyId) {
        List<FarmerBalanceSheet> transactions = repository.getBalanceByDairyId(dairyId);
        return transactions.stream().map(transaction -> {
            Map<String, Object> map = new HashMap<>();
            map.put("farmerId",transaction.getFarmerId().getId());
            map.put("farmerCode", transaction.getFarmerId().getCode());
            map.put("farmerName", transaction.getFarmerId().getFirstName() + " " + transaction.getFarmerId().getLastName());
            map.put("balance", String.valueOf(transaction.getBalance()));
            return map;
        }).collect(Collectors.toList());
    }

}
