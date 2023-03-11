package com.baozun.shoppingcart.controller.vo.request;

import lombok.Data;

@Data
public class PageParameterRequest {

  private Integer pageNumber = 0;
  private Integer pageSize = 10;
}
