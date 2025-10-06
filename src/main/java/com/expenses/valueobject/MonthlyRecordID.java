package com.expenses.valueobject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MonthlyRecordID implements Serializable {
    @Column(name = "month")
    private int month;

    @Column(name = "year")
    private int year;

    public MonthlyRecordID() {}

    public MonthlyRecordID(Month month, Year year) {
        this.month = month.get();
        this.year = year.get();
    }

    public void setMonth(Month month) {
        this.month = month.get();
    }

    public void setYear(Year year) {
        this.year = year.get();
    }

    public Month getMonth() {
        return new Month(this.month);
    }

    public Year getYear() {
        return new Year(this.year);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonthlyRecordID that = (MonthlyRecordID) o;
        return month == that.month && year == that.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(month, year);
    }
}
