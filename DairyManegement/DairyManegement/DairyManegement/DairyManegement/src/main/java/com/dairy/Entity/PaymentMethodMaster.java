package com.dairy.Entity;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
@Entity
@Table(name="payment_Type")
public class PaymentMethodMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="payment_method")
    private String paymentMethod;
    @Column(name = "create_date")
    private LocalDateTime createDate;
    @Column(name = "is_active", nullable = false)
    private boolean isActive=true;
    @Column(name = "is_delete")
    private boolean isDelete;
    @Column(name="is_deactive")
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

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
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
