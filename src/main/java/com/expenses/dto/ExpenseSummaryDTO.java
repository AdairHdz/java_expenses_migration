package com.expenses.dto;


import com.expenses.valueobject.ExpenseStatus;

public class ExpenseSummaryDTO {
    private String concept;
    private double money;
    private ExpenseStatus status;

    public ExpenseSummaryDTO() {
    }

    public ExpenseSummaryDTO(String concept, double money, ExpenseStatus status) {
        this.concept = concept;
        this.money = money;
        this.status = status;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public ExpenseStatus getStatus() {
        return status;
    }

    public void setStatus(ExpenseStatus status) {
        this.status = status;
    }
}
