package com.baozun.shoppingcart.dao.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PromotionTypeEnum {
  DISCOUNT(DiscountPromotionDetail.class),
  GIFT(GiftPromotionDetail.class),
  BUNDLING(BundlingPromotionDetail.class);

  private final Class<? extends AbstractPromotionDetail> clz;
}
