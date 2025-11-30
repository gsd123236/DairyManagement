package com.dairy.Dto;

public class CustomerRateStartingAmountDTO1 {

    private Double startingAmount;
    private Double clrIncrementBy;
    private String milkType;

    public CustomerRateStartingAmountDTO1(Double startingAmount, Double clrIncrementBy, String milkType) {
        this.startingAmount = startingAmount;
        this.clrIncrementBy = clrIncrementBy;
        this.milkType = milkType;
    }

    // Getters and Setters
    public Double getStartingAmount() { return startingAmount; }
    public void setStartingAmount(Double startingAmount) { this.startingAmount = startingAmount; }

    public Double getClrIncrementBy() { return clrIncrementBy; }
    public void setClrIncrementBy(Double clrIncrementBy) { this.clrIncrementBy = clrIncrementBy; }

    public String getMilkType() { return milkType; }
    public void setMilkType(String milkType) { this.milkType = milkType; }
}
