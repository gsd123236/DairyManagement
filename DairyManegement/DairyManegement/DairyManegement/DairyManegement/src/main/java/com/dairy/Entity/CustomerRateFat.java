package com.dairy.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
@Entity
public class CustomerRateFat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "sId")
    @JsonBackReference
    private CustomerRateChart customerRateChart;
    @Column(name = "step", nullable = false)
    private double Step;

    private double stepAmount;

    private boolean isActive=true;
    private boolean isDelete;

    @Column(updatable = false)
    private LocalDateTime createDate;
    @LastModifiedDate
    private LocalDateTime updateDate;

//    public DairyRateFat() {
//    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CustomerRateChart getCustomerRateChart() {
        return customerRateChart;
    }

    public void setCustomerRateChart(CustomerRateChart customerRateChart) {
        this.customerRateChart = customerRateChart;
    }

    public double getStep() {
        return Step;
    }

    public void setStep(double step) {
        Step = step;
    }


    public double getStepAmount() {
        return stepAmount;
    }

    public void setStepAmount(double stepAmount) {
        this.stepAmount = stepAmount;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }


}
