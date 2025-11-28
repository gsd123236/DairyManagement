
package com.dairy.Dto;

import java.math.BigDecimal;

public class FarmerMilkCollectionDto {
    private String farmerCode;
    private String firstName;
    private String lastName;
    private String totalPrice;


    public FarmerMilkCollectionDto() {
    }

    public FarmerMilkCollectionDto(String farmerCode, String firstName, String lastName, String totalPrice) {
        this.farmerCode = farmerCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalPrice = totalPrice;
    }

    public FarmerMilkCollectionDto(int intValue, String s, String s1, String s2, BigDecimal bigDecimal, BigDecimal bigDecimal1) {
    }

    public String getFarmerCode() {
        return farmerCode;
    }

    public void setFarmerCode(String farmerCode) {
        this.farmerCode = farmerCode;
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

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
