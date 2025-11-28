package com.dairy.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class CowMilkRateMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String rateType;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private boolean isActive;
    private boolean isDelete;
    private boolean isDeactive;

    public boolean isDeactive(boolean b) {
        return isDeactive;
    }

    public void setDeactive(boolean deactive) {
        isDeactive = deactive;
    }

    public CowMilkRateMaster() {
    }

    public CowMilkRateMaster(int id, String rateType, LocalDateTime createDate, LocalDateTime updateDate, boolean isActive, boolean isDelete) {
        this.id = id;
        this.rateType = rateType;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.isActive = isActive;
        this.isDelete = isDelete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
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
}
