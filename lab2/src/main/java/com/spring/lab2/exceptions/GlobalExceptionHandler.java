package com.spring.lab2.exceptions;

import com.spring.lab2.dto.response.AppError;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

  @ExceptionHandler(LotNotFoundException.class)
  public AppError lotNotFoundExceptionHandler(){
    return AppError.justNow("No such lot found");
  }

}
