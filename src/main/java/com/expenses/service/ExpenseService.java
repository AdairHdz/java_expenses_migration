package com.expenses.service;

import com.expenses.entity.Expense;
import com.expenses.valueobject.BudgetID;
import com.expenses.valueobject.ExpenseStatus;
import com.expenses.valueobject.Money;

public interface ExpenseService {
    Expense addExpenseToBudget(BudgetID budgetID, String concept, ExpenseStatus status, Money amount);
    void updateExpense(Long expenseID, ExpenseStatus status, Money amount);
}
