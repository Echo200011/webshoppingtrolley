package com.baozun.shoppingcart.dao.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BundlingPromotionDetail extends AbstractPromotionDetail {

  private Integer discount;

  public BundlingPromotionDetail() {
    setType(DetailTypeEnum.BUNDLING);
  }
}
