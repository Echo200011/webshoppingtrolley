package com.baozun.shoppingcart.dao.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class GiftPromotionDetail extends AbstractPromotionDetail {

  public GiftPromotionDetail() {
    setType(PromotionTypeEnum.GIFT);
  }
}
