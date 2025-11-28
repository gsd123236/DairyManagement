package com.dairy.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.springframework.scheduling.quartz.LocalDataSourceJobStore;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class DairySubscription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int id;
    private LocalDate startDate;
    private  LocalDate endDate;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "sub_id")
    private  SubscriptionType subscriptionType;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "dairy_id")
    private  DairyRegistration dairyRegistration;
    private boolean status;

    public DairySubscription(int id, LocalDate startDate, LocalDate endDate, SubscriptionType subscriptionType, DairyRegistration dairyRegistration, boolean status) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.subscriptionType = subscriptionType;
        this.dairyRegistration = dairyRegistration;
        this.status = status;
    }

    public DairySubscription() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate  getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(SubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public DairyRegistration getDairyRegistration() {
        return dairyRegistration;
    }

    public void setDairyRegistration(DairyRegistration dairyRegistration) {
        this.dairyRegistration = dairyRegistration;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
