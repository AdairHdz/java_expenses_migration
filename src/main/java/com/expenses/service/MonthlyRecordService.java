package com.expenses.service;

import com.expenses.dto.MonthlyRecordSummaryDTO;
import com.expenses.entity.MonthlyRecord;
import com.expenses.valueobject.Money;
import com.expenses.valueobject.Month;
import com.expenses.valueobject.MonthlyRecordID;
import com.expenses.valueobject.Year;

public interface MonthlyRecordService {
    MonthlyRecord createMonthlyRecord(Money initialBudget, Month month, Year year);
    MonthlyRecordSummaryDTO getMonthlyRecordSummary(MonthlyRecordID monthlyRecordId);
    void updateMonthlyRecord(MonthlyRecordID id, Money initialBudget);
}
