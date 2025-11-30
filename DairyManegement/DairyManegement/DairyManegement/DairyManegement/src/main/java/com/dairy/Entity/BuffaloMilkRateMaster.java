package com.dairy.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class BuffaloMilkRateMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private  String rateType;
    private  boolean isActive=true;
    private  boolean isDelete;
    private boolean isDeactive;
    private LocalDateTime createDate;
    private  LocalDateTime updateDate;

    public BuffaloMilkRateMaster(int id, String rateType, boolean isActive, boolean isDelete, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.rateType = rateType;
        this.isActive = isActive;
        this.isDelete = isDelete;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public boolean isDeactive() {
        return isDeactive;
    }

    public void setDeactive(boolean deactive) {
        isDeactive = deactive;
    }

    public BuffaloMilkRateMaster() {
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
