package com.expenses.repository;

import com.expenses.entity.MonthlyRecord;
import com.expenses.valueobject.MonthlyRecordID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthlyRecordRepository extends JpaRepository<MonthlyRecord, MonthlyRecordID> {
}
