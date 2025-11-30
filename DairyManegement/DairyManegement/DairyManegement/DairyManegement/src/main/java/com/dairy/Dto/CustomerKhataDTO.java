package com.dairy.Dto;

import com.dairy.Entity.CustomerKhata;
import com.dairy.Entity.CustomerRegistration;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CustomerKhataDTO {
    private int id;
    private LocalDateTime transactionDate;
    private double credit;
    private double debit;
    private double balance;
    private String comments;
    private LocalDate date;
    //    private  String paymentMethod;
//    private String number;
    public CustomerKhataDTO (CustomerKhata customer, String paymentMethod){
        this.id=customer.getId();
        this.transactionDate=customer.getTransactionDate();
        this.credit=customer.getCredit();
        this.debit=customer.getDebit();
        this.balance=customer.getBalance();
        this.comments=customer.getComments();
        this.date=customer.getDate();
//        this.paymentMethod=paymentMethod;
//        this.number=customer.getNumber();

    }

    public CustomerKhataDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public double getDebit() {
        return debit;
    }

    public void setDebit(double debit) {
        this.debit = debit;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

//    public String getPaymentMethod() {
//        return paymentMethod;
//    }
//
//    public void setPaymentMethod(String paymentMethod) {
//        this.paymentMethod = paymentMethod;
//    }
//
//    public String getNumber() {
//        return number;
//    }
//
//    public void setNumber(String number) {
//        this.number = number;
//    }


}
