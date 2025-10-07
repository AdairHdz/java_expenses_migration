package com.expenses.valueobject;

import com.expenses.exception.DomainException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

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
    public static BudgetCategory fromValue(String value) {
        try {
            int index = Integer.parseInt(value);
            if (index >= 0 && index < values().length) {
                return values()[index];
            }
            throw new DomainException("Invalid category index: " + index);
        } catch (NumberFormatException e) {
            return BudgetCategory.valueOf(value);
        }
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }
}
