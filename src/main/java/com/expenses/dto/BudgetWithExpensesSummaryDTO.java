package com.expenses.dto;

import com.expenses.valueobject.BudgetCategory;

import java.util.ArrayList;
import java.util.List;

public class BudgetWithExpensesSummaryDTO {
    private BudgetCategory category;
    private double totalMoneyAssigned;
    private double totalMoneyPendingOfPayment;
    private double totalMoneyAlreadyPaid;
    private double remainingMoney;
    private List<ExpenseSummaryDTO> expensesSummaries = new ArrayList<>();

    public BudgetWithExpensesSummaryDTO() {
    }

    public BudgetWithExpensesSummaryDTO(BudgetCategory category, double totalMoneyAssigned, double totalMoneyPendingOfPayment, double totalMoneyAlreadyPaid, double remainingMoney, List<ExpenseSummaryDTO> expensesSummaries) {
        this.category = category;
        this.totalMoneyAssigned = totalMoneyAssigned;
        this.totalMoneyPendingOfPayment = totalMoneyPendingOfPayment;
        this.totalMoneyAlreadyPaid = totalMoneyAlreadyPaid;
        this.remainingMoney = remainingMoney;
        this.expensesSummaries = expensesSummaries;
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

    public double getRemainingMoney() {
        return remainingMoney;
    }

    public void setRemainingMoney(double remainingMoney) {
        this.remainingMoney = remainingMoney;
    }

    public List<ExpenseSummaryDTO> getExpensesSummaries() {
        return expensesSummaries;
    }

    public void setExpensesSummaries(List<ExpenseSummaryDTO> expensesSummaries) {
        this.expensesSummaries = expensesSummaries;
    }

    public void addExpenseSummary(ExpenseSummaryDTO expenseSummaryDTO) {
        this.expensesSummaries.add(expenseSummaryDTO);
    }
}
