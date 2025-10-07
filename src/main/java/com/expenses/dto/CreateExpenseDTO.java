package com.expenses.dto;

import com.expenses.valueobject.BudgetCategory;
import com.expenses.valueobject.ExpenseStatus;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class CreateExpenseDTO {
    @NotNull(message = "Month is required")
    @Min(value = 1, message = "Month must be between 1 and 12")
    @Max(value = 12, message = "Month must be between 1 and 12")
    private Integer month;

    @NotNull(message = "Year is required")
    @Min(value = 2000, message = "Year must be at least 2000")
    private Integer year;

    @NotNull(message = "category is required")
    private BudgetCategory category;

    @NotNull(message = "concept is required")
    @Length(min = 1, message = "concept is required")
    private String concept;

    @NotNull(message = "amount is required")
    @Min(value = 1, message = "amount must be at least 1")
    private Integer amount;

    @NotNull(message = "status is required")
    private ExpenseStatus status;

    public CreateExpenseDTO() {
    }

    public CreateExpenseDTO(Integer month, Integer year, BudgetCategory category, String concept, Integer amount, ExpenseStatus status) {
        this.month = month;
        this.year = year;
        this.category = category;
        this.concept = concept;
        this.amount = amount;
        this.status = status;
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

    public BudgetCategory getCategory() {
        return category;
    }

    public void setCategory(BudgetCategory category) {
        this.category = category;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public ExpenseStatus getStatus() {
        return status;
    }

    public void setStatus(ExpenseStatus status) {
        this.status = status;
    }
}
