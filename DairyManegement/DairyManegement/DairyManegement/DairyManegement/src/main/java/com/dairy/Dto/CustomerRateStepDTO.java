package com.dairy.Dto;

import java.util.List;

public class CustomerRateStepDTO {
    private Double step;
    private Double stepAmount;

    public CustomerRateStepDTO(Double step, Double stepAmount) {
        this.step = step;
        this.stepAmount = stepAmount;
    }


    // Getters and Setters
    public Double getStep() { return step; }
    public void setStep(Double step) { this.step = step; }

    public Double getStepAmount() { return stepAmount; }
}
