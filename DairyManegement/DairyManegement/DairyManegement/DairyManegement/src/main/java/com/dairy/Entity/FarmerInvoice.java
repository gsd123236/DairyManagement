package com.dairy.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class FarmerInvoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private LocalDate date;
    private double milkAmount;
    private double amountdeducation;
    private double amountDeposite;
    private double amountdue;
    private boolean pendingStatus;
    private double balance;
    @ManyToOne
    @JoinColumn(name = "farmer_id")
    @JsonBackReference
    private FarmerRegistration farmerRegistration;
    @ManyToOne
    @JoinColumn(name="dairy_id")
    @JsonBackReference
    private DairyRegistration dairyRegistration;

    public FarmerInvoice() {
    }

    public FarmerInvoice(int id, LocalDate date, double milkAmount, double amountdue, double amountdeducation,double balance,double amountDeposite, boolean pendingStatus, FarmerRegistration farmerRegistration, DairyRegistration dairyRegistration) {
        this.id = id;
        this.date = date;
        this.milkAmount = milkAmount;
        this.amountdue = amountdue;
        this.balance = balance;
        this.amountdeducation=amountdeducation;
        this.amountDeposite=amountDeposite;

        this.pendingStatus=pendingStatus;
        this.farmerRegistration = farmerRegistration;
        this.dairyRegistration = dairyRegistration;
    }

    public boolean isPendingStatus() {
        return pendingStatus;
    }

    public void setPendingStatus(boolean pendingStatus) {
        this.pendingStatus = pendingStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getMilkAmount() {
        return milkAmount;
    }

    public void setMilkAmount(double milkAmount) {
        this.milkAmount = milkAmount;
    }

    public double getAmountdue() {
        return amountdue;
    }

    public void setAmountdue(double amountdue) {
        this.amountdue = amountdue;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public FarmerRegistration getFarmerRegistration() {
        return farmerRegistration;
    }

    public void setFarmerRegistration(FarmerRegistration farmerRegistration) {
        this.farmerRegistration = farmerRegistration;
    }

    public DairyRegistration getDairyRegistration() {
        return dairyRegistration;
    }

    public void setDairyRegistration(DairyRegistration dairyRegistration) {
        this.dairyRegistration = dairyRegistration;
    }

    public double getAmountdeducation() {
        return amountdeducation;
    }

    public void setAmountdeducation(double amountdeducation) {
        this.amountdeducation = amountdeducation;
    }

    public double getAmountDeposite() {
        return amountDeposite;
    }

    public void setAmountDeposite(double amountDeposite) {
        this.amountDeposite = amountDeposite;
    }
}