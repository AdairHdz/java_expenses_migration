package com.expenses.dto;

import com.expenses.valueobject.ExpenseStatus;

public class UpdateExpenseStatusDTO {
    private ExpenseStatus status;
    private Integer amount;

    public UpdateExpenseStatusDTO() {
    }

    public UpdateExpenseStatusDTO(ExpenseStatus status, Integer amount) {
        this.status = status;
        this.amount = amount;
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
