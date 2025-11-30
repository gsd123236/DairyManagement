package com.dairy.Dto;

import java.util.List;

public class CustomerRateStartingAmountDTO {
    private double startingAmount;
    private double clrIncrementBy;
    private String milkType;

    public CustomerRateStartingAmountDTO(CustomerRateStartingAmountDTO1 startingAmount,
                                         List<CustomerRateStepDTO> fats, List<CustomerRateStepDTO> snfs,
                                         List<CustomerRateStepDTO> clrs) {
    }

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
