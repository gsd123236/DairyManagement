package com.dairy.Dto;

import com.dairy.Entity.FarmerRegistration;

import java.util.List;

public class FarmerDTO {
    private int id;
    private String code;
    private String firstName;
    private String lastName;
    private String mobile;
    private  String milkTypes;
    private int dairyId;// This is now last

    public FarmerDTO(FarmerRegistration farmerRegistration, String milkTypes) {
        this.id = farmerRegistration.getId();
        this.code = farmerRegistration.getCode();
        this.firstName = farmerRegistration.getFirstName();
        this.lastName = farmerRegistration.getLastName();
        this.mobile = farmerRegistration.getMobile();
        this.milkTypes = milkTypes;
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
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getMilkTypes() {
        return milkTypes;
    }
    public void setMilkTypes(String milkTypes) {
        this.milkTypes = milkTypes;
    }
}
