package com.dairy.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL) // This will remove null fields in the JSON response
public class FarmerMilkCollectionTotalDto {

    private Integer farmerId;
    private String firstName;
    private String lastName;
    private Integer dairyId;
    private String milkType;
    private BigDecimal totalLiters;
    private BigDecimal totalPrice;

    // Default Constructor
    public FarmerMilkCollectionTotalDto() {
    }

    public FarmerMilkCollectionTotalDto(Integer farmerId, String firstName, String lastName, String milkType, Integer dairyId, BigDecimal totalLiters, BigDecimal totalPrice) {
        this.farmerId = farmerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dairyId = dairyId;
        this.milkType = milkType;
        this.totalLiters = totalLiters;
        this.totalPrice = totalPrice;
    }

    // Getters and Setters
    public Integer getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(Integer farmerId) {
        this.farmerId = farmerId;
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

    public Integer getDairyId() {
        return dairyId;
    }

    public void setDairyId(Integer dairyId) {
        this.dairyId = dairyId;
    }

    public String getMilkType() {
        return milkType;
    }

    public void setMilkType(String milkType) {
        this.milkType = milkType;
    }

    public BigDecimal getTotalLiters() {
        return totalLiters;
    }

    public void setTotalLiters(BigDecimal totalLiters) {
        this.totalLiters = totalLiters;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
