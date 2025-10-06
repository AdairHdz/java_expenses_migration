package com.expenses.service;

import com.expenses.entity.Budget;
import com.expenses.entity.Expense;
import com.expenses.exception.NotFoundException;
import com.expenses.repository.BudgetRepository;
import com.expenses.repository.ExpenseRepository;
import com.expenses.valueobject.BudgetID;
import com.expenses.valueobject.ExpenseStatus;
import com.expenses.valueobject.Money;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final BudgetRepository budgetRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, BudgetRepository budgetRepository) {
        this.expenseRepository = expenseRepository;
        this.budgetRepository = budgetRepository;
    }

    @Override
    public Expense addExpenseToBudget(BudgetID budgetID, String concept, ExpenseStatus status, Money amount) {
        Optional<Budget> budget = this.budgetRepository.findById(budgetID);
        if(!budget.isPresent()) {
            throw new NotFoundException("budget does not exist");
        }

        Expense newExpense = new Expense(concept, amount, status, budgetID);
        return this.expenseRepository.save(newExpense);
    }

    @Override
    public void updateExpense(Long expenseID, ExpenseStatus status, Money amount) {
        Optional<Expense> optionalExpense = this.expenseRepository.findById(expenseID);
        if(!optionalExpense.isPresent()) {
            throw new NotFoundException("expense does not exist");
        }
        Expense expense = optionalExpense.get();
        expense.changeStatus(status);
        expense.setAmount(amount);
        this.expenseRepository.save(expense);
    }
}
