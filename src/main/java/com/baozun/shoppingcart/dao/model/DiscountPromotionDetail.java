package com.baozun.shoppingcart.dao.model;


import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@JsonTypeName("discount")
public class DiscountPromotionDetail extends AbstractPromotionDetail {

  private String test;

  public DiscountPromotionDetail(){
    setType(PromotionTypeEnum.DISCOUNT);
  }
}
