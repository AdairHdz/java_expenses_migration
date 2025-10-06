package com.expenses.entity;

import com.expenses.valueobject.BudgetID;
import com.expenses.valueobject.Money;

import javax.persistence.*;

@Entity
@Table(name = "budgets")
public class Budget {
    @EmbeddedId
    private BudgetID id;

    @Column(name = "assigned_amount")
    private int assignedAmount;

    @ManyToOne
    @MapsId("monthlyRecordID")
    private MonthlyRecord monthlyRecord;

    public Budget() {}

    public Budget(BudgetID id, Money assignedAmount, MonthlyRecord monthlyRecord) {
        this.id = id;
        this.assignedAmount = assignedAmount.toMXN() != 0 ? (int)(assignedAmount.toMXN() * 100) : 0;
        this.monthlyRecord = monthlyRecord;
    }

    public void setId(BudgetID id) {
        this.id = id;
    }

    public void setAssignedAmount(Money assignedAmount) {
        this.assignedAmount = assignedAmount.toMXN() != 0 ? (int)(assignedAmount.toMXN() * 100) : 0;
    }

    public BudgetID getId() {
        return this.id;
    }

    public Money getAssignedAmount() {
        return new Money(this.assignedAmount);
    }
}
