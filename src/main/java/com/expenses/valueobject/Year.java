package com.expenses.valueobject;

import com.expenses.exception.DomainException;
import com.fasterxml.jackson.annotation.JsonValue;

public class Year {
    public final int year;

    public Year(int year) {
        if(year < 2000 || year > 2030) {
            throw new DomainException("year must be between 2000 and 2030");
        }
        this.year = year;
    }

    @JsonValue
    public int get() {
        return this.year;
    }
}
