package com.expenses.dto;

import com.expenses.valueobject.BudgetCategory;
import com.expenses.valueobject.ExpenseStatus;

public class UpdateExpenseDTO {
    private Integer month;
    private Integer year;
    private BudgetCategory budgetCategory;
    private ExpenseStatus status;
    private String concept;
    private Integer amount;

    public UpdateExpenseDTO() {
    }

    public UpdateExpenseDTO(Integer month, Integer year, BudgetCategory budgetCategory, ExpenseStatus status, String concept, Integer amount) {
        this.month = month;
        this.year = year;
        this.budgetCategory = budgetCategory;
        this.status = status;
        this.concept = concept;
        this.amount = amount;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public BudgetCategory getBudgetCategory() {
        return budgetCategory;
    }

    public void setBudgetCategory(BudgetCategory budgetCategory) {
        this.budgetCategory = budgetCategory;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public ExpenseStatus getStatus() {
        return status;
    }

    public void setStatus(ExpenseStatus status) {
        this.status = status;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
