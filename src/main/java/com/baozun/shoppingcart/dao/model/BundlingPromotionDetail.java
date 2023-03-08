package com.baozun.shoppingcart.dao.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BundlingPromotionDetail extends AbstractPromotionDetail {

  public BundlingPromotionDetail() {
    setType(PromotionTypeEnum.BUNDLING);
  }
}
