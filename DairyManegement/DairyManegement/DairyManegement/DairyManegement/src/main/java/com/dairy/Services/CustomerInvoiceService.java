package com.dairy.Services;

import com.dairy.Entity.*;
import com.dairy.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CustomerInvoiceService {
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


    public CustomerInvoice generatedInvoice(int customer_id) {
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
        customerInvoice.setPendingStatus(true);
        customerInvoice.setAmountdue(customerInvoice.getMilkAmount() - customerInvoice.getBalance() - customerInvoice.getReceived());
        customerInvoice.setDate(LocalDate.now());
        generatedInvoice2(customer_id);//This method show the error.....
        return repository.save(customerInvoice);
    }

    public CustomerInvoice2 generatedInvoice2(int customer_id) {
//        CustomerInvoice customerInvoice=new CustomerInvoice();
        CustomerInvoice2 customerInvoice = new CustomerInvoice2();
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
        customerInvoice.setInvoiceNumber(customerInvoice2Repository.getInvoiceNumber()+1);
        customerInvoice.setReceived(sum1);
        customerInvoice.setPendingStatus(true);
        customerInvoice.setAmountdue(customerInvoice.getMilkAmount() - customerInvoice.getBalance() - customerInvoice.getReceived());
        customerInvoice.setDate(LocalDate.now());
        return customerInvoice2Repository.save(customerInvoice);
    }
    public CustomerInvoice getByDate(int customer_id,LocalDate startDate,LocalDate endDate,CustomerInvoice customerInvoice){
        CustomerRegistration c=customerRegistrationRepository.findById(customer_id)
                .orElseThrow(()->new RuntimeException("Not found the customer"));
        Double balance=customerKhataRepository.getByBalanceByDate(customer_id,startDate,endDate);

        customerInvoice.setBalance(balance != null ? balance : 0.0);
        List<Double>ans= customerMilkSaleRepository.getAmountByDate(customer_id,startDate,endDate);
        Double sum=0.0;
        int n=ans.size();
        for(int i=0;i<n;i++){
            sum+=ans.get(i);
        }
        customerInvoice.setMilkAmount(sum);
        List<Double>ans1= customerMilkSaleRepository.getPaidByDate(customer_id,startDate,endDate);
        Double sum1=0.0;
        int n1=ans1.size();
        for(int i=0;i<n1;i++){
            sum1+=ans1.get(i);
        }
        customerInvoice.setCustomerRegistration(c);
        customerInvoice.setReceived(sum1);
        customerInvoice.setAmountdue(customerInvoice.getMilkAmount() - customerInvoice.getBalance() - customerInvoice.getReceived());
        customerInvoice.setDate(LocalDate.now());
        return repository.save(customerInvoice);
    }

    public List<Map<String, Object>> getAllTransactionByDairyId(int dairyId) {
        List<CustomerInvoice> transactions = repository.getBalanceByDairyId(dairyId);
        return transactions.stream().map(transaction -> {
            Map<String, Object> map = new HashMap<>();
            map.put("customerId",transaction.getCustomerRegistration().getId());
            map.put("customerCode", transaction.getCustomerRegistration().getCode());
            map.put("CustomerName", transaction.getCustomerRegistration().getName());
            map.put("balance", String.valueOf(transaction.getAmountdue()));
            return map;
        }).collect(Collectors.toList());
    }
    public List<Map<String, Object>> getAllTransactionByDate(int dairyId,String startDate,String endDate) {
        List<CustomerInvoice> transactions = repository.getBalanceDate(dairyId,startDate,endDate);
        return transactions.stream().map(transaction -> {
            Map<String, Object> map = new HashMap<>();
            map.put("customerId",transaction.getCustomerRegistration().getId());
            map.put("customerCode", transaction.getCustomerRegistration().getCode());
            map.put("CustomerName", transaction.getCustomerRegistration().getName());
            map.put("balance", String.valueOf(transaction.getAmountdue()));
            return map;
        }).collect(Collectors.toList());
    }
    public List<Map<String, Object>> getAllTransactionByDairyId1(int dairyId) {
        List<CustomerInvoice> transactions = repository.getBalanceByDairyId1(dairyId);
        return transactions.stream().map(transaction -> {
            Map<String, Object> map = new HashMap<>();
            map.put("customerId",transaction.getCustomerRegistration().getId());
            map.put("customerCode", transaction.getCustomerRegistration().getCode());
            map.put("CustomerName", transaction.getCustomerRegistration().getName());
            map.put("balance", String.valueOf(transaction.getAmountdue()));
            return map;
        }).collect(Collectors.toList());
    }



}
