package com.dairy.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
public class DairyNotice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String newNotice;
    private  String details;
    private boolean isActive=true;
    private boolean isDelete;
    private LocalDateTime createdDate;
    @UpdateTimestamp
    private LocalDateTime updateDate;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "dId", nullable = false)
    private DairyRegistration dairyRegistration;

    public DairyNotice() {
    }

    public DairyNotice(int id, String newNotice, String details, boolean isActive, boolean isDelete,
                       LocalDateTime createdDate, LocalDateTime updateDate) {
        this.id = id;
        this.newNotice = newNotice;
        this.details = details;
        this.isActive = isActive;
        this.isDelete = isDelete;
        this.createdDate = createdDate;
        this.updateDate = updateDate;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNewNotice() {
        return newNotice;
    }

    public void setNewNotice(String newNotice) {
        this.newNotice = newNotice;
    }

    public DairyRegistration getDairyRegistration() {
        return dairyRegistration;
    }

    public void setDairyRegistration(DairyRegistration dairyRegistration) {
        this.dairyRegistration = dairyRegistration;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
}
