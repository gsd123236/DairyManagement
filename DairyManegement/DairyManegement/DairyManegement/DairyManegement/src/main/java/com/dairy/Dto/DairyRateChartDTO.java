package com.dairy.Dto;

import com.dairy.Entity.DairyRateClr;
import com.dairy.Entity.DairyRateSnf;
import com.dairy.Entity.DairyRateFat;
import com.dairy.Entity.DairyRateStartingAmount;
//import com.dairy.Services.DairyRateChartServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DairyRateChartDTO {
    private DairyRateStartingAmount dairyRateStartingAmount;
    private List<DairyRateFat> dairyRateFats;
    private List<DairyRateSnf> dairyRateSnfs;
    private List<DairyRateClr> dairyRateClrs;

    public DairyRateStartingAmount getDairyRateStartingAmount() {
        return dairyRateStartingAmount;
    }

    public void setDairyRateStartingAmount(DairyRateStartingAmount dairyRateStartingAmount) {
        this.dairyRateStartingAmount = dairyRateStartingAmount;
    }

    public List<DairyRateFat> getDairyRateFats() {
        return dairyRateFats;
    }

    public void setDairyRateFats(List<DairyRateFat> dairyRateFats) {
        this.dairyRateFats = dairyRateFats;
    }

    public List<DairyRateSnf> getDairyRateSnfs() {
        return dairyRateSnfs;
    }

    public void setDairyRateSnfs(List<DairyRateSnf> dairyRateSnfs) {
        this.dairyRateSnfs = dairyRateSnfs;
    }

    public List<DairyRateClr> getDairyRateClrs() {
        return dairyRateClrs;
    }

    public void setDairyRateClrs(List<DairyRateClr> dairyRateClrs) {
        this.dairyRateClrs = dairyRateClrs;
}
}
