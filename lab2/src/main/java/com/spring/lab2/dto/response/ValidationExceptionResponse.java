package com.spring.lab2.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ValidationExceptionResponse {

    private List<Violation> violations;

    public ValidationExceptionResponse(List<Violation> violations) {
        this.violations = violations;
    }

}