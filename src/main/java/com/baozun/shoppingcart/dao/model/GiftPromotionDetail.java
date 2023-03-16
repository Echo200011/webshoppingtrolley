package com.baozun.shoppingcart.dao.model;

import com.baozun.shoppingcart.controller.vo.response.SpuData;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class GiftPromotionDetail extends AbstractPromotionDetail {


  private List<Integer> giftSpuIdList;

  public GiftPromotionDetail() {
    setLevel(3);
    setType(DetailTypeEnum.GIFT);
  }

  @Override
  public void calculatePrice(List<SpuData> spuData) {
    spuData.forEach(data-> {
    data.setGiftSpu(new SpuData());
    data.getGiftSpu().setId(giftSpuIdList.stream().findAny().orElse(null));
    });
  }
}
