package com.dairy.Dto;

public class DairyRateStartingAmountDTO {
    private double startingAmount;
    private double clrIncrementBy;
    private String milkType;

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
}
