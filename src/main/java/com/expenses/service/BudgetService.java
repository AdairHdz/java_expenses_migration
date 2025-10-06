package com.expenses.service;

import com.expenses.dto.BudgetWithExpensesSummaryDTO;
import com.expenses.entity.Budget;
import com.expenses.valueobject.BudgetID;
import com.expenses.valueobject.Money;


public interface BudgetService {
    Budget createBudget(BudgetID id, Money assignedAmount);
    BudgetWithExpensesSummaryDTO getBudgetWithExpenses(BudgetID budgetId);
}
