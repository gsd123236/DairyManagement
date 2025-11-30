package com.dairy.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class FarmerMilkCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JsonIgnoreProperties(value = { "code", "firstName", "lastName", "mobile", "milkType",
            "createDate", "lastUpdate", "active", "delete", "dairyRegistration" ,"village",
            "district"})
    @JoinColumn(name = "farmerId", nullable = false)
    private FarmerRegistration farmerRegistration;
    @JsonSerialize(using = ToStringSerializer.class)
    private int milkTypeId;
    @JsonSerialize(using = ToStringSerializer.class)
    private int collectionShiftId;
    private String liter;
    private String fat;
    private String clr;
    private String snf;
    private String rate;
    @JsonSerialize(using = ToStringSerializer.class)
    private double totalPrice;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "dId")
    private DairyRegistration dairyRegistration;

    private boolean isActive=true;

    private boolean isDelete;
    @CreatedDate
    private LocalDate createdDate;

    @CreatedDate
    private LocalDateTime createdDateTime;
    @LastModifiedDate
    private LocalDate updateDate;

    public FarmerMilkCollection() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public void setFarmerRegistration(FarmerRegistration farmerRegistration) {
        this.farmerRegistration = farmerRegistration;
    }

    public FarmerRegistration getFarmerRegistration() {
        return farmerRegistration;
    }

    public int getMilkTypeId() {
        return milkTypeId;
    }

    public void setMilkTypeId(int milkTypeId) {
        this.milkTypeId = milkTypeId;
    }

    public int getCollectionShiftId() {
        return collectionShiftId;
    }

    public void setCollectionShiftId(int collectionShiftId) {
        this.collectionShiftId = collectionShiftId;
    }

    public String getLiter() {
        return liter;
    }

    public void setLiter(String liter) {
        this.liter = liter;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getClr() {
        return clr;
    }

    public void setClr(String clr) {
        this.clr = clr;
    }

    public String getSnf() {
        return snf;
    }

    public void setSnf(String snf) {
        this.snf = snf;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
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

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    public DairyRegistration getDairyRegistration() {
        return dairyRegistration;
    }

    public void setDairyRegistration(DairyRegistration dairyRegistration) {
        this.dairyRegistration = dairyRegistration;
    }



//    @JsonInclude(JsonInclude.Include.NON_NULL)
//    public FarmerRegistration getFarmerRegistration() {
//        if (farmerRegistration != null) {
//            FarmerRegistration minimalFarmer = new FarmerRegistration();
//            minimalFarmer.setId(farmerRegistration.getId());
//            return minimalFarmer;
//        }
//       ;
}