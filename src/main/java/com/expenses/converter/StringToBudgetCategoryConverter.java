package com.expenses.converter;

import com.expenses.exception.DomainException;
import com.expenses.valueobject.BudgetCategory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToBudgetCategoryConverter implements Converter<String, BudgetCategory> {

    @Override
    public BudgetCategory convert(String source) {
        try {
            int index = Integer.parseInt(source);
            if (index >= 0 && index < BudgetCategory.values().length) {
                return BudgetCategory.values()[index];
            }
            throw new DomainException("Invalid category index: " + index);
        } catch (NumberFormatException e) {
            return BudgetCategory.valueOf(source);
        }
    }
}
