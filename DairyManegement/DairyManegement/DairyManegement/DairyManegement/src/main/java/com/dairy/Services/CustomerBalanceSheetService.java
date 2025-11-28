package com.dairy.Services;

import com.dairy.Entity.CustomerBalanceSheet;
import com.dairy.Entity.FarmerBalanceSheet;
import com.dairy.Repository.CustomerBalanceSheetRepository;
import com.dairy.Repository.FarmerBalanceSheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CustomerBalanceSheetService {
    @Autowired
    private CustomerBalanceSheetRepository repository;
    public List<Map<String, Object>> getAllTransactionByDairyId(int dairyId) {
        List<CustomerBalanceSheet> transactions = repository.getBalanceByDairyId(dairyId);
        return transactions.stream().map(transaction -> {
            Map<String, Object> map = new HashMap<>();
            map.put("customerId",transaction.getCustomerId().getId());
            map.put("customerCode", transaction.getCustomerId().getCode());
            map.put("CustomerName", transaction.getCustomerId().getName());
            map.put("balance", String.valueOf(transaction.getBalance()));
            return map;
        }).collect(Collectors.toList());
    }
}
