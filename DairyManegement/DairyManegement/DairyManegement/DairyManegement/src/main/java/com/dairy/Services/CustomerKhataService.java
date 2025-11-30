package com.dairy.Services;

import com.dairy.Dto.CustomerKhataDTO;
import com.dairy.Entity.*;
import com.dairy.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerKhataService {
    @Autowired
    private CustomerKhataRepository repository;
    @Autowired
    private CustomerRegistrationRepository customerRepository;
    @Autowired
    private PaymentMethodMasterRepository paymentMethodMasterRepository;
    @Autowired
    private CustomerBalanceSheetRepository CustomerBalanceSheetrepository;
    @Autowired
    private DairyRepository dairyRepository;
    public  CustomerKhata add(CustomerKhata customer,int customerId){
        Optional<CustomerRegistration> c=customerRepository.findById(customerId);
        double previousBalance = repository.findLatestTransactionByCustomerId(customerId)
                .map(CustomerKhata::getBalance)
                .orElse(0.0);
        double currentBalance=previousBalance;
        if(customer.getDebit()>0){
            currentBalance-=customer.getDebit();
        }
        else if (customer.getCredit()>0) {
            currentBalance+= customer.getCredit();

        }
        customer.setBalance(currentBalance);
        customer.setTransactionDate(LocalDateTime.now());
        customer.setDate(LocalDate.now());
        customer.setCustomerRegistration(c.orElse(null));
        addBalance(customerId, customer);
        return repository.save(customer);
    }
    public Optional<CustomerKhata> getBalanceByCustomerId(int customerId){
        return repository.findBalanceByCustomerId(customerId);
    }
    //    public List<CustomerKhataDTO>getAllTransactionByCustomerId(int customerId){
//        List<CustomerKhata>c=repository.getTransactionById(customerId);
//        return c.stream()
//                .map(customer -> new CustomerKhataDTO(customer, getPaymentMethodName(customer.getPaymentMethod())))
//                .collect(Collectors.toList());
//    }
//    public List<CustomerKhataDTO>getAllTransaction(){
//        List<CustomerKhata>c=repository.findAll();
//        return c.stream()
//                .map(customer->new CustomerKhataDTO(customer,getPaymentMethodName(customer.getPaymentMethod())))
//                .collect(Collectors.toList());
//    }
//    public List<CustomerKhataDTO>getTransactionByDateByCustomerId(int customerId,LocalDate startDate,LocalDate endDate){
//        List<CustomerKhata> c=repository.getTransactionByDatesByFarmer(customerId,startDate,endDate);
//        return c.stream()
//                .map(customer->new CustomerKhataDTO(customer,getPaymentMethodName(customer.getPaymentMethod())))
//                .collect(Collectors.toList());
//    }
//    public List<CustomerKhataDTO>getTransaction(LocalDate startDate,LocalDate endDate){
//        List<CustomerKhata>c=repository.getTransactionByDates(startDate,endDate);
//        return c.stream()
//                .map(customer->new CustomerKhataDTO(customer,getPaymentMethodName(customer.getPaymentMethod())))
//                .collect(Collectors.toList());
//    }
    public int deleteByCustomerId(int customerId) {
        return repository.deleteByCustomerId(customerId); // Returns number of deleted rows
    }


    private String getPaymentMethodName(int paymentMethodId){
        return paymentMethodMasterRepository.findById(paymentMethodId)
                .map(PaymentMethodMaster::getPaymentMethod).orElse("unknown");
    }
    public CustomerBalanceSheet addBalance(int customerId, CustomerKhata customerAccount) {
        Double balance = CustomerBalanceSheetrepository.getByCustomerId(customerId);
        if (balance == null) {
            balance = 0.0;
        }
        if (customerAccount.getDebit() > 0) {
            balance -= customerAccount.getDebit();
        } else if (customerAccount.getCredit() > 0) {
            balance += customerAccount.getCredit();
        }
        CustomerBalanceSheet balanceSheet = CustomerBalanceSheetrepository.findByCustomerId(customerId)
                .orElse(new CustomerBalanceSheet());
        CustomerRegistration customerRegistration = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));
        balanceSheet.setCustomerId(customerRegistration);
        balanceSheet.setBalance(balance);
        Integer dairyId = customerRepository.getdairyIdByCustomerId(customerId)
                .orElseThrow(() -> new RuntimeException("Dairy ID not found for Customer ID: " + customerId));
        DairyRegistration dairyRegistration = dairyRepository.findById(dairyId)
                .orElseThrow(() -> new RuntimeException("Dairy not found for ID: " + dairyId));
        balanceSheet.setDairyRegistration(dairyRegistration);
        return CustomerBalanceSheetrepository.save(balanceSheet);
    }

    public List<CustomerKhata>getAllTranscationByCustomerId(int customerId){
        return repository.getTransactionById(customerId);
    }

}
