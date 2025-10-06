package com.expenses.controller;

import com.expenses.dto.CreateMonthlyRecordDTO;
import com.expenses.dto.MonthlyRecordSummaryDTO;
import com.expenses.entity.MonthlyRecord;
import com.expenses.service.MonthlyRecordService;
import com.expenses.valueobject.Money;
import com.expenses.valueobject.Month;
import com.expenses.valueobject.MonthlyRecordID;
import com.expenses.valueobject.Year;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/monthly-records")
public class MonthlyRecordController {
    private final MonthlyRecordService monthlyRecordService;

    public  MonthlyRecordController(MonthlyRecordService monthlyRecordService) {
        this.monthlyRecordService = monthlyRecordService;
    }

    @PostMapping
    public ResponseEntity<?> store(@Valid @RequestBody CreateMonthlyRecordDTO createMonthlyRecordDTO) {
        MonthlyRecord monthlyRecord = this.monthlyRecordService.createMonthlyRecord(
                new Money(createMonthlyRecordDTO.getInitialBudget()),
                new Month(createMonthlyRecordDTO.getMonth()),
                new Year(createMonthlyRecordDTO.getYear())
        );
        return ResponseEntity.ok("ok");
    }

    @GetMapping("{year}/{month}")
    public ResponseEntity<?> findByMonthlyRecordId(@PathVariable("month") int month, @PathVariable("year") int year) {
        MonthlyRecordID monthlyRecordID = new MonthlyRecordID(new Month(month), new Year(year));
        MonthlyRecordSummaryDTO monthlyRecordSummaryDTO = this.monthlyRecordService.getMonthlyRecordSummary(monthlyRecordID);
        return ResponseEntity.ok(monthlyRecordSummaryDTO);
    }
}
