package com.baozun.shoppingcart.dao.model;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class GiftPromotionDetail extends AbstractPromotionDetail {


  private List<Spu> giftSpuList;

  public GiftPromotionDetail() {
    setLevel(3);
    setType(DetailTypeEnum.GIFT);
  }


  @Override
  public void calculatePrice(List<Spu> spuList) {

  }
}
