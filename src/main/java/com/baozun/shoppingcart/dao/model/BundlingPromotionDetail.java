package com.baozun.shoppingcart.dao.model;

import com.baozun.shoppingcart.controller.vo.response.SpuData;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BundlingPromotionDetail extends AbstractPromotionDetail {

  private Integer discount;

  public BundlingPromotionDetail() {
    setLevel(1);
    setType(DetailTypeEnum.BUNDLING);
  }

  @Override
  public void calculatePrice(List<SpuData> spuData) {
    if (spuData.size() < 2) {
      return;
    }
    spuData.forEach(spuResult -> spuResult.setPromotionPrice((spuResult.getPromotionPrice() * discount / 1000))
    );
  }
}
