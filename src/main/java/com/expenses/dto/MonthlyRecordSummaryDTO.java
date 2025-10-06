package com.expenses.dto;

import java.util.ArrayList;
import java.util.List;

public class MonthlyRecordSummaryDTO {
    private int month;
    private int year;
    private double initialBudget;
    private double totalMoneyAlreadyPaid;
    private double totalMoneyPendingOfPayment;
    private List<BudgetSummaryDTO> budgetSummaries = new ArrayList<>();

    public MonthlyRecordSummaryDTO() {
    }

    public MonthlyRecordSummaryDTO(int month, int year, double initialBudget, double totalMoneyAlreadyPaid, double totalMoneyPendingOfPayment, List<BudgetSummaryDTO> budgetSummaries) {
        this.month = month;
        this.year = year;
        this.initialBudget = initialBudget;
        this.totalMoneyAlreadyPaid = totalMoneyAlreadyPaid;
        this.totalMoneyPendingOfPayment = totalMoneyPendingOfPayment;
        this.budgetSummaries = budgetSummaries;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getInitialBudget() {
        return initialBudget;
    }

    public void setInitialBudget(double initialBudget) {
        this.initialBudget = initialBudget;
    }

    public double getTotalMoneyAlreadyPaid() {
        return totalMoneyAlreadyPaid;
    }

    public void setTotalMoneyAlreadyPaid(double totalMoneyAlreadyPaid) {
        this.totalMoneyAlreadyPaid = totalMoneyAlreadyPaid;
    }

    public double getTotalMoneyPendingOfPayment() {
        return totalMoneyPendingOfPayment;
    }

    public void setTotalMoneyPendingOfPayment(double totalMoneyPendingOfPayment) {
        this.totalMoneyPendingOfPayment = totalMoneyPendingOfPayment;
    }

    public List<BudgetSummaryDTO> getBudgetSummaries() {
        return budgetSummaries;
    }

    public void setBudgetSummaries(List<BudgetSummaryDTO> budgetSummaries) {
        this.budgetSummaries = budgetSummaries;
    }

    public void addBudgetSummary(BudgetSummaryDTO budgetSummaryDTO) {
        this.budgetSummaries.add(budgetSummaryDTO);
    }
}
