package com.dairy.Dto;

import com.dairy.Entity.DairyExpensive;

import java.time.LocalDate;

public class DairyExpensiveDTO {
    private  int id;
    private LocalDate date;
    private  String category;
    private  String partyName;
    private  double amount;
    private String comments;

    public DairyExpensiveDTO(DairyExpensive dairy, String category) {
        this.id = dairy.getId();
        this.date=dairy.getDate();
        this.category = category;
        this.partyName = dairy.getPartyName();
        this.amount = dairy.getAmount();
        this.comments = dairy.getComments();

    }

//    public DairyExpensiveDto(DairyExpensive dairy, Optional<ExpenseCategoriesMaster> byId) {
//        this.id = dairy.getId();
//        this.date=dairy.getDate();
//        this.category = category;
//        this.partyName = dairy.getPartyName();
//        this.amount = dairy.getAmount();
//        this.comments = dairy.getComments();
//
//    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
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
}
