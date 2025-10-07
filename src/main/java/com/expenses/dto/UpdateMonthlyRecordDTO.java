package com.expenses.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class UpdateMonthlyRecordDTO {
    @NotNull(message = "initialBudget is required")
    @Min(value = 1, message = "initialBudget must be at least 1")
    private Integer initialBudget;

    public void setInitialBudget(Integer initialBudget) {
        this.initialBudget = initialBudget;
    }

    public Integer getInitialBudget() {
        return this.initialBudget;
    }
}
