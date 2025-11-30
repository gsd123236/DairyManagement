package com.dairy.Services;

import com.dairy.Dto.MilkSaleDTO;
import com.dairy.Entity.*;
import com.dairy.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomerMilkSaleService {
   @Autowired
      private CustomerMilkSaleRepository repository;
      @Autowired
      private CustomerRegistrationRepository customerRepository;
      @Autowired
      private MilkTypeMasterRepository milkTypeMasterRepository;
      @Autowired
      private CollectionShiftMasterRepository collectionShiftMasterRepository;
      @Autowired
      private CustomerKhataRepository customerKhataRepository;
      @Autowired
      private CustomerBalanceSheetRepository customerBalanceSheetRepository;
      @Autowired
      private CustomerInvoiceRepository customerInvoiceRepository;
      @Autowired
      private DairyRepository dairyRegistrationRepository;
      @Autowired
      private DairyRepository dairyRepository;


    public CustomerMilkSale add(CustomerMilkSale customerMilkSale, int dId) {
        DairyRegistration dairy = dairyRepository.findById(dId)
                .orElseThrow(() -> new RuntimeException("Dairy Registration not found"));
        CustomerRegistration customer = customerRepository.findById(customerMilkSale.getCustomerRegistration().getId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        customerMilkSale.setAmount(customerMilkSale.getLiter() * customerMilkSale.getRate());
        customerMilkSale.setBalance(customerMilkSale.getAmount() - customerMilkSale.getPaid());
        customerMilkSale.setCustomerRegistration(customer);
        customerMilkSale.setDairyRegistration(dairy);
        customerMilkSale.setTransactionDate(LocalDateTime.now());
        customerMilkSale.setDate(LocalDate.now());
        addInvoice(customer.getId());
        return repository.save(customerMilkSale);
    }
    public CustomerInvoice addInvoice(int customer_id) {
        // Find existing invoice or create new
        CustomerInvoice customerInvoice = customerInvoiceRepository.findByCustomerId(customer_id)
                .orElse(new CustomerInvoice());

        // Get customer and dairy
        CustomerRegistration customer = customerRepository.findById(customer_id)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customer_id));

        Integer dairyId = customerRepository.getdairyIdByCustomerId(customer_id)
                .orElseThrow(() -> new RuntimeException("Dairy ID not found for Customer ID: " + customer_id));

        DairyRegistration dairy = dairyRegistrationRepository.findById(dairyId)
                .orElseThrow(() -> new RuntimeException("Dairy not found for ID: " + dairyId));

        // Assign relationships
        customerInvoice.setCustomerRegistration(customer);
        customerInvoice.setDairyRegistration(dairy);

        // Fetch calculated values
        Double balance = customerKhataRepository.getByBalance(customer_id);
        Double milkAmount = repository.getTotalMilkAmount(customer_id);
        Double received = repository.getTotalPaid(customer_id);

        // Set values with safe defaults
        customerInvoice.setBalance(balance != null ? balance : 0.0);
        customerInvoice.setMilkAmount(milkAmount != null ? milkAmount : 0.0);
        customerInvoice.setReceived(received != null ? received : 0.0);

        // Calculate due
        double amountDue = customerInvoice.getMilkAmount()
                - customerInvoice.getBalance()
                - customerInvoice.getReceived();
        customerInvoice.setAmountdue(amountDue);
        customerInvoice.setPendingStatus(false);
        customerInvoice.setDate(LocalDate.now());

        return customerInvoiceRepository.save(customerInvoice);
    }

//    public CustomerInvoice addInvoice(int customer_id) {
////        CustomerInvoice customerInvoice=new CustomerInvoice();
//        CustomerInvoice customerInvoice =customerInvoiceRepository.findByCustomerId(customer_id)
//                .orElse(new CustomerInvoice());
//        CustomerRegistration c = customerRepository.findById(customer_id)
//                .orElseThrow(() -> new RuntimeException("Not found the customer"));
//     Integer dairyId = customerRepository.getdairyIdByCustomerId(customer_id)
//             .orElseThrow(() -> new RuntimeException("Dairy ID not found for Customer ID: " + customer_id));
//
//       DairyRegistration dairy = dairyRegistrationRepository.findById(dairyId)
//               .orElseThrow(() -> new RuntimeException("Dairy not found for ID: " + dairyId));
//
//      customerInvoice.setDairyRegistration(dairy);
//
//        Double balance = customerKhataRepository.getByBalance(customer_id);
//        customerInvoice.setBalance(balance != null ? balance : 0.0);
//        List<Double>ans= repository.add(customer_id);
//        Double sum=0.0;
//        int n=ans.size();
//        for(int i=0;i<n;i++){
//            sum+=ans.get(i);
//        }
//        customerInvoice.setMilkAmount(sum);
//        List<Double>ans1= repository.getpaid(customer_id);
//        Double sum1=0.0;
//        int n1=ans1.size();
//        for(int i=0;i<n1;i++){
//            sum1+=ans1.get(i);
//        }
//
//        customerInvoice.setCustomerRegistration(c);
//        customerInvoice.setReceived(sum1);
//        customerInvoice.setAmountdue(customerInvoice.getMilkAmount() - customerInvoice.getBalance() - customerInvoice.getReceived());
//        customerInvoice.setDate(LocalDate.now());
//        return customerInvoiceRepository.save(customerInvoice);
//    }

    public MilkSaleDTO getById(int id) {
        CustomerMilkSale s = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("NOT FOUND"));
        String milkType = milkTypeMasterRepository.findById(s.getMilkType())
                .map(MilkTypeMaster::getAnimalName).orElse(null);
        String shift = collectionShiftMasterRepository.findById(s.getShift())
                .map(CollectionShiftMaster::getShift).orElse(null);
        return new MilkSaleDTO(s, milkType, shift);

    }
    public List<MilkSaleDTO> getByDate(LocalDate date, int id) {
        List<CustomerMilkSale> s = repository.getByDateAndShfit(date, id);

        if (s.isEmpty()) {
            return Collections.emptyList();
        }
        List<MilkSaleDTO> dtoList = s.stream().map(customerMilkSale -> {
            String milkType = milkTypeMasterRepository.findById(customerMilkSale.getMilkType())
                    .map(MilkTypeMaster::getAnimalName)
                    .orElse(null);

            String shift = collectionShiftMasterRepository.findById(customerMilkSale.getShift())
                    .map(CollectionShiftMaster::getShift)
                    .orElse(null);

            return new MilkSaleDTO(customerMilkSale, milkType, shift);
        }).collect(Collectors.toList());

        return dtoList;
    }
    //************

