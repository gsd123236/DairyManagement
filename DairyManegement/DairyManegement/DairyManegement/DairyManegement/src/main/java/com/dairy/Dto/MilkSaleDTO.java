package com.dairy.Dto;

import com.dairy.Entity.CustomerMilkSale;

import java.time.LocalDate;

public class MilkSaleDTO {
    private  int id;;
    private String milkType;
    private  String shift;
    private double liter;
    private double fat;
    private double rate;
    private double amount;
    private double paid;
    private double balance;
    private LocalDate date;

    public MilkSaleDTO() {
    }

    public MilkSaleDTO(CustomerMilkSale milkesale, String milkType, String shift) {
        this.id =milkesale.getId();
        this.milkType = milkType;
        this.shift=shift;
        this.liter = milkesale.getLiter();
        this.fat = milkesale.getFat();
        this.rate = milkesale.getRate();
        this.amount = milkesale.getAmount();
        this.paid = milkesale.getPaid();
        this.balance = milkesale.getBalance();
        this.date = milkesale.getDate();
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMilkType() {
        return milkType;
    }

    public void setMilkType(String milkType) {
        this.milkType = milkType;
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
}
