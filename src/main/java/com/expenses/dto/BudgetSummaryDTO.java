package com.expenses.dto;

import com.expenses.valueobject.BudgetCategory;

public class BudgetSummaryDTO {
    private com.expenses.valueobject.BudgetCategory category;
    private double totalMoneyAssigned;
    private double totalMoneyPendingOfPayment;
    private double totalMoneyAlreadyPaid;

    public BudgetSummaryDTO() {
    }

    public BudgetSummaryDTO(BudgetCategory category, double totalMoneyAssigned, double totalMoneyPendingOfPayment, double totalMoneyAlreadyPaid) {
        this.category = category;
        this.totalMoneyAssigned = totalMoneyAssigned;
        this.totalMoneyPendingOfPayment = totalMoneyPendingOfPayment;
        this.totalMoneyAlreadyPaid = totalMoneyAlreadyPaid;
    }

    public BudgetCategory getCategory() {
        return category;
    }

    public void setCategory(BudgetCategory category) {
        this.category = category;
    }

    public double getTotalMoneyAssigned() {
        return totalMoneyAssigned;
    }

    public void setTotalMoneyAssigned(double totalMoneyAssigned) {
        this.totalMoneyAssigned = totalMoneyAssigned;
    }

    public double getTotalMoneyPendingOfPayment() {
        return totalMoneyPendingOfPayment;
    }

    public void setTotalMoneyPendingOfPayment(double totalMoneyPendingOfPayment) {
        this.totalMoneyPendingOfPayment = totalMoneyPendingOfPayment;
    }

    public double getTotalMoneyAlreadyPaid() {
        return totalMoneyAlreadyPaid;
    }

    public void setTotalMoneyAlreadyPaid(double totalMoneyAlreadyPaid) {
        this.totalMoneyAlreadyPaid = totalMoneyAlreadyPaid;
    }
}
