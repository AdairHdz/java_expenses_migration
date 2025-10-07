package com.expenses.config;

import com.expenses.converter.StringToBudgetCategoryConverter;
import com.expenses.converter.StringToExpenseStatusConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

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

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper());
        converters.add(converter);
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
