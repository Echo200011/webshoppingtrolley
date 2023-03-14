package com.baozun.shoppingcart.controller.vo.request;

import javax.validation.constraints.Min;
import lombok.Data;

@Data
public class PageParameterRequest {

  @Min(value = 0, message = "页码不可为负")
  private Integer pageNumber = 0;
  @Min(value = 1, message = "页码大小最少为1")
  private Integer pageSize = 10;
}
