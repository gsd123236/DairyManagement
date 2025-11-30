package com.dairy.Entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.persistence.*;

@Entity
public class FarmerBalanceSheet {
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(name="farmer_id")
    private FarmerRegistration farmerId;
    private Double balance;
    @ManyToOne
    @JoinColumn(name="dairy_id")
    private DairyRegistration dairyRegistration;

    public FarmerBalanceSheet() {
    }

    public FarmerBalanceSheet(int id, FarmerRegistration farmerId, Double balance, DairyRegistration dairyRegistration) {
        this.id = id;
        this.farmerId = farmerId;
        this.balance = balance;
        this.dairyRegistration = dairyRegistration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FarmerRegistration getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(FarmerRegistration farmerId) {
        this.farmerId = farmerId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public DairyRegistration getDairyRegistration() {
        return dairyRegistration;
    }

    public void setDairyRegistration(DairyRegistration dairyRegistration) {
        this.dairyRegistration = dairyRegistration;
    }
}