//    public List<MilkSaleDTO> getByDate(LocalDate date, Integer dId) {
//        DairyRegistration dairy = dairyRepository.findById(dId)
//                .orElseThrow(() -> new RuntimeException("Dairy Registration not found"));
//
//        // Use new repository method
//        List<CustomerMilkSale> sales = repository.getByDateAndDairyId(date, dId);
//
//        if (sales.isEmpty()) {
//            return Collections.emptyList();
//        }
//
//        return sales.stream().map(customerMilkSale -> {
//            String milkType = milkTypeMasterRepository.findById(customerMilkSale.getMilkType())
//                    .map(MilkTypeMaster::getAnimalName).orElse(null);
//            String shift = collectionShiftMasterRepository.findById(customerMilkSale.getShift())
//                    .map(CollectionShiftMaster::getShift).orElse(null);
//            return new MilkSaleDTO(customerMilkSale, milkType, shift);
//        }).collect(Collectors.toList());
//    }

    public List<Map<String, Object>> getByDate(LocalDate date, Integer dId) {

        List<Object[]> results = repository.getByDateAndDairyId(dId, date);

        if (results.isEmpty()) {
            throw   new NoSuchElementException("No milk collection records found for the given Dairy ID and Date.");
        }

        List<Map<String, Object>> responseList = new ArrayList<>();
        for (Object[] row : results) {
            Map<String, Object> map = new HashMap<>();
            System.out.println(Arrays.toString(row));
            map.put("id", row[0]);
            map.put("shift", row[1]);
            map.put("clr", row[2]);
            map.put("milk_type", row[3] );
            map.put("fat", row[4]);
            map.put("liter", row[5]);
            map.put("snf", row[6]);
            map.put("amount", row[7]);
            map.put("rate", row[8]);
            map.put("transaction_date", row[9]);
            map.put("customer_id", row[10]);
            map.put("name", row[11]);
            map.put("code", row[12]);

            responseList.add(map);
        }

        return responseList;
    }


    public List<Map<String, Object>> getByCustomerMilkInvoice(LocalDate startDate,LocalDate endDate,Integer dId) {

        List<Object[]> results = repository.getByCustomerInvoice(dId,startDate,endDate);
        if (results.isEmpty()) {
            throw new NoSuchElementException("No milk collection records found for the given Dairy ID and Date.");
        }

        List<Map<String, Object>> responseList = new ArrayList<>();
        for (Object[] row : results) {
            Map<String, Object> map = new HashMap<>();
            System.out.println(Arrays.toString(row));
            map.put("id", row[0]);
            map.put("shift", row[1]);
            map.put("milk_type", row[2]);
            map.put("liter", row[3]);
            map.put("amount", row[4]);
            map.put("rate", row[5]);
            map.put("paid", row[6]);
            map.put("transaction_date", row[7]);
            map.put("customer_id", row[8]);
            map.put("name", row[9]);
            map.put("code", row[10]);

            responseList.add(map);
        }

        return responseList;
    }

    //**************
    public List<MilkSaleDTO>getAll(){
        List<CustomerMilkSale> s=repository.findAll();
        if(s.isEmpty()){
            return Collections.emptyList();
        }
        List<MilkSaleDTO>dtoList=s.stream().map(customerMilkSale -> {
            String milkType=milkTypeMasterRepository.findById(customerMilkSale.getMilkType())
                    .map(MilkTypeMaster::getAnimalName).orElse(null);
            String shift=collectionShiftMasterRepository.findById(customerMilkSale.getShift())
                    .map(CollectionShiftMaster::getShift).orElse(null);
            return new MilkSaleDTO(customerMilkSale,milkType,shift);
        }).collect(Collectors.toList());
        return dtoList;
    }
}

