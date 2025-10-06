package com.expenses.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CreateMonthlyRecordDTO {
    @NotNull(message = "Month is required")
    @Min(value = 1, message = "Month must be between 1 and 12")
    @Max(value = 12, message = "Month must be between 1 and 12")
    private Integer month;

    @NotNull(message = "Year is required")
    @Min(value = 2020, message = "Year must be at least 2020")
    private Integer year;

    @NotNull(message = "initialBudget is required")
    @Min(value = 1, message = "initialBudget must be at least 1")
    private int initialBudget;

    public CreateMonthlyRecordDTO() {
    }

    public CreateMonthlyRecordDTO(Integer month, Integer year, int initialBudget) {
        this.month = month;
        this.year = year;
        this.initialBudget = initialBudget;
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

    public int getInitialBudget() {
        return initialBudget;
    }

    public void setInitialBudget(int initialBudget) {
        this.initialBudget = initialBudget;
    }

    @Override
    public String toString() {
        return "CreateMonthlyRecordDTO{" +
                "month=" + month +
                ", year=" + year +
                ", initialBudget=" + initialBudget +
                '}';
    }
}
