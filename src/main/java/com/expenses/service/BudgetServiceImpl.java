package com.expenses.service;

import com.expenses.dto.BudgetWithExpensesSummaryDTO;
import com.expenses.dto.CategoryDTO;
import com.expenses.dto.ExpenseStatusDTO;
import com.expenses.dto.ExpenseSummaryDTO;
import com.expenses.entity.Budget;
import com.expenses.entity.Expense;
import com.expenses.entity.MonthlyRecord;
import com.expenses.exception.NotFoundException;
import com.expenses.repository.BudgetRepository;
import com.expenses.repository.ExpenseRepository;
import com.expenses.repository.MonthlyRecordRepository;
import com.expenses.valueobject.BudgetID;
import com.expenses.valueobject.ExpenseStatus;
import com.expenses.valueobject.Money;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository budgetRepository;
    private final MonthlyRecordRepository monthlyRecordRepository;
    private final ExpenseRepository expenseRepository;

    public BudgetServiceImpl(BudgetRepository budgetRepository, MonthlyRecordRepository monthlyRecordRepository, ExpenseRepository expenseRepository) {
        this.budgetRepository = budgetRepository;
        this.monthlyRecordRepository = monthlyRecordRepository;
        this.expenseRepository = expenseRepository;
    }

    @Override
    public Budget createBudget(BudgetID id, Money assignedAmount) {
        Optional<MonthlyRecord> optionalMonthlyRecord = this.monthlyRecordRepository.findById(id.getMonthlyRecordID());
        if(!optionalMonthlyRecord.isPresent()) {
            throw new NotFoundException("Monthly record does not exist");
        }

        Budget budget = new Budget(id, assignedAmount, optionalMonthlyRecord.get());
        return this.budgetRepository.save(budget);
    }

    @Override
    public BudgetWithExpensesSummaryDTO getBudgetWithExpenses(BudgetID budgetId) {
        Optional<Budget> optionalBudget = this.budgetRepository.findById(budgetId);
        if(!optionalBudget.isPresent()) {
            throw new NotFoundException("budget does not exist");
        }

        Budget budget = optionalBudget.get();
        BudgetWithExpensesSummaryDTO budgetWithExpensesSummaryDTO = new BudgetWithExpensesSummaryDTO();
        CategoryDTO categoryDTO = new CategoryDTO(budget.getId().getCategory().ordinal(), budget.getId().getCategory().name());
        budgetWithExpensesSummaryDTO.setCategory(categoryDTO);
        budgetWithExpensesSummaryDTO.setTotalMoneyAssigned(budget.getAssignedAmount().toMXN());

        Money totalMoneyAlreadyPaid = new Money(0);
        Money totalMoneyPendingOfPayment = new Money(0);

        List<Expense> expenses = this.expenseRepository.findByBudgetId(budgetId);
        for(Expense expense : expenses) {

            ExpenseStatusDTO expenseStatusDTO = new ExpenseStatusDTO(expense.getStatus().ordinal(), expense.getStatus().name());
            ExpenseSummaryDTO expenseSummaryDTO = new ExpenseSummaryDTO(expense.getId(), expense.getConcept(), expense.getAmount().toMXN(), expenseStatusDTO);

            if(expense.getStatus() == ExpenseStatus.PAID) {
                totalMoneyAlreadyPaid = totalMoneyAlreadyPaid.add(expense.getAmount());
            } else {
                totalMoneyPendingOfPayment = totalMoneyPendingOfPayment.add(expense.getAmount());
            }

            budgetWithExpensesSummaryDTO.addExpenseSummary(expenseSummaryDTO);
        }

        Money moneyUsed = totalMoneyAlreadyPaid.add(totalMoneyPendingOfPayment);
        Money remainingMoney = budget.getAssignedAmount().subtract(moneyUsed);

        budgetWithExpensesSummaryDTO.setTotalMoneyAlreadyPaid(totalMoneyAlreadyPaid.toMXN());
        budgetWithExpensesSummaryDTO.setTotalMoneyPendingOfPayment(totalMoneyPendingOfPayment.toMXN());
        budgetWithExpensesSummaryDTO.setRemainingMoney(remainingMoney.toMXN());
        return budgetWithExpensesSummaryDTO;

    }

    @Override
    public void deleteById(BudgetID budgetId) {
        this.budgetRepository.deleteById(budgetId);
    }

    @Override
    public void updateBudget(BudgetID budgetID, Money assignedAmount) {
        Optional<Budget> optionalBudget = this.budgetRepository.findById(budgetID);
        if(!optionalBudget.isPresent()) {
            throw new NotFoundException("Budget does not exist");
        }
        Budget budget = optionalBudget.get();
        budget.setAssignedAmount(assignedAmount);
        this.budgetRepository.save(budget);
    }
}
