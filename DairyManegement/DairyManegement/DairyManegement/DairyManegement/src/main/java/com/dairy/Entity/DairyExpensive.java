package com.dairy.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "dairy_expensive")
public class DairyExpensive {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;
    @JsonSerialize(using = ToStringSerializer.class)
    private  int category;
    private  String partyName;
    @JsonSerialize(using = ToStringSerializer.class)
    private  double amount;
    private String comments;
    @JoinColumn(name = "dairy_id", nullable = false)
    @ManyToOne
    @JsonBackReference
    private DairyRegistration dairyRegistration;

    public DairyExpensive() {
    }

    public DairyExpensive(int id, LocalDate date, int category, String partyName, double amount, String comments, DairyRegistration dairyRegistration) {
        this.id = id;
        this.date = date;
        this.category = category;
        this.partyName = partyName;
        this.amount = amount;
        this.comments = comments;
        this.dairyRegistration = dairyRegistration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public DairyRegistration getDairyRegistration() {
        return dairyRegistration;
    }

    public void setDairyRegistration(DairyRegistration dairyRegistration) {
        this.dairyRegistration = dairyRegistration;
    }
}
