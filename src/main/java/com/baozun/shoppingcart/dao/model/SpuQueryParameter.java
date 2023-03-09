package com.baozun.shoppingcart.dao.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class SpuQueryParameter {

  private String name;
  private Integer maxPrice;
  private Integer minPrice;
  private String categoryName;
  private String promotionName;
}
