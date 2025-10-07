package com.expenses.valueobject;

import com.expenses.exception.DomainException;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum BudgetCategory {
    PERSONAL,
    SERVICES,
    MENTAL_HEALTH,
    PHYSICAL_HEALTH,
    FINANCES,
    PETS,
    HOUSE,
    WORKSPACE,
    ESSENTIALS,
    LEARNING,
    GROCERIES,
    CLOTHES,
    TRANSPORT;

    @JsonCreator
    public static BudgetCategory fromValue(Object value) {
        if (value instanceof Number) {
            int index = ((Number) value).intValue();
            if (index >= 0 && index < values().length) {
                return values()[index];
            }
            throw new DomainException("Invalid category index: " + index);
        }
        return BudgetCategory.valueOf(value.toString());
    }
}
