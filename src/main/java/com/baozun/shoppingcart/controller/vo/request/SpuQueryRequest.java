package com.baozun.shoppingcart.controller.vo.request;

import java.sql.Timestamp;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SpuQueryRequest extends PageParameterRequest {

  private String name;
  private Integer minPrice = 0;
  private Integer maxPrice = 0;
  private String categoryName;
  private String promotionName;
  private Timestamp createTime;
  private SpuStatusEnum status;
}
