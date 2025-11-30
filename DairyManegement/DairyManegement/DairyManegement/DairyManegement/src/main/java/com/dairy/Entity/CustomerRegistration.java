package com.dairy.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(
        name = "customer_registration",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"code", "dairy_id"}),
                @UniqueConstraint(columnNames = {"phone", "dairy_id"})
        }
)
public class CustomerRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int id;
    private  String code;
    private String name;
    // @Pattern(regexp = "\\d{10}", message = "Mobile number must be exactly 10 digits")
    @NotNull
    private String mobile;
    private String milkType;
    private String cowMilkRateMasterId;
    @JsonSerialize(using = ToStringSerializer.class)
    private double cowMilkRate;
    private  String buffaloMilkRateMasterId;
    @JsonSerialize(using = ToStringSerializer.class)
    private double buffaloMilkRate;
    private String address;
    private String village;
    private String taluka;
    private String district;
    private String details;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private boolean isActive=true;
    private boolean isDeactive;
    @ManyToOne
    @JoinColumn(name = "dairy_id", nullable = false)
    @JsonBackReference
    private DairyRegistration dairyRegistration;

    @OneToMany(mappedBy = "customerRegistration")
    @JsonManagedReference(value = "customer-milk-sale")
    private List<CustomerMilkSale> milkSales;


    public DairyRegistration getDairyRegistration() {
        return dairyRegistration;
    }

    public void setDairyRegistration(DairyRegistration dairyRegistration) {
        this.dairyRegistration = dairyRegistration;
    }

    public CustomerRegistration() {
    }

    public CustomerRegistration(int id, String code, String name, String mobile, String milkType, String cowMilkRateMasterId, double cowMilkRate, String buffaloMilkRateMasterId, double buffaloMilkRate, String address, String village, String taluka, String district, String details, LocalDateTime createDate, LocalDateTime updateDate, boolean isActive, boolean isDeactive) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.mobile = mobile;
        this.milkType = milkType;
        this.cowMilkRateMasterId = cowMilkRateMasterId;
        this.cowMilkRate = cowMilkRate;
        this.buffaloMilkRateMasterId = buffaloMilkRateMasterId;
        this.buffaloMilkRate = buffaloMilkRate;
        this.address = address;
        this.village = village;
        this.taluka = taluka;
        this.district = district;
        this.details = details;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.isActive = isActive;
        this.isDeactive = isDeactive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String phone) {
        this.mobile = phone;
    }


    public String getCowMilkRateMasterId() {
        return cowMilkRateMasterId;
    }

    public void setCowMilkRateMasterId(String cowMilkRateMasterId) {
        this.cowMilkRateMasterId = cowMilkRateMasterId;
    }

    public double getCowMilkRate() {
        return cowMilkRate;
    }

    public void setCowMilkRate(double cowMilkRate) {
        this.cowMilkRate = cowMilkRate;
    }

    public String getBuffaloMilkRateMasterId() {
        return buffaloMilkRateMasterId;
    }

    public void setBuffaloMilkRateMasterId(String buffaloMilkRateMasterId) {
        this.buffaloMilkRateMasterId = buffaloMilkRateMasterId;
    }

    public double getBuffaloMilkRate() {
        return buffaloMilkRate;
    }

    public void setBuffaloMilkRate(double buffaloMilkRate) {
        this.buffaloMilkRate = buffaloMilkRate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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
//    public List<Integer> getMilkType() {
//        if (milkType == null || milkType.isEmpty()) {
//            return new ArrayList<>();
//        }
//        return Arrays.stream(milkType.split(","))
//                .map(Integer::parseInt)
//                .collect(Collectors.toList());
//    }
//    public void setMilkType(List<Integer> milkTypeList) {
//        if (milkTypeList == null || milkTypeList.isEmpty()) {
//            this.milkType = "";
//        } else {
//            this.milkType = milkTypeList.stream()
//                    .map(String::valueOf)
//                    .collect(Collectors.joining(","));
//        }
//    }


    public String getMilkType() {
        return milkType;
    }

    public void setMilkType(String milkType) {
        this.milkType = milkType;
    }
}
