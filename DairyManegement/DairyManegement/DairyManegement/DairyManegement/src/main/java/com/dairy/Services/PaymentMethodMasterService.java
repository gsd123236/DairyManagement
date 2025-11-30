package com.dairy.Services;

import com.dairy.Entity.PaymentMethodMaster;
import com.dairy.Entity.PaymentPeriodMaster;
import com.dairy.Repository.PaymentMethodMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class PaymentMethodMasterService {

    @Autowired
    private PaymentMethodMasterRepository repository;

    public PaymentMethodMaster savePaymentMethod(PaymentMethodMaster paymentMethod) {
        List<PaymentMethodMaster>existingPayment=repository.paymentTypeByName(paymentMethod.getPaymentMethod());
        boolean anyNotDelted=existingPayment.stream().anyMatch(p->!p.isDelete());
        if(anyNotDelted){
            throw new RuntimeException("PaymentType with the same name already exists and is not deleted.");
        }
        paymentMethod.setCreateDate(LocalDateTime.now());
        paymentMethod.setActive(true);
        return repository.save(paymentMethod);
    }

    public List<PaymentMethodMaster> getAllPaymentMethods() {
        return repository.getAllActiveData();
    }

    public Optional<PaymentMethodMaster> getPaymentMethodById(int id) {
        return repository.findById(id);
    }
    public PaymentMethodMaster isActive(int id,PaymentMethodMaster paymentMethodMaster){
        repository.findById(id).orElseThrow(()->new RuntimeException("Not found"));
        paymentMethodMaster.setActive(true);
        paymentMethodMaster.setDelete(false);
        paymentMethodMaster.setDeactive(false);
        return  repository.save(paymentMethodMaster);

    }

    public PaymentMethodMaster deactivatePaymentMethod(int id) {
        Optional<PaymentMethodMaster> optionalPayment = repository.findById(id);
        if (optionalPayment.isPresent()) {
           PaymentMethodMaster payment= optionalPayment.get();
            payment.setActive(false);
            payment.setDelete(false);
            payment.setDeactive(true);
            return repository.save(payment);
        } else {
            throw new RuntimeException("Milk with ID " + id + " not found");
        }
    }
    public PaymentMethodMaster activatePaymentMethod(int id){
        Optional<PaymentMethodMaster> paymentPeriod= repository.findById(id);
        if(paymentPeriod.isPresent()){
            PaymentMethodMaster s=paymentPeriod.get();
            paymentPeriod.get().setActive(true);
            paymentPeriod.get().setDelete(false);
            paymentPeriod.get().setDeactive(false);
            return repository.save(s);
        }
        else {
            throw new RuntimeException("PaymentPeriod with Id"+id+"not found");
        }
    }
    public List<PaymentMethodMaster> getActiveDeactive(){
        return repository.getAllActiveOrDeactive();
    }
    public PaymentMethodMaster DeletedPaymentMethod(int id){
        Optional<PaymentMethodMaster> paymentPeriod= repository.findById(id);
        if(paymentPeriod.isPresent()){
            PaymentMethodMaster s=paymentPeriod.get();
            paymentPeriod.get().setActive(false);
            paymentPeriod.get().setDelete(true);
            paymentPeriod.get().setDeactive(false);
            return repository.save(s);
        }
        else {
            throw new RuntimeException("PaymentPeriod with Id"+id+"not found");
        }
    }
    public PaymentMethodMaster update(int id, String paymentMethodName){
        PaymentMethodMaster p= repository.findById(id).orElseThrow(()->new RuntimeException("Not Found"));
        p.setPaymentMethod(paymentMethodName);
        p.setLastUpdate(LocalDateTime.now());
        return repository.save(p);

    }
}

