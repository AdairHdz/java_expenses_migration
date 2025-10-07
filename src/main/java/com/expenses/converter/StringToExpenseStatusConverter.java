package com.expenses.converter;

import com.expenses.exception.DomainException;
import com.expenses.valueobject.ExpenseStatus;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToExpenseStatusConverter implements Converter<String, ExpenseStatus> {

    @Override
    public ExpenseStatus convert(String source) {
        try {
            int index = Integer.parseInt(source);
            if (index >= 0 && index < ExpenseStatus.values().length) {
                return ExpenseStatus.values()[index];
            }
            throw new DomainException("Invalid status index: " + index);
        } catch (NumberFormatException e) {
            return ExpenseStatus.valueOf(source);
        }
    }
}
