package com.dairy.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class PartyRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private  String lastName;
    private String mobile;
    private String village;
    private String district;
    private boolean active;
    private boolean inactive;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dairy_id", nullable = false)
    @JsonBackReference

    private DairyRegistration dairyRegistration;


    public PartyRegistration() {
    }

    public PartyRegistration(int id, String firstName, String lastName, String mobile, String village, String district, boolean active, boolean inactive, LocalDateTime createDate, LocalDateTime updateDate, DairyRegistration dairyRegistration) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.village = village;
        this.district = district;
        this.active = active;
        this.inactive = inactive;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.dairyRegistration = dairyRegistration;
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
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isInactive() {
        return inactive;
    }

    public void setInactive(boolean inactive) {
        this.inactive = inactive;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
}
