package com.dairy.Dto;

import com.dairy.Dto.DairyRateStartingAmountDTO;
import com.dairy.Dto.DairyRateStartingAmountDTO1;
import com.dairy.Dto.DairyRateStepDTO;

import java.util.List;

public class DairyRateResponseDTO {
    private DairyRateStartingAmountDTO1 dairyRateStartingAmount;
    private List<DairyRateStepDTO> dairyRateFats;
    private List<DairyRateStepDTO> dairyRateSnfs;
    private List<DairyRateStepDTO> dairyRateClrs;

    // Constructors
    public DairyRateResponseDTO() {}

    public DairyRateResponseDTO(DairyRateStartingAmountDTO1 dairyRateStartingAmount,
                                List<DairyRateStepDTO> dairyRateFats,
                                List<DairyRateStepDTO> dairyRateSnfs,
                                List<DairyRateStepDTO> dairyRateClrs) {
        this.dairyRateStartingAmount = dairyRateStartingAmount;
        this.dairyRateFats = dairyRateFats;
        this.dairyRateSnfs = dairyRateSnfs;
        this.dairyRateClrs = dairyRateClrs;
    }

    // Getters and Setters
    public DairyRateStartingAmountDTO1 getDairyRateStartingAmount() { return dairyRateStartingAmount; }
    public void setDairyRateStartingAmount(DairyRateStartingAmountDTO1 dairyRateStartingAmount) { this.dairyRateStartingAmount = dairyRateStartingAmount; }

    public List<DairyRateStepDTO> getDairyRateFats() { return dairyRateFats; }
    public void setDairyRateFats(List<DairyRateStepDTO> dairyRateFats) { this.dairyRateFats = dairyRateFats; }

    public List<DairyRateStepDTO> getDairyRateSnfs() { return dairyRateSnfs; }
    public void setDairyRateSnfs(List<DairyRateStepDTO> dairyRateSnfs) { this.dairyRateSnfs = dairyRateSnfs; }

    public List<DairyRateStepDTO> getDairyRateClrs() { return dairyRateClrs; }
    public void setDairyRateClrs(List<DairyRateStepDTO> dairyRateClrs) { this.dairyRateClrs = dairyRateClrs; }
}
