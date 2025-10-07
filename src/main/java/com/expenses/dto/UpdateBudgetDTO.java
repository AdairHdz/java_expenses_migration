package com.expenses.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class UpdateBudgetDTO {
    @NotNull(message = "assignedAmount is required")
    @Min(value = 1, message = "assignedAmount must be at least 1")
    private Integer assignedAmount;

    public void setAssignedAmount(Integer assignedAmount) {
        this.assignedAmount = assignedAmount;
    }

    public Integer getAssignedAmount() {
        return this.assignedAmount;
    }
}
