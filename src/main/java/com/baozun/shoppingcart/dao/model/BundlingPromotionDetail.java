package com.baozun.shoppingcart.dao.model;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BundlingPromotionDetail extends AbstractPromotionDetail {

  private List<Spu> spuList;

  private Integer discount;

  public BundlingPromotionDetail() {
    setType(PromotionTypeEnum.BUNDLING);
  }
}
