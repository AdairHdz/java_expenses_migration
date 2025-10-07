package com.expenses.valueobject;

import com.expenses.exception.DomainException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ExpenseStatus {
    PAID,
    PENDING_OF_PAYMENT;

    @JsonCreator
    public static ExpenseStatus fromValue(String value) {
        try {
            int index = Integer.parseInt(value);
            if (index >= 0 && index < values().length) {
                return values()[index];
            }
            throw new DomainException("Invalid status index: " + index);
        } catch (NumberFormatException e) {
            return ExpenseStatus.valueOf(value);
        }
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }
}
