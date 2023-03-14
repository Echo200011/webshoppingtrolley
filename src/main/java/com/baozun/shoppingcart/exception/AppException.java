package com.baozun.shoppingcart.exception;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException {

  private final int code;

  private final String msg;

  public AppException(AppExceptionCodeMsg codeMsg) {
    this.code = codeMsg.getCode();
    this.msg = codeMsg.getMsg();
  }
}
