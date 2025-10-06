package com.expenses.valueobject;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BudgetID implements Serializable {
    @Embedded
    private MonthlyRecordID monthlyRecordID;

    @Enumerated(EnumType.STRING)
    private BudgetCategory category;

    public BudgetID() {}

    public BudgetID(MonthlyRecordID monthlyRecordID, BudgetCategory category) {
        this.monthlyRecordID = monthlyRecordID;
        this.category = category;
    }

    public void setMonthlyRecordID(MonthlyRecordID monthlyRecordID) {
        this.monthlyRecordID = monthlyRecordID;
    }

    public void setCategory(BudgetCategory category) {
        this.category = category;
    }

    public MonthlyRecordID getMonthlyRecordID() {
        return this.monthlyRecordID;
    }

    public BudgetCategory getCategory() {
        return this.category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetID budgetID = (BudgetID) o;
        return Objects.equals(monthlyRecordID, budgetID.monthlyRecordID) &&
               category == budgetID.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(monthlyRecordID, category);
    }
}
