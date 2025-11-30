package com.dairy.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="customer_khata")
public class CustomerKhata {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime transactionDate;
    @JsonSerialize(using = ToStringSerializer.class)
    private  double credit;
    @JsonSerialize(using = ToStringSerializer.class)
    private double debit;
    @JsonSerialize(using = ToStringSerializer.class)
    private double balance;
    private String comments;
    private LocalDate date;
    //    private int paymentMethod;
//    private String number;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private CustomerRegistration customerRegistration;

    public CustomerKhata() {
    }

    public CustomerKhata(int id, LocalDateTime transactionDate, double credit, double debit, double balance, String comments, LocalDate date, int paymentMethod, String number, CustomerRegistration customerRegistration) {
        this.id = id;
        this.transactionDate = transactionDate;
        this.credit = credit;
        this.debit = debit;
        this.balance = balance;
        this.comments = comments;
        this.date = date;
//        this.paymentMethod = paymentMethod;
//        this.number = number;
        this.customerRegistration = customerRegistration;
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

//    public int getPaymentMethod() {
//        return paymentMethod;
//    }
//
//    public void setPaymentMethod(int paymentMethod) {
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

    public CustomerRegistration getCustomerRegistration() {
        return customerRegistration;
    }

    public void setCustomerRegistration(CustomerRegistration customerRegistration) {
        this.customerRegistration = customerRegistration;
    }
}
