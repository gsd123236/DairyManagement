package com.dairy.Dto;

public class DairyRateStepDTO {
    private Double step;
    private Double stepAmount;

    public DairyRateStepDTO(Double step, Double stepAmount) {
        this.step = step;
        this.stepAmount = stepAmount;
    }

    // Getters and Setters
    public Double getStep() { return step; }
    public void setStep(Double step) { this.step = step; }

    public Double getStepAmount() { return stepAmount; }
    public void setStepAmount(Double stepAmount) { this.stepAmount = stepAmount; }
}

