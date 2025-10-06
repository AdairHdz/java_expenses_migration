package com.expenses.repository;

import com.expenses.entity.Expense;
import com.expenses.valueobject.BudgetID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByBudgetId(BudgetID budgetId);
}
