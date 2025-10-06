package com.expenses.repository;

import com.expenses.entity.Budget;
import com.expenses.valueobject.BudgetID;
import com.expenses.valueobject.MonthlyRecordID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, BudgetID> {
    List<Budget> findByMonthlyRecordId(MonthlyRecordID monthlyRecordId);
}
