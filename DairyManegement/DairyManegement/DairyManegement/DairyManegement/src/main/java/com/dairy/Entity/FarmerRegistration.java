package com.dairy.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@DynamicUpdate
@Table(
        name = "farmer_registration",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"code", "dairy_id"}),
                @UniqueConstraint(columnNames = {"mobile", "dairy_id"})
        }
)
public class FarmerRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="farmerCode")
    private  String code;
    private String firstName;
    private String lastName;
    @Pattern(regexp = "\\d{10}", message = "Mobile number must be exactly 10 digits")
    @NotNull
    private String mobile;

    private String milkType;

    private String village;

    private String district;
    @Column(name = "create_date")
    private LocalDateTime createDate;
    @Column(name = "is_active", nullable = false)
    private boolean isActive=true;
    @Column(name = "is_delete")
    private boolean isDelete;
    @UpdateTimestamp
    @Column(name = "last_update",nullable = false)
    private LocalDateTime lastUpdate;
    @ManyToOne
    @JoinColumn(name = "dairy_id", nullable = false)
    @JsonBackReference
    private DairyRegistration dairyRegistration;


    public String getMilkType() {
        return milkType;
    }

    public void setMilkType(String milkType) {
        this.milkType = milkType;
    }
    public DairyRegistration getDairyRegistration() {
        return dairyRegistration;
    }
    public void setDairyRegistration(DairyRegistration dairyRegistration) {
        this.dairyRegistration = dairyRegistration;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
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
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public LocalDateTime getCreateDate() {
        return createDate;
    }
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }
    public boolean isDelete() {
        return isDelete;
    }
    public void setDelete(boolean delete) {
        isDelete = delete;
    }
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }


//    public List<Integer> getMilkType() {
//        if (milkType == null || milkType.isEmpty()) {
//            return new ArrayList<>();
//        }
//        return Arrays.stream(milkType.split(","))
//                .map(Integer::parseInt)
//                .collect(Collectors.toList());
//    }
//
//    public void setMilkType(List<Integer> milkTypeList) {
//        if (milkTypeList == null || milkTypeList.isEmpty()) {
//            this.milkType = "";
//        } else {
//            this.milkType = milkTypeList.stream()
//                    .map(String::valueOf)
//                    .collect(Collectors.joining(","));
//        }
//    }

}
