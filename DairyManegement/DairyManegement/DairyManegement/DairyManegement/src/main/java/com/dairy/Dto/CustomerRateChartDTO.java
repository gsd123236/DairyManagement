package com.dairy.Dto;

import com.dairy.Entity.*;

import java.util.List;

public class CustomerRateChartDTO {
    private CustomerRateChart customerRateChart;
    private List<CustomerRateFat> customerRateFats;
    private List<CustomerRateSnf> customerRateSnfs;
    private List<CustomerRateClr> customerRateClrs;

    public CustomerRateChart getCustomerRateChart() {
        return customerRateChart;
    }

    public void setCustomerRateChart(CustomerRateChart customerRateChart) {
        this.customerRateChart = customerRateChart;
    }


    public List<CustomerRateFat> getCustomerRateFats() {
        return customerRateFats;
    }

    public void setCustomerRateFats(List<CustomerRateFat> customerRateFats) {
        this.customerRateFats = customerRateFats;
    }

    public List<CustomerRateSnf> getCustomerRateSnfs() {
        return customerRateSnfs;
    }

    public void setCustomerRateSnfs(List<CustomerRateSnf> customerRateSnfs) {
        this.customerRateSnfs = customerRateSnfs;
    }

    public List<CustomerRateClr> getCustomerRateClrs() {
        return customerRateClrs;
    }

    public void setCustomerRateClrs(List<CustomerRateClr> customerRateClrs) {
        this.customerRateClrs = customerRateClrs;
    }
}
