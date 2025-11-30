package com.dairy.Dto;

import java.util.List;

public class CustomerRateResponseDTO {

    private DairyRateStartingAmountDTO1 dairyRateStartingAmount;
    private List<CustomerRateStepDTO> customerRateFats;
    private List<CustomerRateStepDTO> customerRateSnfs;
    private List<CustomerRateStepDTO> customerRateClrs;

    // Constructors
    public CustomerRateResponseDTO() {}

    public CustomerRateResponseDTO(DairyRateStartingAmountDTO1 dairyRateStartingAmount,
                                List<CustomerRateStepDTO> customerRateFats,
                                List<CustomerRateStepDTO> customerRateSnfs,
                                List<CustomerRateStepDTO> customerRateClrs) {
        this.dairyRateStartingAmount = dairyRateStartingAmount;
        this.customerRateFats = customerRateFats;
        this.customerRateSnfs = customerRateSnfs;
        this.customerRateClrs = customerRateClrs;
    }

    // Getters and Setters
    public DairyRateStartingAmountDTO1 getDairyRateStartingAmount() { return dairyRateStartingAmount; }
    public void setDairyRateStartingAmount(DairyRateStartingAmountDTO1 dairyRateStartingAmount) { this.dairyRateStartingAmount = dairyRateStartingAmount; }

    public List<CustomerRateStepDTO> getCustomerRateFats() { return customerRateFats; }
    public void setCustomerRateFats(List<CustomerRateStepDTO>customerRateFats) { this.customerRateFats = customerRateFats; }

    public List<CustomerRateStepDTO> getCustomerRateSnfs() { return customerRateSnfs; }
    public void setCustomerRateSnfs(List<CustomerRateStepDTO> customerRateSnfs) { this.customerRateSnfs = customerRateSnfs; }

    public List<CustomerRateStepDTO> getCustomerRateClrs() { return customerRateClrs; }
    public void setCustomerRateClrs(List<CustomerRateStepDTO> customerRateClrs) { this.customerRateClrs = customerRateClrs; }
}
