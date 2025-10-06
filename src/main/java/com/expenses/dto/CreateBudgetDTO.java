package com.expenses.dto;

import com.expenses.valueobject.BudgetCategory;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CreateBudgetDTO {
    @NotNull(message = "Month is required")
    @Min(value = 1, message = "Month must be between 1 and 12")
    @Max(value = 12, message = "Month must be between 1 and 12")
    private Integer month;

    @NotNull(message = "Year is required")
    @Min(value = 2020, message = "Year must be at least 2020")
    private Integer year;

    @NotNull(message = "assignedAmount is required")
    @Min(value = 1, message = "assignedAmount must be at least 1")
    private Integer assignedAmount;

    @NotNull(message = "category is required")
    private BudgetCategory category;

    public CreateBudgetDTO() {
    }

    public CreateBudgetDTO(Integer month, Integer year, Integer assignedAmount, BudgetCategory category) {
        this.month = month;
        this.year = year;
        this.assignedAmount = assignedAmount;
        this.category = category;
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

    public Integer getAssignedAmount() {
        return assignedAmount;
    }

    public void setAssignedAmount(Integer assignedAmount) {
        this.assignedAmount = assignedAmount;
    }

    public BudgetCategory getCategory() {
        return category;
    }

    public void setCategory(BudgetCategory category) {
        this.category = category;
    }
}
