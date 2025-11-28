package com.dairy.Entity;

import jakarta.persistence.*;
@Entity
public class CustomerBalanceSheet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(name="customer_id")
    private CustomerRegistration customerId;
    private Double balance;
    @ManyToOne
    @JoinColumn(name="dairy_id")
    private DairyRegistration dairyRegistration;

    public CustomerBalanceSheet() {
    }

    public CustomerBalanceSheet(int id, CustomerRegistration customerId, Double balance, DairyRegistration dairyRegistration) {
        this.id = id;
        this.customerId = customerId;
        this.balance = balance;
        this.dairyRegistration = dairyRegistration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CustomerRegistration getCustomerId() {
        return customerId;
    }

    public void setCustomerId(CustomerRegistration customerId) {
        this.customerId = customerId;
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
