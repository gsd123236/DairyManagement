package com.dairy.Services;

import com.dairy.Entity.PaymentPeriodMaster;
import com.dairy.Entity.StateMaster;
import com.dairy.Repository.PaymentPeriodMasterRepository;
import com.twilio.twiml.voice.Pay;
import jakarta.persistence.PrePersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentPeriodMasterService {
    @Autowired
    private PaymentPeriodMasterRepository paymentPeriodMasterRepository;
    public List<PaymentPeriodMaster>getAll(){
        return paymentPeriodMasterRepository.getActiveData();
    }
    public Optional<PaymentPeriodMaster> getById(int id){
        return  paymentPeriodMasterRepository.findById(id);
    }
    public PaymentPeriodMaster addPaymentPeriod(PaymentPeriodMaster paymentPeriod){
       List<PaymentPeriodMaster>existingPayment=paymentPeriodMasterRepository.paymentPeriodByName(paymentPeriod.getPaymentPeriodName());
       boolean anyNotDelted=existingPayment.stream().anyMatch(p->!p.isDelete());
       if(anyNotDelted){
           throw new RuntimeException("PaymentPeriod with the same name already exists and is not deleted.");
       }
            paymentPeriod.setLastUpdate(LocalDateTime.now());
            paymentPeriod.setCreateDate(LocalDateTime.now());
            paymentPeriod.setActive(true);
            return paymentPeriodMasterRepository.save(paymentPeriod);
    }
    public List<PaymentPeriodMaster> getActiveDeactive(){
        return paymentPeriodMasterRepository.getAllActiveOrDeactive();
    }
    public PaymentPeriodMaster deactivatePaymentPeriod(int id) {
        Optional<PaymentPeriodMaster> stateOpt = paymentPeriodMasterRepository.findById(id);
        if (stateOpt.isPresent()) {
            PaymentPeriodMaster state = stateOpt.get();
            state.setActive(false);
            state.setDeactive(true);
            return paymentPeriodMasterRepository.save(state);
        }
        return null;
    }
    public PaymentPeriodMaster activatePaymentPeriod(int id){
        Optional<PaymentPeriodMaster> stateOpt = paymentPeriodMasterRepository.findById(id);
        if (stateOpt.isPresent()) {
            PaymentPeriodMaster state = stateOpt.get();
            state.setActive(true);
            state.setDeactive(false);
            return paymentPeriodMasterRepository.save(state);
        }
        return null;
    }
    public PaymentPeriodMaster DeletedPaymentPeriod(int id){
        Optional<PaymentPeriodMaster> paymentPeriod= paymentPeriodMasterRepository.findById(id);
        if(paymentPeriod.isPresent()){
            PaymentPeriodMaster s=paymentPeriod.get();
            paymentPeriod.get().setActive(false);
            paymentPeriod.get().setDelete(true);
            paymentPeriod.get().setDeactive(false);
            return paymentPeriodMasterRepository.save(s);
        }
        else {
            throw new RuntimeException("PaymentPeriod with Id"+id+"not found");
        }
    }
    @PrePersist
    public PaymentPeriodMaster paymentPeriodTime(int id, String newPaymentPeriod ){
        Optional<PaymentPeriodMaster> paymentPeriod = paymentPeriodMasterRepository.findById(id);
        if(paymentPeriod.isPresent()) {
            PaymentPeriodMaster s = paymentPeriod.get();
            s.setPaymentPeriodName(newPaymentPeriod);
            s.setLastUpdate(LocalDateTime.now());
            return  paymentPeriodMasterRepository.save(s);
        }
        else{
            throw  new RuntimeException("PaymentPeriod with Id "+id+" not found");
        }
    }

}

