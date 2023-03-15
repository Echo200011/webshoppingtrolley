package com.baozun.shoppingcart.exception;

import lombok.Getter;

@Getter
public enum AppExceptionCodeMsg {

  UPDATE_SPU_STATUS_FAILED(2023, "商品状态更新失败"),

  CATEGORY_NOT_EXIST(2024, "商品类别不存在"),

  SPU_NOT_EXIST(2025, "商品不存在"),

  PRICE_SETTING_ERROR(2026, "价格设置错误"),

  SPU_IS_EXIST(2027, "商品已存在"),

  SPU_ALREADY_ON_SHELVES(2028, "商品已经上架"),

  SPU_ALREADY_SOLD_OUT(2029, "商品已经下架"),

  STOCK_MUST_HAVE(2030, "库存必须大于0"),

  ON_SHELVES_FAILED(2031, "上架失败"),

  SOLD_OUT_FAILED(2032, "下架失败"),

  SPU_MUST_ON_SHELVES(2033, "商品必须上架"),

  PRICE_RANGE_ERROR(2034,"价格范围出错"),

  PROMOTION_NOT_EXIST(2035, "活动不存在");


  private final int code;

  private final String msg;


  AppExceptionCodeMsg(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

}
