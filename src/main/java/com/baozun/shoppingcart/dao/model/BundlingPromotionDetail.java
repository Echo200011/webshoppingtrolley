package com.baozun.shoppingcart.dao.model;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BundlingPromotionDetail extends AbstractPromotionDetail {

  private Integer discount;

  public BundlingPromotionDetail() {
    setType(DetailTypeEnum.BUNDLING);
  }

  @Override
  public void calculatePrice(List<Spu> spuList) {
    if (spuList.size()<2){
      return;
    }
    spuList.forEach(spu -> spu.setBidPrice(spu.getBidPrice()*discount/1000));
  }
}
