package com.expenses.dto;

public class ExpenseSummaryDTO {
    private Long id;
    private String concept;
    private double amount;
    private ExpenseStatusDTO status;

    public ExpenseSummaryDTO() {
    }

    public ExpenseSummaryDTO(String concept, double money, ExpenseStatusDTO status) {
        this.concept = concept;
        this.amount = money;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public ExpenseStatusDTO getStatus() {
        return status;
    }

    public void setStatus(ExpenseStatusDTO status) {
        this.status = status;
    }
}
