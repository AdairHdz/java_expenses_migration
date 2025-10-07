package com.expenses.controller;

import com.expenses.dto.CreateExpenseDTO;
import com.expenses.dto.HttpResponseDTO;
import com.expenses.dto.UpdateExpenseDTO;
import com.expenses.entity.Expense;
import com.expenses.service.ExpenseService;
import com.expenses.valueobject.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<?> store(@Valid @RequestBody CreateExpenseDTO createExpenseDTO) {
        MonthlyRecordID monthlyRecordID = new MonthlyRecordID(
                new Month(createExpenseDTO.getMonth()),
                new Year(createExpenseDTO.getYear())
        );
        BudgetID budgetID = new BudgetID(monthlyRecordID, createExpenseDTO.getCategory());

        Expense expense = this.expenseService.addExpenseToBudget(
                budgetID,
                createExpenseDTO.getConcept(),
                createExpenseDTO.getStatus(),
                new Money(createExpenseDTO.getAmount())
        );

        HttpResponseDTO<Expense> expenseHttpResponseDTO = new HttpResponseDTO<>("Expense created successfully", expense);
        return new ResponseEntity<>(expenseHttpResponseDTO, HttpStatus.CREATED);
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> patch(@PathVariable("id") Long id, @RequestBody UpdateExpenseDTO updateExpenseStatusDTO) {
        this.expenseService.updateExpense(id,updateExpenseStatusDTO.getStatus(), new Money(updateExpenseStatusDTO.getAmount()));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        this.expenseService.deleteExpense(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
