package com.dairy.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
public class CustomerInvoice2 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private LocalDate date;
    private double milkAmount;
    private double received;
    private double balance;
    private double amountdue;
    private boolean pendingStatus;

    @Column( nullable = false)
    private long invoiceNumber;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private CustomerRegistration customerRegistration;

    @ManyToOne
    @JoinColumn(name="dairy_id")
    @JsonBackReference
    private DairyRegistration dairyRegistration;
//    private static long counter = 0;

    // ✅ This method will auto-generate invoice number before save
  public void setInvoiceNumber(long invoiceNumber){
      this.invoiceNumber=invoiceNumber;

  }

    public long getInvoiceNumber() {
        return invoiceNumber;
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

    public double getReceived() {
        return received;
    }

    public void setReceived(double received) {
        this.received = received;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getAmountdue() {
        return amountdue;
    }

    public void setAmountdue(double amountdue) {
        this.amountdue = amountdue;
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

    public CustomerInvoice2() {}

    public CustomerInvoice2(int id, LocalDate date, long invoiceNumber, double milkAmount,
                            double received, double balance, boolean pendingStatus,
                            double amountdue, CustomerRegistration customerRegistration,
                            DairyRegistration dairyRegistration) {
        this.id = id;
        this.date = date;
        this.invoiceNumber = invoiceNumber;
        this.milkAmount = milkAmount;
        this.received = received;
        this.balance = balance;
        this.pendingStatus = pendingStatus;
        this.amountdue = amountdue;
        this.customerRegistration = customerRegistration;
        this.dairyRegistration = dairyRegistration;
    }
}
