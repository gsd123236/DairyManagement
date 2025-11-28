package com.dairy.Dto;

import com.dairy.Entity.CustomerRegistration;

import java.util.List;

public class CustomerDTO {
    private int id;
    private  String code;
    private String name;
    private String mobile;
    private  String milkTypes;
    private String cowMilkRateMaster;
    private double cowMilkRate;
    private  String buffaloMilkRateMaster;
    private double buffaloMilkRate;
    private String address;
    private String village;
    private String taluka;
    private String district;
    private String details;
    public CustomerDTO(CustomerRegistration customer,String milkTypes,String buffaloMilkRateMaster,String cowMilkRateMaster){
        this.id=customer.getId();
        this.code=customer.getCode();
        this.name=customer.getName();
        this.mobile=customer.getMobile();
        this.milkTypes = milkTypes;
        this.cowMilkRateMaster=cowMilkRateMaster;
        this.cowMilkRate=customer.getCowMilkRate();
        this.buffaloMilkRateMaster=buffaloMilkRateMaster;
        this.buffaloMilkRate=customer.getBuffaloMilkRate();
        this.address=customer.getAddress();
        this.village=customer.getVillage();
        this.taluka=customer.getTaluka();
        this.district=customer.getDistrict();
        this.details=customer.getDetails();
    }

    public CustomerDTO() {
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

    public void setMobile(String mobile) {
        this.mobile= mobile;
    }

    public String getMilkTypes() {
        return milkTypes;
    }
    public void setMilkTypes(String  milkTypes) {
        this.milkTypes = milkTypes;
    }

    public String getCowMilkRateMaster() {
        return cowMilkRateMaster;
    }

    public void setCowMilkRateMaster(String cowMilkRateMaster) {
        this.cowMilkRateMaster = cowMilkRateMaster;
    }

    public double getCowMilkRate() {
        return cowMilkRate;
    }

    public void setCowMilkRate(double cowMilkRate) {
        this.cowMilkRate = cowMilkRate;
    }

    public String getBuffaloMilkRateMaster() {
        return buffaloMilkRateMaster;
    }

    public void setBuffaloMilkRateMaster(String buffaloMilkRateMaster) {
        this.buffaloMilkRateMaster = buffaloMilkRateMaster;
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

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "id=" + id +
                ", code=" + code +
                ", name='" + name + '\'' +
                ", phone='" + mobile + '\'' +
                ", milkTypes=" + milkTypes +
                ", cowMilkRateMaster='" + cowMilkRateMaster + '\'' +
                ", cowMilkRate=" + cowMilkRate +
                ", buffaloMilkRateMaster='" + buffaloMilkRateMaster + '\'' +
                ", buffaloMilkRate=" + buffaloMilkRate +
                ", address='" + address + '\'' +
                ", village='" + village + '\'' +
                ", taluka='" + taluka + '\'' +
                ", district='" + district + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
