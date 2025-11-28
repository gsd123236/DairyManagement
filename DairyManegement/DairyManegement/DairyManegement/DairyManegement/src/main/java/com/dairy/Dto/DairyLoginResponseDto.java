package com.dairy.Dto;

public class DairyLoginResponseDto {
    private Integer dId;
    private String firstName;
    private String lastName;
    private String mobile;

    public DairyLoginResponseDto(Integer dId, String firstName, String lastName, String mobile) {
        this.dId=dId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
    }

    // Getters and Setters

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
