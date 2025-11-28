package com.dairy.Entity;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="milktype")
public class MilkTypeMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="animal_name")
    private String animalName;
    @Column(name = "create_date")
    private LocalDateTime createDate;
    @Column(name = "is_active", nullable = false)
    private boolean isActive=true;
    @Column(name = "is_delete")
    private boolean isDelete;
    @Column(name="is_Deactive")
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

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



}
