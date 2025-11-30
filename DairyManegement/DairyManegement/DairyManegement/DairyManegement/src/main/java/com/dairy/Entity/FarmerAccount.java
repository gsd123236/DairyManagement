package com.dairy.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="farmer_account")
public class FarmerAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @JsonSerialize(using = ToStringSerializer.class)
    private long credit;
    @JsonSerialize(using = ToStringSerializer.class)
    private long debit;
    @JsonSerialize(using = ToStringSerializer.class)
    private double Balance;
    @JsonSerialize(using = ToStringSerializer.class)
    private long advance;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime TransactionDate;
    private boolean isActive = true;
    private boolean isDeactive;
    private LocalDate date;
    @JsonSerialize(using = ToStringSerializer.class)
    private int paymentMethod;
    private String number;
    private String status;
    private  String comments;
    @ManyToOne
    @JoinColumn(name = "farmer_id")
    @JsonBackReference
    private FarmerRegistration farmerRegistration;

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public LocalDateTime getTransactionDate() {
        return TransactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        TransactionDate = transactionDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isDeactive() {
        return isDeactive;
    }

    public void setDeactive(boolean deactive) {
        isDeactive = deactive;
    }

    public FarmerRegistration getFarmerRegistration() {
        return farmerRegistration;
    }

    public void setFarmerRegistration(FarmerRegistration farmerRegistration) {
        this.farmerRegistration = farmerRegistration;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(int paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public FarmerAccount() {
    }

    public String getNumber() {
        return number;
    }


    public void setNumber(String number) {
        this.number = number;
    }
}
