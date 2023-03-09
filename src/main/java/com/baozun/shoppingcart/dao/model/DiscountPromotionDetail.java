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

/*  full:Int!
      #减多少钱
  discount:Int!*/

  private Integer full;

  private Integer discount;

  public DiscountPromotionDetail(){
    setType(PromotionTypeEnum.DISCOUNT);
  }
}
