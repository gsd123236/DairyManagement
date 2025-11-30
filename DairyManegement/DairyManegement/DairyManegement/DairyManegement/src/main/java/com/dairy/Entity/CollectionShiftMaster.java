package com.dairy.Entity;


import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="collection_shift")
public class CollectionShiftMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true,name="shift_name")
    private  String shift;
    @Column(name = "create_date")
    private LocalDateTime createDate;
    @Column(name = "is_active", nullable = false)
    private boolean isActive=true;
    @Column(name = "is_delete")
    private boolean isDelete;
    @Column(name = "is_deactive")
    private boolean isDeactive;
    @UpdateTimestamp
    @Column(name = "last_update",nullable = false)
    private LocalDateTime lastUpdate;

    public boolean isDeactive() {
        return isDeactive;
    }

    public void setDeactive(boolean deactive) {
        isDeactive = deactive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
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
}
