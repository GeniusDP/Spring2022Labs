package com.spring.lab2.exceptions;

import com.spring.lab2.dto.response.AppError;
import com.spring.lab2.dto.response.ValidationExceptionResponse;
import com.spring.lab2.dto.response.Violation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(LotNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public AppError lotNotFoundExceptionHandler() {
        return AppError.justNow("No such lot found");
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public AppError userNotFoundExceptionHandler() {
        return AppError.justNow("No such user found");
    }

    @ExceptionHandler(InvalidLotUpdateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AppError handleInvalidLotUpdateException() {
        return AppError.justNow("Cannot update a lot with the SOLD status");
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationExceptionResponse bindExceptionHandle(BindException exception) {
        List<Violation> violations = exception.getAllErrors().stream()
                .map(objectError -> (FieldError) objectError)
                .map(objectError -> new Violation(objectError.getField(), objectError.getDefaultMessage()))
                .toList();
        return new ValidationExceptionResponse(violations);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationExceptionResponse constraintViolationExceptionHandle(ConstraintViolationException exception) {
        List<Violation> violations = exception.getConstraintViolations().stream()
                .map(objectError -> new Violation(objectError.getPropertyPath().toString(), objectError.getMessage()))
                .toList();
        return new ValidationExceptionResponse(violations);
    }

}
