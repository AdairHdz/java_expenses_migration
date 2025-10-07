package com.expenses.config;

import com.expenses.converter.StringToBudgetCategoryConverter;
import com.expenses.converter.StringToExpenseStatusConverter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.expenses")
public class WebConfig implements WebMvcConfigurer {

    private final StringToBudgetCategoryConverter budgetCategoryConverter;
    private final StringToExpenseStatusConverter expenseStatusConverter;

    public WebConfig(StringToBudgetCategoryConverter budgetCategoryConverter,
                     StringToExpenseStatusConverter expenseStatusConverter) {
        this.budgetCategoryConverter = budgetCategoryConverter;
        this.expenseStatusConverter = expenseStatusConverter;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(budgetCategoryConverter);
        registry.addConverter(expenseStatusConverter);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
