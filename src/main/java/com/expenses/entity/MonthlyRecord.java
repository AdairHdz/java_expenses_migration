package com.expenses.entity;

import com.expenses.valueobject.Money;
import com.expenses.valueobject.MonthlyRecordID;

import javax.persistence.*;

@Entity
@Table(name = "monthly_records")
public class MonthlyRecord {
    @EmbeddedId
    private MonthlyRecordID id;

    @Column(name = "initial_budget")
    private int initialBudget;

    public MonthlyRecord() {}

    public MonthlyRecord(MonthlyRecordID id, Money initialBudget) {
        this.id = id;
        this.initialBudget = initialBudget.toMXN() != 0 ? (int)(initialBudget.toMXN() * 100) : 0;
    }

    public void setID(MonthlyRecordID id) {
        this.id = id;
    }

    public void setInitialBudget(Money initialBudget) {
        this.initialBudget = initialBudget.toMXN() != 0 ? (int)(initialBudget.toMXN() * 100) : 0;
    }

    public MonthlyRecordID getID() {
        return this.id;
    }

    public Money getInitialBudget() {
        return new Money(this.initialBudget);
    }
}
