package com.baozun.shoppingcart.dao.model;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class GiftPromotionDetail extends AbstractPromotionDetail {

  private List<Spu> spuList;

  private List<Spu> giftSpuList;

  public GiftPromotionDetail() {
    setType(PromotionTypeEnum.GIFT);
  }
}
