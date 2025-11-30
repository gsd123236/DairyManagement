package com.dairy.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity

public class DairyRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dId;

    private String firstName;
    private String lastName;
    private String dairyName;
    private String password;

    private String mobile;
    private String otp;
    private String village;
    private String taluka;
    private String district;
    //    private LocalDateTime otpExpiryTime;
    private String stateId;
    @JsonProperty("collectionTypeId")
    private String collectionTypeId;

    @Column(columnDefinition = "TEXT")
    private String milkTypeId;
    @Column(columnDefinition = "TEXT")
    private String collectionShiftId;
    private String paymentPeriodId;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updateDate;

    private boolean isActive=true;

    private boolean isDeleted;

    private boolean isDeactive;

    @OneToMany(mappedBy = "dairyRegistration")
    @JsonManagedReference(value = "dairy-milk-sale")
    private List<CustomerMilkSale> milkSales;


//    @OneToMany(mappedBy = "dairyRegistration", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<FarmerRegistration> farmers = new ArrayList<>();
//
//    public List<FarmerRegistration> getFarmers() {
//        return farmers;
//    }
//
//    public void setFarmers(List<FarmerRegistration> farmers) {
//        this.farmers = farmers;
//    }


//    @OneToMany(mappedBy = "dairyRegistration", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<DairyRateStartingAmount> dairyRateStartingAmounts;
//
//    public List<DairyRateStartingAmount> getDairyRateStartingAmounts() {
//        return dairyRateStartingAmounts;
//    }
//
//    public void setDairyRateStartingAmounts(List<DairyRateStartingAmount> dairyRateStartingAmounts) {
//        this.dairyRateStartingAmounts = dairyRateStartingAmounts;
//    }


    public DairyRegistration(int dId, String firstName, String lastName, String dairyName,
                             String password, String mobile, String otp, String village,
                             String taluka, String district, String stateId, String collectionTypeId,
                             String milkTypeId, String collectionShiftId, String paymentPeriodId,
                             LocalDateTime createdDate, LocalDateTime updateDate, boolean isActive,
                             boolean isDeleted, boolean isDeactive) {
        this.dId = dId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dairyName = dairyName;
        this.password = password;
        this.mobile = mobile;
        this.otp = otp;
        this.village = village;
        this.taluka = taluka;
        this.district = district;
        this.stateId = stateId;
        this.collectionTypeId = collectionTypeId;
        this.milkTypeId = milkTypeId;
        this.collectionShiftId = collectionShiftId;
        this.paymentPeriodId = paymentPeriodId;
        this.createdDate = createdDate;
        this.updateDate = updateDate;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.isDeactive = isDeactive;
    }

    public DairyRegistration() {
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

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }



    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getCollectionTypeId() {
        return collectionTypeId;
    }

    public void setCollectionTypeId(String collectionTypeId) {
        this.collectionTypeId = collectionTypeId;
    }

    public String getPaymentPeriodId() {
        return paymentPeriodId;
    }

    public void setPaymentPeriodId(String paymentPeriodId) {
        this.paymentPeriodId = paymentPeriodId;
    }

    public String getCollectionShiftId() {
        return collectionShiftId;
    }

    public void setCollectionShiftId(String collectionShiftId) {
        this.collectionShiftId = collectionShiftId;
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

    public String getTaluka() {
        return taluka;
    }

    public void setTaluka(String taluka) {
        this.taluka = taluka;
    }

    public String getMilkTypeId() {
        return milkTypeId;
    }

    public void setMilkTypeId(String milkTypeId) {
        this.milkTypeId = milkTypeId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public boolean isDeactive() {
        return isDeactive;
    }

    public void setDeactive(boolean deactive) {
        isDeactive = deactive;
    }

    @JsonIgnore
    public List<Integer> getMilkTypeList() {
        if (milkTypeId == null || milkTypeId.isEmpty()) {
            return new ArrayList<>();
        }
        return Arrays.stream(milkTypeId.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }


}
