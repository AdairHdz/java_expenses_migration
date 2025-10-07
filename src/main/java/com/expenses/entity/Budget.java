package com.expenses.entity;

import com.expenses.valueobject.BudgetID;
import com.expenses.valueobject.Money;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "budgets")
public class Budget {
    @EmbeddedId
    private BudgetID id;

    @Column(name = "assigned_amount")
    private int assignedAmount;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "month", referencedColumnName = "month", insertable = false, updatable = false),
        @JoinColumn(name = "year", referencedColumnName = "year", insertable = false, updatable = false)
    })
    private MonthlyRecord monthlyRecord;

    @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Expense> expenses = new ArrayList<>();

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

    public List<Expense> getExpenses() {
        return this.expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }
}
