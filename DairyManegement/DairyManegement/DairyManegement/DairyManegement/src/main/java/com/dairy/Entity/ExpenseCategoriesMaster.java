package com.dairy.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class ExpenseCategoriesMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String Expense;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private boolean isActive=true;
    private boolean isDeactive;
    private boolean isDeleted;

    public ExpenseCategoriesMaster() {
    }

    public ExpenseCategoriesMaster(int id, String expense, LocalDateTime createDate, LocalDateTime updateDate, boolean isActive, boolean isDeactive) {
        this.id = id;
        Expense = expense;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.isActive = isActive;
        this.isDeactive = isDeactive;
    }

    public boolean isDeleted(boolean b) {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExpense() {
        return Expense;
    }

    public void setExpense(String expense) {
        Expense = expense;
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

    public boolean isDeactive() {
        return isDeactive;
    }

    public void setDeactive(boolean deactive) {
        isDeactive = deactive;
    }
}
