package com.expenses.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class HttpErrorResponseDTO {
    private LocalDateTime timestamp;
    private String message;
    private Map<String, String> errors;

    public HttpErrorResponseDTO() {
    }

    public HttpErrorResponseDTO(LocalDateTime timestamp, String message, Map<String, String> errors) {
        this.timestamp = timestamp;
        this.message = message;
        this.errors = errors;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
