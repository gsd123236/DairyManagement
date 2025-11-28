package com.dairy.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class FarmerInvoice2 {
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
    @Column( nullable = false)
    private long invoiceNumber;
    @ManyToOne
    @JoinColumn(name = "farmer_id")
    @JsonBackReference
    private FarmerRegistration farmerRegistration;
    @ManyToOne
    @JoinColumn(name="dairy_id")
    @JsonBackReference
    private DairyRegistration dairyRegistration;

    public FarmerInvoice2() {
    }

    public FarmerInvoice2(int id, double milkAmount, double amountdeducation, double amountDeposite,LocalDate date ,double amountdue, boolean pendingStatus, double balance, long invoiceNumber, FarmerRegistration farmerRegistration, DairyRegistration dairyRegistration) {
        this.id = id;
        this.milkAmount = milkAmount;
        this.amountdeducation = amountdeducation;
        this.amountDeposite = amountDeposite;
        this.date=date;
        this.amountdue = amountdue;
        this.pendingStatus = pendingStatus;
        this.balance = balance;
        this.invoiceNumber = invoiceNumber;
        this.farmerRegistration = farmerRegistration;
        this.dairyRegistration = dairyRegistration;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMilkAmount() {
        return milkAmount;
    }

    public void setMilkAmount(double milkAmount) {
        this.milkAmount = milkAmount;
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

    public double getAmountdue() {
        return amountdue;
    }

    public void setAmountdue(double amountdue) {
        this.amountdue = amountdue;
    }

    public boolean isPendingStatus() {
        return pendingStatus;
    }

    public void setPendingStatus(boolean pendingStatus) {
        this.pendingStatus = pendingStatus;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public long getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(long invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
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
}
