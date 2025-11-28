package com.dairy.Dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class DairyAdminDTO {
    private int dId;
    private String firstName;
    private String lastName;
    private String dairyName;
    private String password;
    private String mobile;
    private String village;
    private String taluka;
    private String district;

    private String state;
    private String collectionTypeId;
    private String milkTypeId;
    private String collectionShiftId;
    private String paymentPeriodId;

    private LocalDate createdDate;
    private LocalDateTime updateDate;


    public DairyAdminDTO(Integer dId ,String firstName, String lastName, String dairyName, String password, String mobile,
                         String village, String taluka, String district, String state, String collectionTypeId,
                         String milkTypeId, String collectionShiftId, String paymentPeriodId, LocalDate createdDate,
                         LocalDateTime updateDate) {
        this.dId=dId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dairyName = dairyName;
        this.password = password;
        this.mobile = mobile;
        this.village = village;
        this.taluka = taluka;
        this.district = district;
        this.state = state;
        this.collectionTypeId = collectionTypeId;
        this.milkTypeId = milkTypeId;
        this.collectionShiftId = collectionShiftId;
        this.paymentPeriodId = paymentPeriodId;
        this.createdDate = createdDate;
        this.updateDate = updateDate;
    }

    public int getdId() {
        return dId;
    }

    public void setdId(int dId) {
        this.dId = dId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDairyName() {
        return dairyName;
    }

    public void setDairyName(String dairyName) {
        this.dairyName = dairyName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getTaluka() {
        return taluka;
    }

    public void setTaluka(String taluka) {
        this.taluka = taluka;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCollectionTypeId() {
        return collectionTypeId;
    }

    public void setCollectionTypeId(String collectionTypeId) {
        this.collectionTypeId = collectionTypeId;
    }

    public String getMilkTypeId() {
        return milkTypeId;
    }

    public void setMilkTypeId(String milkTypeId) {
        this.milkTypeId = milkTypeId;
    }

    public String getCollectionShiftId() {
        return collectionShiftId;
    }

    public void setCollectionShiftId(String collectionShiftId) {
        this.collectionShiftId = collectionShiftId;
    }

    public String getPaymentPeriodId() {
        return paymentPeriodId;
    }

    public void setPaymentPeriodId(String paymentPeriodId) {
        this.paymentPeriodId = paymentPeriodId;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
// Getters and Setters
    // (Generate using IDE or Lombok if available)
}
