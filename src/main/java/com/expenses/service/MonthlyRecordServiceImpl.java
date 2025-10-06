package com.expenses.service;

import com.expenses.dto.BudgetSummaryDTO;
import com.expenses.dto.MonthlyRecordSummaryDTO;
import com.expenses.entity.Budget;
import com.expenses.entity.Expense;
import com.expenses.entity.MonthlyRecord;
import com.expenses.exception.NotFoundException;
import com.expenses.repository.BudgetRepository;
import com.expenses.repository.ExpenseRepository;
import com.expenses.repository.MonthlyRecordRepository;
import com.expenses.valueobject.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MonthlyRecordServiceImpl implements MonthlyRecordService {

    private final MonthlyRecordRepository monthlyRecordRepository;
    private final BudgetRepository budgetRepository;
    private final ExpenseRepository expenseRepository;

    public MonthlyRecordServiceImpl(MonthlyRecordRepository monthlyRecordRepository, BudgetRepository budgetRepository, ExpenseRepository expenseRepository) {
        this.monthlyRecordRepository = monthlyRecordRepository;
        this.budgetRepository = budgetRepository;
        this.expenseRepository = expenseRepository;
    }

    @Override
    public MonthlyRecord createMonthlyRecord(Money initialBudget, Month month, Year year) {
        MonthlyRecordID monthlyRecordID = new MonthlyRecordID(month, year);
        MonthlyRecord monthlyRecord = new MonthlyRecord(monthlyRecordID, initialBudget);
        return this.monthlyRecordRepository.save(monthlyRecord);
    }

    @Override
    public MonthlyRecordSummaryDTO getMonthlyRecordSummary(MonthlyRecordID monthlyRecordId) {

        Optional<MonthlyRecord> fetchedMonthlyRecord = this.monthlyRecordRepository.findById(monthlyRecordId);
        if(!fetchedMonthlyRecord.isPresent()) {
            throw new NotFoundException("Monthly record does not exist");
        }
        MonthlyRecordSummaryDTO monthlyRecordSummaryDTO = new MonthlyRecordSummaryDTO();
        MonthlyRecord monthlyRecord = fetchedMonthlyRecord.get();
        monthlyRecordSummaryDTO.setMonth(monthlyRecord.getID().getMonth().get());
        monthlyRecordSummaryDTO.setYear(monthlyRecord.getID().getYear().get());
        monthlyRecordSummaryDTO.setInitialBudget(monthlyRecord.getInitialBudget().toMXN());

        Money totalMoneyAlreadyPaidForMonthlyRecord = new Money(0);
        Money totalMoneyPendingOfPaymentForMonthlyRecord = new Money(0);

        List<Budget> budgetsForMonthlyRecord = this.budgetRepository.findByMonthlyRecordId(monthlyRecordId);
        for(Budget budget : budgetsForMonthlyRecord) {
            Money totalMoneyAlreadyPaidPerBudget = new Money(0);
            Money totalMoneyPendingOfPaymentPerBudget = new Money(0);

            List<Expense> expensesForBudget = this.expenseRepository.findByBudgetId(budget.getId());
            for (Expense expense : expensesForBudget) {
                if (expense.getStatus() == ExpenseStatus.PAID) {
                    totalMoneyAlreadyPaidPerBudget = totalMoneyAlreadyPaidPerBudget.add(expense.getAmount());
                } else {
                    totalMoneyPendingOfPaymentPerBudget = totalMoneyPendingOfPaymentPerBudget.add(expense.getAmount());
                }
            }

            BudgetSummaryDTO budgetSummaryDTO = new BudgetSummaryDTO();
            budgetSummaryDTO.setCategory(budget.getId().getCategory());
            budgetSummaryDTO.setTotalMoneyAssigned(budget.getAssignedAmount().toMXN());
            budgetSummaryDTO.setTotalMoneyAlreadyPaid(totalMoneyAlreadyPaidPerBudget.toMXN());
            budgetSummaryDTO.setTotalMoneyPendingOfPayment(totalMoneyPendingOfPaymentPerBudget.toMXN());
            monthlyRecordSummaryDTO.addBudgetSummary(budgetSummaryDTO);

            totalMoneyAlreadyPaidForMonthlyRecord = totalMoneyAlreadyPaidForMonthlyRecord.add(totalMoneyAlreadyPaidPerBudget);
            totalMoneyPendingOfPaymentForMonthlyRecord = totalMoneyPendingOfPaymentForMonthlyRecord.add(totalMoneyPendingOfPaymentPerBudget);
        }

        monthlyRecordSummaryDTO.setTotalMoneyAlreadyPaid(totalMoneyAlreadyPaidForMonthlyRecord.toMXN());
        monthlyRecordSummaryDTO.setTotalMoneyPendingOfPayment(totalMoneyPendingOfPaymentForMonthlyRecord.toMXN());

        return monthlyRecordSummaryDTO;
    }
}
