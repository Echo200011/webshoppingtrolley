package com.baozun.shoppingcart.dao.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DiscountPromotionDetail extends AbstractPromotionDetail {

  private Integer full;

  private Integer discount;

  public DiscountPromotionDetail() {
    setType(DetailTypeEnum.DISCOUNT);
  }
}
