package com.expenses.controller;

import com.expenses.dto.CreateMonthlyRecordDTO;
import com.expenses.dto.HttpResponseDTO;
import com.expenses.dto.MonthlyRecordSummaryDTO;
import com.expenses.dto.UpdateMonthlyRecordDTO;
import com.expenses.entity.MonthlyRecord;
import com.expenses.service.MonthlyRecordService;
import com.expenses.valueobject.Money;
import com.expenses.valueobject.Month;
import com.expenses.valueobject.MonthlyRecordID;
import com.expenses.valueobject.Year;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

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
        HttpResponseDTO<MonthlyRecord> monthlyRecordHttpResponseDTO = new HttpResponseDTO<>("Monthly record created successfully", monthlyRecord);
        return new ResponseEntity<>(monthlyRecordHttpResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("{year}/{month}")
    public ResponseEntity<?> findByMonthlyRecordId(
            @PathVariable("month") @Min(value = 1, message = "Month must be between 1 and 12") @Max(value = 12, message = "Month must be between 1 and 12") int month,
            @PathVariable("year") @Min(value = 2000, message = "Year must be grater than or equal to 2000") @Max(value = 2030, message = "Year must be less than or equal to 2030") int year
    ) {
        MonthlyRecordID monthlyRecordID = new MonthlyRecordID(new Month(month), new Year(year));
        MonthlyRecordSummaryDTO monthlyRecordSummaryDTO = this.monthlyRecordService.getMonthlyRecordSummary(monthlyRecordID);
        HttpResponseDTO<MonthlyRecordSummaryDTO> monthlyRecordSummaryDTOHttpResponseDTO = new HttpResponseDTO<>("Monthly record summary fetched correctly", monthlyRecordSummaryDTO);
        return new ResponseEntity<>(monthlyRecordSummaryDTOHttpResponseDTO, HttpStatus.OK);
    }

    @PatchMapping("{year}/{month}")
    public ResponseEntity<?> updateMonthlyRecord(
            @PathVariable("month") @Min(value = 1, message = "Month must be between 1 and 12") @Max(value = 12, message = "Month must be between 1 and 12") int month,
            @PathVariable("year") @Min(value = 2000, message = "Year must be grater than or equal to 2000") @Max(value = 2030, message = "Year must be less than or equal to 2030") int year,
            @Valid @RequestBody UpdateMonthlyRecordDTO updateMonthlyRecordDTO
            ) {
        MonthlyRecordID monthlyRecordID = new MonthlyRecordID(new Month(month), new Year(year));
        Money initialBudget = new Money(updateMonthlyRecordDTO.getInitialBudget());
        this.monthlyRecordService.updateMonthlyRecord(monthlyRecordID, initialBudget);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
