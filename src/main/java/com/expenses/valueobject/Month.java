package com.expenses.valueobject;

import com.expenses.exception.DomainException;
import com.fasterxml.jackson.annotation.JsonValue;

public class Month {
    // number of the month
    private final int month;

    public Month(int monthNumber) {
        if(monthNumber < 1 || monthNumber > 12) {
            throw new DomainException("month number must be between 1 and 12");
        }
        this.month = monthNumber;
    }

    @JsonValue
    public int get() {
        return this.month;
    }
}
