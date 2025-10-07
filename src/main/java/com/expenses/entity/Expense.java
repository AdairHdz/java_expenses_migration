package com.expenses.entity;

import com.expenses.valueobject.BudgetID;
import com.expenses.valueobject.ExpenseStatus;
import com.expenses.valueobject.Money;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "concept")
    private String concept;

    @Column(name = "amount")
    private int amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ExpenseStatus status;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "month", referencedColumnName = "month"),
        @JoinColumn(name = "year", referencedColumnName = "year"),
        @JoinColumn(name = "category", referencedColumnName = "category")
    })
    @JsonIgnore
    private Budget budget;

    public Expense() {}

    public Expense(Long id, String concept, Money amount, ExpenseStatus status, Budget budget) {
        this.id = id;
        this.concept = concept;
        this.amount = amount.toMXN() != 0 ? (int)(amount.toMXN() * 100) : 0;
        this.status = status;
        this.budget = budget;
    }

    public Expense(String concept, Money amount, ExpenseStatus status, Budget budget) {
        this.concept = concept;
        this.amount = amount.toMXN() != 0 ? (int)(amount.toMXN() * 100) : 0;
        this.status = status;
        this.budget = budget;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public void setAmount(Money amount) {
        this.amount = amount.toMXN() != 0 ? (int)(amount.toMXN() * 100) : 0;
    }

    public void changeStatus(ExpenseStatus status) {
        this.status = status;
    }

    public Long getId() {
        return this.id;
    }

    public String getConcept() {
        return this.concept;
    }

    public Money getAmount() {
        return new Money(this.amount);
    }

    public ExpenseStatus getStatus() {
        return this.status;
    }

    public Budget getBudget() {
        return this.budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }
}
