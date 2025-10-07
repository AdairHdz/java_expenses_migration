package com.expenses.controller;

import com.expenses.dto.BudgetWithExpensesSummaryDTO;
import com.expenses.dto.CreateBudgetDTO;
import com.expenses.dto.HttpResponseDTO;
import com.expenses.dto.UpdateBudgetDTO;
import com.expenses.entity.Budget;
import com.expenses.entity.MonthlyRecord;
import com.expenses.service.BudgetService;
import com.expenses.valueobject.*;
import org.springframework.http.HttpStatus;
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
        HttpResponseDTO<Budget> httpResponseDTO = new HttpResponseDTO<>("Budget created successfully", budget);
        return new ResponseEntity<>(httpResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("{year}/{month}/{category}")
    public ResponseEntity<?> findByMonthAndYearAndCategory(
            @PathVariable("month") @Min(value = 1, message = "Month must be between 1 and 12") @Max(value = 12, message = "Month must be between 1 and 12") int month,
            @PathVariable("year") int year,
            @PathVariable("category") BudgetCategory category
    ) {
        MonthlyRecordID monthlyRecordID = new MonthlyRecordID(
                new Month(month),
                new Year(year)
        );

        BudgetID budgetID = new BudgetID(monthlyRecordID, category);

        BudgetWithExpensesSummaryDTO budgetWithExpensesSummaryDTO =  this.budgetService.getBudgetWithExpenses(budgetID);
        HttpResponseDTO<BudgetWithExpensesSummaryDTO> budgetWithExpensesSummaryDTOHttpResponseDTO = new HttpResponseDTO<>("Budget with expenses summary fetched correctly", budgetWithExpensesSummaryDTO);
        return new ResponseEntity<>(budgetWithExpensesSummaryDTOHttpResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("{year}/{month}/{category}")
    public ResponseEntity<?> delete(
            @PathVariable("month") @Min(value = 1, message = "Month must be between 1 and 12") @Max(value = 12, message = "Month must be between 1 and 12") int month,
            @PathVariable("year") @Min(value = 2000, message = "Year must be grater than or equal to 2000") @Max(value = 2030, message = "Year must be less than or equal to 2030") int year,
            @PathVariable("category") BudgetCategory category
    ) {
        MonthlyRecordID monthlyRecordID = new MonthlyRecordID(new Month(month), new Year(year));
        BudgetID budgetId = new BudgetID(monthlyRecordID, category);
        this.budgetService.deleteById(budgetId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("{year}/{month}/{category}")
    public ResponseEntity<?> update(
            @PathVariable("month") @Min(value = 1, message = "Month must be between 1 and 12") @Max(value = 12, message = "Month must be between 1 and 12") int month,
            @PathVariable("year") @Min(value = 2000, message = "Year must be grater than or equal to 2000") @Max(value = 2030, message = "Year must be less than or equal to 2030") int year,
            @PathVariable("category") BudgetCategory category,
            @Valid @RequestBody UpdateBudgetDTO updateBudgetDTO
    ) {
        MonthlyRecordID monthlyRecordID = new MonthlyRecordID(new Month(month), new Year(year));
        BudgetID budgetID = new BudgetID(monthlyRecordID, category);
        Money assignedAmount = new Money(updateBudgetDTO.getAssignedAmount());
        this.budgetService.updateBudget(budgetID, assignedAmount);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
