package com.dairy.Dto;

import com.dairy.Entity.FarmerAccount;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class FarmerAccountDTO {
    private  int id;
    private long credit;
    private long debit;
    private double Balance;
    private long advance;
    private String number;
    private LocalDate Date;
    private String PaymentMethod;

    public FarmerAccountDTO(FarmerAccount farmerAccount,String paymentMethod) {
        this.id = farmerAccount.getId();
        this.credit = farmerAccount.getCredit();
        this.debit = farmerAccount.getDebit();
        this.Balance = farmerAccount.getBalance();
        this.advance = farmerAccount.getAdvance();
        this.Date=farmerAccount.getDate();
        this.number=farmerAccount.getNumber();
        this.PaymentMethod = paymentMethod;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCredit() {
        return credit;
    }

    public void setCredit(long credit) {
        this.credit = credit;
    }

    public long getDebit() {
        return debit;
    }

    public void setDebit(long debit) {
        this.debit = debit;
    }

    public double getBalance() {
        return Balance;
    }

    public void setBalance(double balance) {
        Balance = balance;
    }

    public long getAdvance() {
        return advance;
    }

    public void setAdvance(long advance) {
        this.advance = advance;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }

    public String getPaymentMethod() {
        return PaymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        PaymentMethod = paymentMethod;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
