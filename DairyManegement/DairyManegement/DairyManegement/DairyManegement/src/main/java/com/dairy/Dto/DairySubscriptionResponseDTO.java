package com.dairy.Dto;

import java.time.LocalDate;

public class DairySubscriptionResponseDTO {
    private int id;
    private  String firstName;
    private String lastName;
    private int amount;
    private String dairyName;
    private String dairyNumber;
    private String dairyAddress;
    private String subscriptionPlanName;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean status;
    public DairySubscriptionResponseDTO() {}

    public DairySubscriptionResponseDTO(int id, String firstName, String lastName, int amount, String dairyName, String dairyNumber, String dairyAddress, String subscriptionPlanName, LocalDate startDate, LocalDate endDate, boolean status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.amount = amount;
        this.dairyName = dairyName;
        this.dairyNumber = dairyNumber;
        this.dairyAddress = dairyAddress;
        this.subscriptionPlanName = subscriptionPlanName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDairyName() {
        return dairyName;
    }

    public void setDairyName(String dairyName) {
        this.dairyName = dairyName;
    }

    public String getDairyNumber() {
        return dairyNumber;
    }

    public void setDairyNumber(String dairyNumber) {
        this.dairyNumber = dairyNumber;
    }

    public String getDairyAddress() {
        return dairyAddress;
    }

    public void setDairyAddress(String dairyAddress) {
        this.dairyAddress = dairyAddress;
    }

    public String getSubscriptionPlanName() {
        return subscriptionPlanName;
    }

    public void setSubscriptionPlanName(String subscriptionPlanName) {
        this.subscriptionPlanName = subscriptionPlanName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
