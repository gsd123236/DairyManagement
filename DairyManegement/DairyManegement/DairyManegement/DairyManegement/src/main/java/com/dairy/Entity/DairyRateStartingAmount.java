package com.dairy.Entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity

public class DairyRateStartingAmount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
//    @JsonIgnoreProperties(value = { "dId", "firstName", "lastName", "mobile", "milkTypeId",
//            "createdDate", "updateDate", "dairyRegistration","dairyName",
//            "password","otp","village","taluka","district","stateId","collectionShiftId",
//            "deactive","deleted","active","paymentPeriodId","dairyRateStartingAmounts"})
//    @JsonUnwrapped
    @JsonBackReference
    @JoinColumn(name = "dairyId")
    private  DairyRegistration dairyRegistration;
    private int rateChartId;
    private double startingAmount;
    private double clrIncrementBy;
    private String milkType;

    private boolean isActive=true;
    private boolean isDelete;
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime updateDate;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DairyRegistration getDairyRegistration() {
        return dairyRegistration;
    }

    public void setDairyRegistration(DairyRegistration dairyRegistration) {
        this.dairyRegistration = dairyRegistration;
    }

    public int getRateChartId() {
        return rateChartId;
    }

    public void setRateChartId(int rateChartId) {
        this.rateChartId = rateChartId;
    }

    public double getStartingAmount() {
        return startingAmount;
    }

    public void setStartingAmount(double startingAmount) {
        this.startingAmount = startingAmount;
    }

    public double getClrIncrementBy() {
        return clrIncrementBy;
    }

    public void setClrIncrementBy(double clrIncrementBy) {
        this.clrIncrementBy = clrIncrementBy;
    }

    public String getMilkType() {
        return milkType;
    }

    public void setMilkType(String milkType) {
        this.milkType = milkType;
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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

//    @Override
//    public String toString() {
//        return "DairyRateChartMaster{" +
//                "id=" + id +
//                ", dairyId=" + dairyId +
//                ", rateChartId=" + rateChartId +
//                ", startingAmount=" + startingAmount +
//                ", isActive=" + isActive +
//                ", isDelete=" + isDelete +
//                ", createdDate=" + createdDate +
//                ", updateDate=" + updateDate +
//                ", fatSteps=" + (fatSteps != null ? fatSteps.size() : 0) +
//                ", rates=" + (rates != null ? rates.size() : 0) +
//                '}';
//    }


}
