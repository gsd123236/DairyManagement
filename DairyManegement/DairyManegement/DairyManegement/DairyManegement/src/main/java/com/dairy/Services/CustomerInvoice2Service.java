package com.dairy.Services;

import com.dairy.Entity.CustomerInvoice;
import com.dairy.Entity.CustomerInvoice2;
import com.dairy.Entity.CustomerRegistration;
import com.dairy.Entity.DairyRegistration;
import com.dairy.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CustomerInvoice2Service {

    @Autowired
    private CustomerInvoiceRepository repository;
    @Autowired
    private CustomerRegistrationRepository customerRegistrationRepository;
    @Autowired
    private CustomerKhataRepository customerKhataRepository;
    @Autowired
    private CustomerMilkSaleRepository customerMilkSaleRepository;
    @Autowired
    private DairyRepository dairyRegistrationRepository;
    @Autowired
    private CustomerInvoice2Repository customerInvoice2Repository;
    public List<Map<String, Object>> getAllTransactionByDairyId1(int dairyId) {
        List<CustomerInvoice2> transactions = customerInvoice2Repository.getBalanceByDairyId1(dairyId);
        return transactions.stream().map(transaction -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id",transaction.getId());
            map.put("invoiceNumber",transaction.getInvoiceNumber());
            map.put("customerId",transaction.getCustomerRegistration().getId());
            map.put("customerCode", transaction.getCustomerRegistration().getCode());
            map.put("CustomerName", transaction.getCustomerRegistration().getName());
            map.put("TranscantionDate",transaction.getDate());
            map.put("balance", String.valueOf(transaction.getAmountdue()));
            return map;
        }).collect(Collectors.toList());
    }
    public List<Map<String, Object>> getAllTransactionByDate(int dairyId,String startDate,String endDate) {
        List<CustomerInvoice2> transactions = customerInvoice2Repository.getBalanceDate(dairyId,startDate,endDate);
        return transactions.stream().map(transaction -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id",transaction.getId());
            map.put("invoiceNumber",transaction.getInvoiceNumber());
            map.put("customerId",transaction.getCustomerRegistration().getId());
            map.put("customerCode", transaction.getCustomerRegistration().getCode());
            map.put("CustomerName", transaction.getCustomerRegistration().getName());
            map.put("TranscantionDate",transaction.getDate());
            map.put("balance", String.valueOf(transaction.getAmountdue()));
            return map;
        }).collect(Collectors.toList());
    }
    public CustomerInvoice2 add2(int customer_id, int invoice_id) {
        // Fetch the specific invoice for this customer
        CustomerInvoice2 customerInvoice = customerInvoice2Repository
                .findByCustomerRegistrationIdAndId(customer_id, invoice_id)
                .orElseThrow(() -> new RuntimeException("Invoice not found for Customer ID: " + customer_id + " and Invoice ID: " + invoice_id));

        // Update dairy info
        Integer dairyId = customerRegistrationRepository.getdairyIdByCustomerId(customer_id)
                .orElseThrow(() -> new RuntimeException("Dairy ID not found for Customer ID: " + customer_id));

        DairyRegistration dairy = dairyRegistrationRepository.findById(dairyId)
                .orElseThrow(() -> new RuntimeException("Dairy not found for ID: " + dairyId));

        customerInvoice.setDairyRegistration(dairy);

        // Update balance
        Double balance = customerKhataRepository.getByBalance(customer_id);
        customerInvoice.setBalance(balance != null ? balance : 0.0);

        // Milk Amount
        List<Double> ans = customerMilkSaleRepository.add(customer_id);
        double sum = ans.stream().mapToDouble(Double::doubleValue).sum();
        customerInvoice.setMilkAmount(sum);

        // Received
        List<Double> ans1 = customerMilkSaleRepository.getpaid(customer_id);
        double sum1 = ans1.stream().mapToDouble(Double::doubleValue).sum();
        customerInvoice.setReceived(sum1);

        // Update status
        customerInvoice.setPendingStatus(false);
        customerInvoice.setAmountdue(customerInvoice.getMilkAmount() - customerInvoice.getBalance() - customerInvoice.getReceived());
        customerInvoice.setDate(LocalDate.now());
        add(customer_id);
        return customerInvoice2Repository.save(customerInvoice);
    }
    public CustomerInvoice add(int customer_id) {
//        CustomerInvoice customerInvoice=new CustomerInvoice();
        CustomerInvoice customerInvoice = repository.findByCustomerId(customer_id)
                .orElse(new CustomerInvoice());
        CustomerRegistration c = customerRegistrationRepository.findById(customer_id)
                .orElseThrow(() -> new RuntimeException("Not found the customer"));
        Integer dairyId = customerRegistrationRepository.getdairyIdByCustomerId(customer_id)
                .orElseThrow(() -> new RuntimeException("Dairy ID not found for Customer ID: " + customer_id));

        DairyRegistration dairy = dairyRegistrationRepository.findById(dairyId)
                .orElseThrow(() -> new RuntimeException("Dairy not found for ID: " + dairyId));

        customerInvoice.setDairyRegistration(dairy);

        Double balance = customerKhataRepository.getByBalance(customer_id);
        customerInvoice.setBalance(balance != null ? balance : 0.0);
        List<Double>ans= customerMilkSaleRepository.add(customer_id);
        Double sum=0.0;
        int n=ans.size();
        for(int i=0;i<n;i++){
            sum+=ans.get(i);
        }
        customerInvoice.setMilkAmount(sum);
        List<Double>ans1= customerMilkSaleRepository.getpaid(customer_id);
        Double sum1=0.0;
        int n1=ans1.size();
        for(int i=0;i<n1;i++){
            sum1+=ans1.get(i);
        }

        customerInvoice.setCustomerRegistration(c);
        customerInvoice.setReceived(sum1);
        customerInvoice.setPendingStatus(false);
        customerInvoice.setAmountdue(customerInvoice.getMilkAmount() - customerInvoice.getBalance() - customerInvoice.getReceived());
        customerInvoice.setDate(LocalDate.now());
        return repository.save(customerInvoice);
    }


}
