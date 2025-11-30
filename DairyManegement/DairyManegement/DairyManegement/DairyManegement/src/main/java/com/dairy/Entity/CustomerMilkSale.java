package com.dairy.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class CustomerMilkSale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int id;
    @JsonSerialize(using = ToStringSerializer.class)
    private int milkType;
    @JsonSerialize(using = ToStringSerializer.class)
    private int shift;
    @JsonSerialize(using = ToStringSerializer.class)
    private double liter;
    @JsonSerialize(using = ToStringSerializer.class)
    private double fat;
    @JsonSerialize(using = ToStringSerializer.class)
    private double clr;
    @JsonSerialize(using = ToStringSerializer.class)
    private double snf;
    @JsonSerialize(using = ToStringSerializer.class)
    private double rate;
    @JsonSerialize(using = ToStringSerializer.class)
    private double amount;
    @JsonSerialize(using = ToStringSerializer.class)
    private double paid;
    @JsonSerialize(using = ToStringSerializer.class)
    private double balance;
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate date;
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDateTime transactionDate;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference(value = "customer-milk-sale")
    private CustomerRegistration customerRegistration;

    @ManyToOne
    @JoinColumn(name = "dId")
    @JsonBackReference(value = "dairy-milk-sale")
    private DairyRegistration dairyRegistration;

    public CustomerMilkSale() {
    }

    public CustomerMilkSale(int id, int milkType, int shift, double liter, double fat,double clr,double snf,
                            double rate, double amount, double paid, double balance, LocalDate date,
                            LocalDateTime transactionDate, CustomerRegistration customerRegistration,DairyRegistration dairyRegistration) {
        this.id = id;
        this.milkType = milkType;
        this.shift = shift;
        this.liter = liter;
        this.fat = fat;
        this.clr = clr;
        this.snf = snf;
        this.rate = rate;
        this.amount = amount;
        this.paid = paid;
        this.balance = balance;
        this.date = date;
        this.transactionDate = transactionDate;
        this.customerRegistration = customerRegistration;
        this.dairyRegistration=dairyRegistration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMilkType() {
        return milkType;
    }

    public void setMilkType(int milkType) {
        this.milkType = milkType;
    }

    public int getShift() {
        return shift;
    }

    public void setShift(int shift) {
        this.shift = shift;
    }

    public double getLiter() {
        return liter;
    }

    public void setLiter(double liter) {
        this.liter = liter;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getClr() {
        return clr;
    }

    public void setClr(double clr) {
        this.clr = clr;
    }

    public double getSnf() {
        return snf;
    }

    public void setSnf(double snf) {
        this.snf = snf;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPaid() {
        return paid;
    }

    public void setPaid(double paid) {
        this.paid = paid;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public CustomerRegistration getCustomerRegistration() {
        return customerRegistration;
    }

    public void setCustomerRegistration(CustomerRegistration customerRegistration) {
        this.customerRegistration = customerRegistration;
    }

    public DairyRegistration getDairyRegistration() {
        return dairyRegistration;
    }

    public void setDairyRegistration(DairyRegistration dairyRegistration) {
        this.dairyRegistration = dairyRegistration;
    }
}

