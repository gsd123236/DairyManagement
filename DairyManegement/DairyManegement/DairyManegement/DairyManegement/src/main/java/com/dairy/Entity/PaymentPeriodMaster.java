package com.dairy.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Entity
@Table(name="payment_period")
public class PaymentPeriodMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int id;
    @Column(name="payment_period")
    private String paymentPeriodName;
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

    public boolean isDeactive(boolean b) {
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

    public String getPaymentPeriodName() {
        return paymentPeriodName;
    }

    public void setPaymentPeriodName(String paymentPeriodName) {
        this.paymentPeriodName = paymentPeriodName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
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
