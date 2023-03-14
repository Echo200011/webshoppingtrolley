package com.baozun.shoppingcart.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = Exception.class)
  public <T> Result<T> result(Exception e) {
    if (e instanceof AppException) {
      AppException appException = (AppException) e;
      return Result.error(appException.getCode(), appException.getMsg());
    }
    return Result.error(500, e.getMessage());
  }
}
