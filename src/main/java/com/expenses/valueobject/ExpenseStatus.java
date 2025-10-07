package com.expenses.valueobject;

import com.expenses.exception.DomainException;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum ExpenseStatus {
    PAID,
    PENDING_OF_PAYMENT;

    @JsonCreator
    public static ExpenseStatus fromValue(Object value) {
        if (value instanceof Number) {
            int index = ((Number) value).intValue();
            if (index >= 0 && index < values().length) {
                return values()[index];
            }
            throw new DomainException("Invalid status index: " + index);
        }
        return ExpenseStatus.valueOf(value.toString());
    }
}
