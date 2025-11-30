package com.dairy.Entity;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;

import java.time.LocalDateTime;

@Entity
@Table(name = "subscription_type")
public class SubscriptionType{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String planName;
    private int duration;
    private int amount;
    private boolean active;
    private boolean inactive;
    private boolean isdelete;
    private LocalDateTime createDate;
    private LocalDateTime UpdateDate;

    public SubscriptionType() {
    }

    public SubscriptionType(int id, String planName, int duration, int amount, boolean active, boolean inactive, boolean isdelete, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.planName = planName;
        this.duration = duration;
        this.amount = amount;
        this.active = active;
        this.inactive = inactive;
        this.isdelete = isdelete;
        this.createDate = createDate;
        UpdateDate = updateDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isInactive() {
        return inactive;
    }

    public void setInactive(boolean inactive) {
        this.inactive = inactive;
    }

    public boolean isIsdelete() {
        return isdelete;
    }

    public void setIsdelete(boolean isdelete) {
        this.isdelete = isdelete;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return UpdateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        UpdateDate = updateDate;
    }
}
