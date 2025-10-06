package com.expenses.controller;

import com.expenses.dto.BudgetWithExpensesSummaryDTO;
import com.expenses.dto.CreateBudgetDTO;
import com.expenses.entity.Budget;
import com.expenses.entity.MonthlyRecord;
import com.expenses.service.BudgetService;
import com.expenses.valueobject.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/api/v1/budgets")
@Validated
public class BudgetController {

    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @PostMapping
    public ResponseEntity<?> store(@Valid @RequestBody CreateBudgetDTO createBudgetDTO) {
        MonthlyRecordID monthlyRecordID = new MonthlyRecordID(
                new Month(createBudgetDTO.getMonth()),
                new Year(createBudgetDTO.getYear())
        );

        BudgetID budgetID = new BudgetID(monthlyRecordID, createBudgetDTO.getCategory());

        Budget budget = this.budgetService.createBudget(budgetID, new Money(createBudgetDTO.getAssignedAmount()));
        return ResponseEntity.ok("OK");
    }

    @GetMapping
    public ResponseEntity<?> findByMonthAndYearAndCategory(
            @RequestParam("month") @Min(value = 1, message = "Month must be between 1 and 12") @Max(value = 12, message = "Month must be between 1 and 12") int month,
            @RequestParam("year") int year,
            @RequestParam("category") BudgetCategory category
    ) {
        MonthlyRecordID monthlyRecordID = new MonthlyRecordID(
                new Month(month),
                new Year(year)
        );

        BudgetID budgetID = new BudgetID(monthlyRecordID, category);

        BudgetWithExpensesSummaryDTO budgetWithExpensesSummaryDTO =  this.budgetService.getBudgetWithExpenses(budgetID);
        return ResponseEntity.ok(budgetWithExpensesSummaryDTO);
    }
}
