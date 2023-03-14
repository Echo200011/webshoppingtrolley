package com.baozun.shoppingcart.exception;

import lombok.Getter;

@Getter
public class Result<T> {

  private final int code;
  private final String msg;
  private final T data;

  public Result(int code, String msg, T data) {
    this.code = code;
    this.msg = msg;
    this.data = data;
  }

  public static <T> Result<T> success(T data) {
    return new Result<>(0, "success", data);
  }

  public static <T> Result<T> error(int code, String msg) {
    return new Result<>(code, msg, null);
  }

}
