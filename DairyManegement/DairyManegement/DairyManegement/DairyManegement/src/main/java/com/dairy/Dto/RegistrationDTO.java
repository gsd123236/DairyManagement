package com.dairy.Dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.List;
import java.util.Map;

public class RegistrationDTO{
    private String state;
    private List<Map<String, Object>> milkName;
    private String collectionType;
    private List<Map<String, Object>> shiftType;
    private String paymentType;


    public RegistrationDTO(String state, List<Map<String, Object>> milkName, String collectionType,
                           List<Map<String, Object>> shiftType, String paymentType) {
        this.state = state;
        this.milkName = milkName;
        this.collectionType = collectionType;
        this.shiftType = shiftType;
        this.paymentType = paymentType;
    }

    public String getState() {
        return state;
    }
    public List<Map<String, Object>> getMilkName() {  // ✅ Correct return type
        return milkName;
    }
    public String getCollectionType() {
        return collectionType;
    }

    public List<Map<String, Object>> getShiftType() {
        return shiftType;
    }

    public String getPaymentType() {
        return paymentType;
    }
}
