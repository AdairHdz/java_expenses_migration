package com.expenses.valueobject;

import com.expenses.exception.DomainException;
import com.fasterxml.jackson.annotation.JsonValue;

public class Money {
    // money in cents
    private final int amount;

    public Money(int amount) {
        if(amount < 0) {
            throw new DomainException("amount cant be less than zero");
        }
        this.amount = amount;
    }

    @JsonValue
    public int cents() {
        return this.amount;
    }

    public double toMXN() {
        return (double) this.amount / 100;
    }

    public Money add(Money money) {
        return new Money(this.amount + money.cents());
    }

    public Money subtract(Money money) {
        return new Money(this.amount - money.cents());
    }
}
